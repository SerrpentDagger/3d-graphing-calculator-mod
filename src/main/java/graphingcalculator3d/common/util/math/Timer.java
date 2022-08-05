package graphingcalculator3d.common.util.math;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 
 * High-resolution timer utility for easier performance tests.
 * 
 * @author SerpentDagger
 */
public class Timer
{
	private static HashMap<String, double[]> saved = new HashMap<String, double[]>();
	
	/////////////////////////////////////////
	
	double[] working;
	private TimeUnit unit = TimeUnit.SECONDS;
	int index = 0;
	int slots;
	
	public Timer(int slots)
	{
		working = new double[slots];
		this.slots = slots;
	}
	
	public Timer()
	{
		working = new double[10];
	}
	
	public void mark()
	{
		if (index >= working.length)
			working = Arrays.copyOf(working, index + 10);
		working[index] = System.nanoTime();
		index++;
	}
	
	public void reset()
	{
		working = new double[slots];
		index = 0;
	}
	
	public void save(String key)
	{
		saved.put(key, Arrays.copyOf(working, working.length));
	}
	
	public void load(String key)
	{
		double[] getted = saved.get(key);
		working = Arrays.copyOf(getted, getted.length);
		slots = working.length;
		index = 0;
	}
	
	public double average()
	{
		double total = 0;
		for (int i = 0; i + 1 < index; i++)
		{
			total += working[i + 1] - working[i];
		}
		if (index > 1)
		{
			total /= index - 1;
			return toUnit(total);
		}
		else
			return 0;
	}
	
	public double total()
	{
		double total = 0;
		for (int i = 0; i + 1 < index; i++)
		{
			total += working[i + 1] - working[i];
		}
		if (index > 1)
			return toUnit(total);
		else
			return 0;
	}
	
	public double greatest()
	{
		double greatest = working[0];
		double diff = 0;
		for (int i = 0; i + 1 < index; i++)
		{
			diff = working[i + 1] - working[i];
			if (diff > greatest)
				greatest = diff;
		}
		if (index > 1)
			return toUnit(greatest);
		else
			return 0;
	}
	
	public double least()
	{
		double least = working[0];
		double diff = 0;
		for (int i = 0; i + 1 < index; i++)
		{
			diff = working[i + 1] - working[i];
			if (diff < least)
				least = diff;
		}
		if (index > 1)
			return toUnit(least);
		else
			return 0;
	}
	
	public double[] differences()
	{
		double[] diffs = new double[index - 1];
		for (int i = 0; i + 1 < index; i++)
		{
			diffs[i] = toUnit(working[i + 1] - working[i]);
		}
		return diffs;
	}
	
	public String dataString()
	{
		return "Unit: " + unit + "; Differences: " + differences().toString()
				+ "; Average: " + average() + "; Least: " + least() + "; Greatest: " + greatest() + "; Total: " + total();
	}
	
	public void printData()
	{
		System.out.println(dataString());
	}
	
	private double toUnit(double input)
	{
		switch (unit)
		{
			case HOURS :
				input /= 60d;
			case MINUTES :
				input /= 60d;
			case SECONDS :
				input /= 10d;
			case TENTHS :
				input /= 10d;
			case HUNDREDTHS :
				input /= 10d;
			case THOUSANDTHS :
				input /= 1000d;
			case MILIS :
				input /= 1000d;
			case NANOS :
				return input;
			default :
				return input;
		}
	}
	
	public enum TimeUnit
	{
		HOURS,
		MINUTES,
		SECONDS,
		TENTHS,
		HUNDREDTHS,
		THOUSANDTHS,
		MILIS,
		NANOS
	}
}
