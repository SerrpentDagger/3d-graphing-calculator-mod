package graphingcalculator3d.common.util.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to mark methods to trigger when a given event is fired. Requires an event key.
 * <p>Used alongside {@linkplain Event}
 * @author SerpentDagger
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TriggerOn
{
	public String value();
}
