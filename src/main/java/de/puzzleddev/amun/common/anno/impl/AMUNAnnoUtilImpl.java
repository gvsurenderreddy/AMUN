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

import com.google.common.collect.Maps;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import de.puzzleddev.amun.common.anno.AnnotationData;
import de.puzzleddev.amun.common.anno.IAMUNAnnoCheck;
import de.puzzleddev.amun.common.anno.IAMUNAnnoUtil;
import de.puzzleddev.amun.common.anno.IAMUNAnnotationCallback;
import de.puzzleddev.amun.common.anno.IAMUNAnnotationRegistry;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotation;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotationHolder;
import de.puzzleddev.amun.common.anno.construct.AMUNAnnotationSearch;
import de.puzzleddev.amun.common.anno.construct.AMUNCheck;
import de.puzzleddev.amun.common.core.AMUN;
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

		callAll(0);
		callAll(1);
		callAll(2);
		callAll(3);
		callAll(4);

		for(AnnotationHolder ah : m_holders.values())
		{
			ah.checkRegistry();
		}

		callAll(5);
		callAll(6);
		callAll(7);
		callAll(8);
		callAll(9);
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

	private static Map<Class<? extends IAMUNAnnoCheck>, IAMUNAnnoCheck> m_checks = Maps.newHashMap();

	public static boolean isAllowed(Annotation[] ans)
	{
		for(Annotation an : ans)
		{
			if(an.annotationType() == AMUNCheck.class)
			{
				Class<? extends IAMUNAnnoCheck> check = ((AMUNCheck) an).check();
				String[] data = ((AMUNCheck) an).data();

				if(!m_checks.containsKey(check))
				{
					try
					{
						IAMUNAnnoCheck cobj = check.newInstance();

						m_checks.put(check, cobj);
					} catch(Throwable t)
					{
						t.printStackTrace();
					}
				}

				if(!m_checks.get(check).check(data))
					return false;
			}
		}

		return true;
	}

	private boolean checkHolder(Class<?> cls) throws IOException
	{
		if(m_holders.containsKey(cls))
			return true;

		boolean found = false;

		if(!isAllowed(cls.getAnnotations()))
			return false;

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
	private <A extends Annotation> void callAll(int state)
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

					int[] toCall = and.getAnnotation().annotationType().getAnnotation(AMUNAnnotation.class).toCall();

					boolean found = false;

					for(int s : toCall)
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
		callAll(1);
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		callAll(2);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		callAll(3);
	}

}
