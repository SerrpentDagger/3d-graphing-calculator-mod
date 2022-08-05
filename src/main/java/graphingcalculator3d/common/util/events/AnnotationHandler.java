package graphingcalculator3d.common.util.events;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * Lightweight Annotation-handling tool, to gather and keep track of all Annotation Objects and Classes implementing Annotation interfaces
 * from the given set of Classes. Annotations are only re-gathered if the searching domain has changed since the last time they were gathered, meaning that
 * the tool searches through the domain only as often as is necessary.
 *
 * @author SerpentDagger
 */
public class AnnotationHandler
{
	/** The searching domain. */
	private static ArrayList<Class<?>> domain = new ArrayList<Class<?>>();
	/** The map of Annotation types to their Object-Annotation maps.*/
	private static HashMap<Class<? extends Annotation>, HashMap<Object, ? extends Annotation>> annMap = new HashMap<Class<? extends Annotation>, HashMap<Object, ? extends Annotation>>();
	/** The map of Annotation types to whether the domain has changed since their last gathering */
	private static HashMap<Class<? extends Annotation>, Boolean> domainChanged = new HashMap<Class<? extends Annotation>, Boolean>();
	
	/**
	 * Stretch the searching domain to include the given class, its superclass, its declaring class, its interfaces, its inner classes, the classes of its fields,
	 * and the classes of its methods' and constructors' parameters (if not already present within domain).
	 * <b>Recursively calls on those classes as well.</b>
	 * @param newDomainClass
	 * @return true if domain has successfully been changed.
	 * @author SerpentDagger
	 */
	public static boolean stretchDomainExtensiveRecursive(Class<?> newDomainClass)
	{
		if (newDomainClass != null && !domain.contains(newDomainClass))
		{
			domain.add(newDomainClass);
			stretchDomainExtensiveRecursive(newDomainClass.getSuperclass());
			stretchDomainExtensiveRecursive(newDomainClass.getDeclaringClass());
			for (Class<?> cl : newDomainClass.getInterfaces())
			{
				stretchDomainExtensiveRecursive(cl);
			}
			for (Class<?> cl : newDomainClass.getClasses())
			{
				stretchDomainExtensiveRecursive(cl);
			}
			for (Field fd : newDomainClass.getFields())
			{
				stretchDomainExtensiveRecursive(fd.getType());
			}
			for (Method md : newDomainClass.getMethods())
			{
				for (Parameter pr : md.getParameters())
				{
					stretchDomainExtensiveRecursive(pr.getType());
				}
			}
			domainChanged();
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Stretch the searching domain to include the given class, its superclass, its declaring class, its interfaces, its inner classes, the classes of its fields,
	 * and the classes of its methods' parameters (if not already present within domain).
	 * @param newDomainClass
	 * @return true if domain has successfully been changed.
	 * @author SerpentDagger
	 */
	public static boolean stretchDomainExtensive(Class<?> newDomainClass)
	{
		if (newDomainClass != null && !domain.contains(newDomainClass))
		{
			domain.add(newDomainClass);
			domain.add(newDomainClass.getSuperclass());
			domain.add(newDomainClass.getDeclaringClass());
			for (Class<?> cl : newDomainClass.getInterfaces())
			{
				domain.add(cl);
			}
			for (Class<?> cl : newDomainClass.getClasses())
			{
				domain.add(cl);
			}
			for (Field fd : newDomainClass.getFields())
			{
				domain.add(fd.getType());
			}
			for (Method md : newDomainClass.getMethods())
			{
				for (Parameter pr : md.getParameters())
				{
					domain.add(pr.getType());
				}
			}
			domainChanged();
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Stretch the searching domain to include the given class, its superclass, its declaring class, its interfaces, and its inner classes (if not already present within domain).
	 * <b>Recursively calls on those classes as well.</b>
	 * @param newDomainClass
	 * @return true if domain has successfully been changed.
	 * @author SerpentDagger
	 */
	public static boolean stretchDomainUpDownRecursive(Class<?> newDomainClass)
	{
		if (newDomainClass != null && !domain.contains(newDomainClass))
		{
			domain.add(newDomainClass);
			stretchDomainUpDownRecursive(newDomainClass.getSuperclass());
			stretchDomainUpDownRecursive(newDomainClass.getDeclaringClass());
			for (Class<?> cl : newDomainClass.getInterfaces())
			{
				stretchDomainUpDownRecursive(cl);
			}
			for (Class<?> cl : newDomainClass.getClasses())
			{
				stretchDomainUpDownRecursive(cl);
			}
			domainChanged();
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Stretch the searching domain to include the given class, its superclass, its declaring class, its interfaces, and its inner classes (if not already present within domain).
	 * @param newDomainClass
	 * @return true if domain has successfully been changed.
	 * @author SerpentDagger
	 */
	public static boolean stretchDomainUpDown(Class<?> newDomainClass)
	{
		if (newDomainClass != null && !domain.contains(newDomainClass))
		{
			domain.add(newDomainClass);
			domain.add(newDomainClass.getSuperclass());
			domain.add(newDomainClass.getDeclaringClass());
			for (Class<?> cl : newDomainClass.getInterfaces())
			{
				domain.add(cl);
			}
			for (Class<?> cl : newDomainClass.getClasses())
			{
				domain.add(cl);
			}
			domainChanged();
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Stretch the searching domain to include the given class only.
	 * @param newDomainClass
	 * @return true if domain has successfully been changed.
	 * @author SerpentDagger
	 */
	public static boolean stretchDomain(Class<?> newDomainClass)
	{
		if (newDomainClass != null && !domain.contains(newDomainClass))
		{
			domain.add(newDomainClass);
			domainChanged();
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Shrink the searching domain to remove the given class, its superclass, its declaring class, its interfaces, its inner classes, the classes of its fields,
	 * and the classes of its methods' parameters (if present within domain).
	 * <b>Recursively calls on those classes as well.</b>
	 * @param domainToRemove
	 * @return true if domain has successfully been changed.
	 * @author SerpentDagger
	 */
	public static boolean shrinkDomainExtensiveRecursive(Class<?> domainToRemove)
	{
		if (domainToRemove != null && domain.contains(domainToRemove))
		{
			domain.remove(domainToRemove);
			shrinkDomainExtensiveRecursive(domainToRemove.getSuperclass());
			shrinkDomainExtensiveRecursive(domainToRemove.getDeclaringClass());
			for (Class<?> cl : domainToRemove.getInterfaces())
			{
				shrinkDomainExtensiveRecursive(cl);
			}
			for (Class<?> cl : domainToRemove.getClasses())
			{
				shrinkDomainExtensiveRecursive(cl);
			}
			for (Field fd : domainToRemove.getFields())
			{
				shrinkDomainExtensiveRecursive(fd.getType());
			}
			for (Method md : domainToRemove.getMethods())
			{
				for (Parameter pr : md.getParameters())
				{
					shrinkDomainExtensiveRecursive(pr.getType());
				}
			}
			domainChanged();
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Shrink the searching domain to remove the given class, its superclass, its declaring class, its interfaces, its inner classes, the classes of its fields,
	 * and the classes of its methods' parameters (if present within domain).
	 * @param domainToRemove
	 * @return true if domain has successfully been changed.
	 * @author SerpentDagger
	 */
	public static boolean shrinkDomainExtensive(Class<?> domainToRemove)
	{
		if (domainToRemove != null && domain.contains(domainToRemove))
		{
			domain.remove(domainToRemove);
			domain.remove(domainToRemove.getSuperclass());
			domain.remove(domainToRemove.getDeclaringClass());
			for (Class<?> cl : domainToRemove.getInterfaces())
			{
				domain.remove(cl);
			}
			for (Class<?> cl : domainToRemove.getClasses())
			{
				domain.remove(cl);
			}
			for (Field fd : domainToRemove.getFields())
			{
				domain.remove(fd.getType());
			}
			for (Method md : domainToRemove.getMethods())
			{
				for (Parameter pr : md.getParameters())
				{
					domain.remove(pr.getType());
				}
			}
			domainChanged();
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Shrink the searching domain to remove the given class, its superclass, its declaring class, its interfaces, and its inner classes (if not already present within domain).
	 * <b>Recursively calls on those classes as well.</b>
	 * @param domainToRemove
	 * @return true if domain has successfully been changed.
	 * @author SerpentDagger
	 */
	public static boolean shrinkDomainUpDownRecursive(Class<?> domainToRemove)
	{
		if (domainToRemove != null && !domain.contains(domainToRemove))
		{
			domain.remove(domainToRemove);
			shrinkDomainUpDownRecursive(domainToRemove.getSuperclass());
			shrinkDomainUpDownRecursive(domainToRemove.getDeclaringClass());
			for (Class<?> cl : domainToRemove.getInterfaces())
			{
				shrinkDomainUpDownRecursive(cl);
			}
			for (Class<?> cl : domainToRemove.getClasses())
			{
				shrinkDomainUpDownRecursive(cl);
			}
			domainChanged();
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Shrink the searching domain to remove the given class, its superclass, its declaring class, its interfaces, and its inner classes (if not already present within domain).
	 * @param domainToRemove
	 * @return true if domain has successfully been changed.
	 * @author SerpentDagger
	 */
	public static boolean shrinkDomainUpDown(Class<?> domainToRemove)
	{
		if (domainToRemove != null && !domain.contains(domainToRemove))
		{
			domain.remove(domainToRemove);
			domain.remove(domainToRemove.getSuperclass());
			domain.remove(domainToRemove.getDeclaringClass());
			for (Class<?> cl : domainToRemove.getInterfaces())
			{
				domain.remove(cl);
			}
			for (Class<?> cl : domainToRemove.getClasses())
			{
				domain.remove(cl);
			}
			domainChanged();
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Remove given class from searching domain.
	 * @param domainToRemove
	 * @return true if domain contained specified class.
	 * @author SerpentDagger
	 */
	public static boolean shrinkDomain(Class<?> domainToRemove)
	{
		boolean ch = domain.remove(domainToRemove);
		if (ch)
			domainChanged();
		return ch;
	}
	
	/**
	 * Mark the domain as having been changed.
	 * @author SerpentDagger
	 */
	private static void domainChanged()
	{
		domainChanged.forEach((k, v) ->
		{
			domainChanged.put(k, true);
		});
	}
	
	/**
	 * @return The searching domain of classes.
	 * @author SerpentDagger
	 */
	public static ArrayList<Class<?>> getDomain()
	{
		return domain;
	}
	
	/**
	 * @param cl
	 * @return true if the domains contains cl.
	 * @author SerpentDagger
	 */
	public static boolean domainContains(Class<?> cl)
	{
		return domain.contains(cl);
	}
	
	/**
	 * @param type
	 * @return true if the domain has changed since type was last gathered or if type has never been gathered before, false if type's map is up-to-date.
	 * @author SerpentDagger
	 */
	public static <A extends Annotation> boolean domainChangedFor(Class<A> type)
	{
		Boolean b = domainChanged.get(type);
		return b == null ? true : b;
	}
	
	/**
	 * @param classes
	 * @param type
	 * @return HashMap&ltObject, A extends Annotation&gt of all Annotations within the classes supplied, of type type, as values corresponding to their annotated Objects,
	 * unless the value is null, in which case the Object is the class implementing the annotation interface.
	 * @author SerpentDagger
	 */
	public static <A extends Annotation> HashMap<Object, A> fromClasses(ArrayList<Class<?>> classes, Class<A> type)
	{
		HashMap<Object, A> map = new HashMap<Object, A>();
		classes.forEach((cl) ->
		{
			fromClassToMap(cl, type, map);
		});
		return map;
	}
	
	/**
	 * @param cl
	 * @param type
	 * @return HashMap&ltObject, A extends Annotation&gt of all Annotations within the class supplied, of type type, as values corresponding to their annotated Objects,
	 * unless the value is null, in which case the Object is the class implementing the annotation interface.
	 */
	public static <A extends Annotation> HashMap<Object, A> fromClass(Class<?> cl, Class<A> type)
	{
		HashMap<Object, A> map = new HashMap<Object, A>();
		fromClassToMap(cl, type, map);
		return map;
	}
	
	/**
	 * @param type
	 * @return HashMap&ltObject, A extends Annotation&gt of all Annotations within the searching domain, of type type, as values corresponding to their annotated Objects,
	 * unless the value is null, in which case the Object is the class implementing the annotation interface.
	 * @author SerpentDagger
	 */
	@SuppressWarnings("unchecked")
	public static <A extends Annotation> HashMap<Object, A> fromDomain(Class<A> type)
	{
		if (domainChangedFor(type))
		{
			HashMap<Object, A> map = fromClasses(domain, type);
			annMap.put(type, map);
			domainChanged.put(type, false);
			return map;
		}
		else
		{
			return (HashMap<Object, A>) annMap.get(type);
		}
	}
	
	/**
	 * Adds the annotations from the given class of the given type to the given map.
	 * @param cl
	 * @param type
	 * @param map
	 * @return Updated map
	 * @author SerpentDagger
	 */
	@SuppressWarnings("unchecked")
	public static <A extends Annotation> HashMap<Object, A> fromClassToMap(Class<?> cl, Class<A> type, HashMap<Object, A> map)
	{
		if (cl == null || type == null || map == null)
			return map;
		for (Annotation clAn : cl.getAnnotationsByType(type))
		{
			map.put(cl, (A) clAn);
		}
		for (Field fd : cl.getFields())
		{
			for (Annotation fdAn : fd.getAnnotationsByType(type))
			{
				map.put(fd, (A) fdAn);
			}
		}
		for (Method md : cl.getMethods())
		{
			for (Annotation mdAn : md.getAnnotationsByType(type))
			{
				map.put(md, (A) mdAn);
			}
			for (Parameter pr : md.getParameters())
			{
				try
				{
					for (Annotation prAn : pr.getAnnotationsByType(type))
					{
						map.put(pr, (A) prAn);
					}
				}
				catch (IndexOutOfBoundsException e)
				{
					System.out.println("Error getting parameters from Method Parameter in Class: " + cl.getName() + ", Method: " + md.getName()
							+ ", Parameter: " + pr.getName() + ". Skipping.");
				}
			}
		}
		for (Constructor<?> cr : cl.getConstructors())
		{
			for (Annotation crAn : cr.getAnnotationsByType(type))
			{
				map.put(cr, (A) crAn);
			}
			for (Parameter pr : cr.getParameters())
			{
				try
				{
					for (Annotation prAn : pr.getAnnotationsByType(type))
					{
						map.put(pr, (A) prAn);
					}
				}
				catch (IndexOutOfBoundsException e)
				{
					System.out.println("Error getting parameters from Constructor Parameter in Class: " + cl.getName() + ", Constructor: " + cr.getName()
							+ ", Parameter: " + pr.getName() + ". Skipping.");
				}
			}
		}
		for (Class<?> in : cl.getInterfaces())
		{
			if (in == type)
			{
				map.put(cl, null);
			}
		}
		
		return map;
	}
}
