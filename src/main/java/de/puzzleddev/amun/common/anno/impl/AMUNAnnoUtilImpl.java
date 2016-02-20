package de.puzzleddev.amun.common.anno.impl;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAMUNAnnoUtil;
import de.puzzleddev.amun.common.anno.IAMUNAnnotationCallback;
import de.puzzleddev.amun.common.anno.IAMUNAnnotationRegistry;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotation;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotationHolder;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotationSearch;
import de.puzzleddev.amun.common.anno.construct.AMUNModOnly;
import de.puzzleddev.amun.common.core.AMUN;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.ModAPIManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class AMUNAnnoUtilImpl implements IAMUNAnnoUtil
{

	public AMUNAnnoUtilImpl()
	{
		m_reg = new AMUNAnnotationRegistryImpl();
		m_holders = new HashMap<Class<?>, AnnotationHolder>();
	}

	private IAMUNAnnotationRegistry m_reg;

	@Override
	public IAMUNAnnotationRegistry getRegistry()
	{
		return m_reg;
	}

	private Map<Class<?>, AnnotationHolder> m_holders;

	@Override
	public void constructAnnotations(Class<?>[] base)
	{
		AMUN.instance().addLoadHook(this);

		check(base);

		callAll(LoaderState.LOADING);

		for(AnnotationHolder ah : m_holders.values())
		{
			ah.checkRegistry();
		}

		callAll(LoaderState.NOINIT);
		callAll(LoaderState.CONSTRUCTING);
	}

	private void check(Class<?>[] base)
	{
		for(Class<?> cls : base)
		{
			if(!checkAnnotation(cls))
			{
				try
				{
					checkHolder(cls);
				} catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	private boolean checkAnnotation(Class<?> cls)
	{
		if(cls.isAnnotation() && cls.isAnnotationPresent(AMUNAnnotation.class))
		{
			IAMUNAnnotationCallback<?> obj = null;

			try
			{
				Constructor<? extends IAMUNAnnotationCallback<?>> con = cls.getAnnotation(AMUNAnnotation.class).value().getConstructor();

				if(con == null)
					return false;

				obj = con.newInstance();

			} catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}

			AMUN.ANNOTATION.getRegistry().setRaw(cls, obj);

			return true;
		}

		return false;
	}

	private static ClassPath CLASS_PATH = null;

	static
	{
		try
		{
			CLASS_PATH = ClassPath.from(AMUNAnnoUtilImpl.class.getClassLoader());
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public static boolean isAllowed(Annotation[] ans)
	{
		for(Annotation an : ans)
		{
			if(an.annotationType() == AMUNModOnly.class)
			{
				String id = ((AMUNModOnly) an).value();
				
				if(!Loader.isModLoaded(id) && ! ModAPIManager.INSTANCE.hasAPI(id)) return false;
			}	
		}
		
		return true;
	}
	
	private boolean checkHolder(Class<?> cls) throws IOException
	{
		if(m_holders.containsKey(cls))
			return true;

		boolean found = false;

		if(!isAllowed(cls.getAnnotations())) return false;
		
		for(Annotation an : cls.getAnnotations())
		{
			if(an.annotationType().isAnnotationPresent(AMUNAnnotationHolder.class))
			{
				found = true;
				break;
			}
		}

		if(!found)
			return false;

		AMUNAnnotationSearch amunAnno = null;

		if(cls.isAnnotationPresent(AMUNAnnotationSearch.class))
		{
			amunAnno = cls.getAnnotation(AMUNAnnotationSearch.class);
		}

		AnnotationHolder hold = new AnnotationHolder(amunAnno, cls);

		List<Class<?>> clss = new ArrayList<Class<?>>();
		
		if(amunAnno != null)
		{

			if(hold.getAnnotation().searchClasses().length > 0)
			{
				clss.addAll(Arrays.asList(hold.getAnnotation().searchClasses()));
			}

			if(hold.getAnnotation().searchPackages().length > 0)
			{
				for(String pkg : hold.getAnnotation().searchPackages())
				{
					Set<ClassInfo> set = CLASS_PATH.getTopLevelClasses(pkg);

					for(ClassInfo ci : set)
					{
						clss.add(ci.load());
					}
				}
			}

		}

		m_holders.put(cls, hold);

		if(!clss.isEmpty())
			check(clss.toArray(new Class<?>[0]));

		return true;
	}

	@SuppressWarnings("unchecked")
	private <A extends Annotation> void callAll(LoaderState state)
	{
		for(AnnotationHolder amcd : m_holders.values())
		{
			if(amcd.getAnnotations() == null)
				continue;

			for(AnnotationData<?> and : amcd.getAnnotations())
			{
				try
				{
					if(!and.getAnnotation().annotationType().isAnnotationPresent(AMUNAnnotation.class))
						continue;

					LoaderState[] toCall = and.getAnnotation().annotationType().getAnnotation(AMUNAnnotation.class).toCall();

					boolean found = false;

					for(LoaderState s : toCall)
					{
						if(s == state)
						{
							found = true;
							break;
						}
					}

					if(!found)
						continue;

					if(AMUN.ANNOTATION.getRegistry().has(and.getAnnotation().annotationType()))
					{
						IAMUNAnnotationCallback<A> calBack = (IAMUNAnnotationCallback<A>) AMUN.ANNOTATION.getRegistry().get(and.getAnnotation().annotationType());

						calBack.call(state, (AnnotationData<A>) and);
					}

				} catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		callAll(LoaderState.PREINITIALIZATION);
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		callAll(LoaderState.INITIALIZATION);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		callAll(LoaderState.POSTINITIALIZATION);
	}

}
