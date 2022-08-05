package graphingcalculator3d.common.util.events;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Lightweight event system designed to work alongside {@linkplain AnnotationHandler} and {@linkplain TriggerOn}.
 * <p>Objects (non-static) or Classes (static) are registered to the Event system using one of the {@linkplain Event#register(Object)} methods,
 * and unregistered using one of the {@linkplain Event#unRegister(Object)} methods. Registered listeners then recieve an event when one is triggered. This means that any
 * Methods within the listener that are annotated with {@linkplain TriggerOn}, with the correct key, will be run when the corresponding event is triggered.
 * <p> To trigger an event, use one of the {@linkplain Event#triggerO(String, Object...)} methods. You can trigger all, static, or non-static methods, use any number
 * and type of parameters (mixing of types is fine), and you will recieve all the returns of the methods called, contained in an array of some sort.
 *
 * @author SerpentDagger
 */
public class Event
{
	/** Maps the active Methods, Objects, and keys together. */
	private static HashMap<String, HashMap<Method, Object>> methodMap = new HashMap<String, HashMap<Method, Object>>();
	/** Maps the active Classes to their keys' registry conditions. */
	private static HashMap<Class<?>, HashMap<String, Boolean>> classMap = new HashMap<Class<?>, HashMap<String, Boolean>>(); 
	
	/**
	 * Register the Object to all events within its {@linkplain TriggerOn} annotations.
	 * @param obj Object to register.
	 * @return Whether or not the Object was registered.
	 * @author SerpentDagger
	 */
	public static boolean register(Object obj)
	{
		if (obj instanceof Class)
		{
			return AnnotationHandler.stretchDomain((Class<?>) obj);
		}
		else
		{
			HashMap<Object, TriggerOn> triggers = AnnotationHandler.fromClass(obj.getClass(), TriggerOn.class);
			if (triggers.size() == 0)
				return false;
			else
			{
				triggers.forEach((ob, tr) ->
				{
					if (!methodMap.containsKey(tr.value()))
						methodMap.put(tr.value(), new HashMap<Method, Object>());
					methodMap.get(tr.value()).put((Method) ob, obj);
				});
				return true;
			}
		}
	}
	
	/**
	 * Register the Object to only the event matching the given key.
	 * @param obj Object to register.
	 * @param key Event key.
	 * @return Whether or not the Object was registered.
	 * @author SerpentDagger
	 */
	public static boolean register(Object obj, String key)
	{
		if (obj instanceof Class)
		{
			Class<?> cl = (Class<?>) obj;
			HashMap<String, Boolean> map = classMap.get(cl);
			if (map == null)
			{
				map = new HashMap<String, Boolean>();
				classMap.put(cl, map);
			}
			map.put(key, true);
			return true;
		}
		else
		{
			HashMap<Object, TriggerOn> triggers = AnnotationHandler.fromClass(obj.getClass(), TriggerOn.class);
			if (triggers.size() == 0)
				return false;
			else
			{
				AtomicBoolean added = new AtomicBoolean(false);
				triggers.forEach((ob, tr) ->
				{
					if (!methodMap.containsKey(tr.value()))
						methodMap.put(tr.value(), new HashMap<Method, Object>());
					if (key.equals(tr.value()))
					{
						methodMap.get(tr.value()).put((Method) ob, obj);
						added.set(true);
					}
				});
				return added.get();
			}
		}
	}
	
	/**
	 * Unregister the Object from all events.
	 * @param obj Object to unregister.
	 * @return Whether or not the Object was unregistered.
	 * @author SerpentDagger
	 */
	public static boolean unRegister(Object obj)
	{
		if (obj instanceof Class)
		{
			return AnnotationHandler.shrinkDomain((Class<?>) obj);
		}
		else
		{
			AtomicBoolean removed = new AtomicBoolean(false);
			methodMap.forEach((ky, mp) ->
			{
				mp.values().removeIf((ob) ->
				{
					if (ob == obj)
					{
						removed.set(true);
						return true;
					}
					else
						return false;
				});
			});
			return removed.get();
		}
	}
	
	/**
	 * Unregister the Object from only the event matching the given key.
	 * @param obj Object to unregister.
	 * @param key Event key.
	 * @return Whether or not the Object was unregistered.
	 * @author SerpentDagger
	 */
	public static boolean unRegister(Object obj, String key)
	{
		if (obj instanceof Class)
		{
			Class<?> cl = (Class<?>) obj;
			HashMap<String, Boolean> map = classMap.get(cl);
			if (map == null)
			{
				map = new HashMap<String, Boolean>();
				classMap.put(cl, map);
			}
			map.put(key, false);
			return true;
		}
		else
		{
			AtomicBoolean removed = new AtomicBoolean(false);
			methodMap.get(key).values().removeIf((ob) ->
			{
				if (ob == obj)
				{
					removed.set(true);
					return true;
				}
				else
					return false;
			});
			return removed.get();
		}
	}
	
	/**
	 * Run all methods registered, annotated with {@linkplain TriggerOn}, with the given arguments
	 * <p>Args can be null when running a method with no parameters.
	 * @param key
	 * @param args
	 * @return EventReturn containing all returns from the methods run, according to {@linkplain Method#invoke(Object, Object...)}, and sorted into static and non-static arrays.
	 * @author SerpentDagger
	 */
	public static EventReturn triggerER(String key, Object... args)
	{
		return new EventReturn(Event.triggerStatic(key, args), Event.triggerInstance(key, args));
	}

	/**
	 * Run all methods registered, annotated with {@linkplain TriggerOn}, with the given arguments
	 * <p>Args can be null when running a method with no parameters.
	 * @param key
	 * @param args
	 * @return Object[] containing all returns from the methods run, according to {@linkplain Method#invoke(Object, Object...)}.
	 * @author SerpentDagger
	 */
	public static Object[] triggerO(String key, Object... args)
	{
		Object[] st = triggerStatic(key, args);
		Object[] in = triggerInstance(key, args);
		Object[] returns = new Object[st.length + in.length];
		for (int i = 0; i < st.length; i++)
		{
			returns[i] = st[i];
		}
		for (int i = 0; i < in.length; i++)
		{
			returns[st.length + i] = in[i];
		}
		return returns;
	}
	
	/**
	 * Run all <b>static</b> methods registered, annotated with @TriggerOn(key), with the given arguments.
	 * <p>Args can be null when running a method with no parameters.
	 * @param key
	 * @param args
	 * @return Object[] corresponding to all returns of the methods run, according to {@linkplain Method#invoke(Object, Object...)}.
	 * @author SerpentDagger
	 */
	public static Object[] triggerStatic(String key, Object... args)
	{
		HashMap<Object, ? extends Annotation> map = AnnotationHandler.fromDomain(TriggerOn.class);
		Object[] returns = new Object[map.size()];
		AtomicInteger i = new AtomicInteger(0);
		map.forEach((m, a) ->
		{
			Method md = (Method) m;
			TriggerOn t = (TriggerOn) a;
			if (t.value().equals(key) && classMap.get(md.getClass()).get(key))
			{
				try
				{
					returns[i.get()] = md.invoke(null, args);
					i.incrementAndGet();
				}
				catch (NullPointerException e)
				{
					System.out.println("Error while triggering event \"" + key + "\" on method \"" + md.getName() + "\", probably the result of annotating a non-static method.");
					e.printStackTrace();
				}
				catch (Throwable e)
				{
					System.out.println("Error while triggering event: " + key + "\" on method \"" + md.getName() + "\".");
					e.printStackTrace();
				}
			}
		});
		return returns;
	}
	
	/**
	 * Run all <b>non-static</b> methods registered, annotated with @TriggerOn(key), with the given arguments.
	 * <p>Args can be null when running a method with no parameters.
	 * @param key
	 * @param args
	 * @return Object[] corresponding to all returns of the methods run, according to {@linkplain Method#invoke(Object, Object...)}.
	 * @author SerpentDagger
	 */
	public static Object[] triggerInstance(String key, Object... args)
	{
		HashMap<Method, Object> methods = methodMap.get(key);
		if (methods == null)
			return null;
		Object[] returns = new Object[methods.size()];
		AtomicInteger i = new AtomicInteger(0);
		methods.forEach((md, ob) ->
		{
			try
			{
				returns[i.get()] = md.invoke(ob, args);
				i.incrementAndGet();
			}
			catch (Throwable e)
			{
				System.out.println("Error while triggering event: " + key + "\" on method \"" + md.getName() + "\".");
				e.printStackTrace();
			}
		});
		return returns;
	}
	
	////////////////////
	
	/**
	 * Container class to store returns from static vs. instance event calls.
	 * @author SerpentDagger
	 */
	public static class EventReturn
	{
		public final Object[] staticReturns;
		public final Object[] instanceReturns;
		
		EventReturn(Object[] st, Object[] in)
		{
			staticReturns = st;
			instanceReturns = in;
		}
	}

	/////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Get the {@linkplain Event#methodMap}. You should probably just use the registry methods.
	 * @return methodMap
	 * @author SerpentDagger
	 */
	public static HashMap<String, HashMap<Method, Object>> getMethodMap()
	{
		return methodMap;
	}

	/**
	 * Get the {@linkplain Event#classMap}. You should probably just use the registry methods.
	 * @return classMap
	 * @author SerpentDagger
	 */
	public static HashMap<Class<?>, HashMap<String, Boolean>> getClassMap()
	{
		return classMap;
	}

	/**
	 * Set the {@linkplain Event#methodMap}. You should probably just use the registry methods.
	 * @param methodMap methodMap to set
	 * @author SerpentDagger
	 */
	public static void setMethodMap(HashMap<String, HashMap<Method, Object>> methodMap)
	{
		Event.methodMap = methodMap;
	}

	/**
	 * Set the {@linkplain Event#classMap}. You should probably just use the registry methods.
	 * @param classMap classMap to set
	 * @author SerpentDagger
	 */
	public static void setClassMap(HashMap<Class<?>, HashMap<String, Boolean>> classMap)
	{
		Event.classMap = classMap;
	}
}
