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
import de.puzzleddev.amun.common.anno.IAmunAnnoCheck;
import de.puzzleddev.amun.common.anno.IAmunAnnoUtil;
import de.puzzleddev.amun.common.anno.IAmunAnnotationCallback;
import de.puzzleddev.amun.common.anno.IAmunAnnotationRegistry;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotation;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationHolder;
import de.puzzleddev.amun.common.anno.construct.AmunAnnotationSearch;
import de.puzzleddev.amun.common.anno.construct.AmunCheck;
import de.puzzleddev.amun.common.core.Amun;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class AmunAnnoUtilImpl implements IAmunAnnoUtil
{

	public AmunAnnoUtilImpl()
	{
		m_reg = new AmunAnnotationRegistryImpl();
		m_holders = new HashMap<Class<?>, AnnotationHolder>();
	}

	private IAmunAnnotationRegistry m_reg;

	@Override
	public IAmunAnnotationRegistry getRegistry()
	{
		return m_reg;
	}

	private Map<Class<?>, AnnotationHolder> m_holders;

	@Override
	public void constructAnnotations(Class<?>[] base)
	{
		Amun.instance().addLoadHook(this);

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
		if(cls.isAnnotation() && cls.isAnnotationPresent(AmunAnnotation.class))
		{
			IAmunAnnotationCallback<?> obj = null;

			try
			{
				Constructor<? extends IAmunAnnotationCallback<?>> con = cls.getAnnotation(AmunAnnotation.class).value().getConstructor();

				if(con == null)
					return false;

				obj = con.newInstance();

			} catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}

			Amun.ANNOTATION.getRegistry().setRaw(cls, obj);

			return true;
		}

		return false;
	}

	private static ClassPath CLASS_PATH = null;

	static
	{
		try
		{
			CLASS_PATH = ClassPath.from(AmunAnnoUtilImpl.class.getClassLoader());
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private static Map<Class<? extends IAmunAnnoCheck>, IAmunAnnoCheck> m_checks = Maps.newHashMap();

	public static boolean isAllowed(Annotation[] ans)
	{
		for(Annotation an : ans)
		{
			if(an.annotationType() == AmunCheck.class)
			{
				Class<? extends IAmunAnnoCheck> check = ((AmunCheck) an).check();
				String[] data = ((AmunCheck) an).data();

				if(!m_checks.containsKey(check))
				{
					try
					{
						IAmunAnnoCheck cobj = check.newInstance();

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
			if(an.annotationType().isAnnotationPresent(AmunAnnotationHolder.class))
			{
				found = true;
				break;
			}
		}

		if(!found)
			return false;

		AmunAnnotationSearch amunAnno = null;

		if(cls.isAnnotationPresent(AmunAnnotationSearch.class))
		{
			amunAnno = cls.getAnnotation(AmunAnnotationSearch.class);
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
					if(!and.getAnnotation().annotationType().isAnnotationPresent(AmunAnnotation.class))
						continue;

					int[] toCall = and.getAnnotation().annotationType().getAnnotation(AmunAnnotation.class).toCall();

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

					if(Amun.ANNOTATION.getRegistry().has(and.getAnnotation().annotationType()))
					{
						IAmunAnnotationCallback<A> calBack = (IAmunAnnotationCallback<A>) Amun.ANNOTATION.getRegistry().get(and.getAnnotation().annotationType());
						
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
		callAll(10);
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		callAll(11);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		callAll(12);
	}

}
