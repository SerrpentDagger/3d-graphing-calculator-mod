package graphingcalculator3d.common.util.math.expression;

import org.apache.commons.lang3.ArrayUtils;

import graphingcalculator3d.common.util.config.ConfigVars;

public class Expression
{
	public static final char END = ")".charAt(0);
	public static final char START = "(".charAt(0);
	public static final char V_END = "]".charAt(0);
	public static final char V_START = "[".charAt(0);
	public static final char SPACER = ",".charAt(0);
	private static int parseIndex = 0;
	private static boolean parseIsVariable = true;
	
	public Expression[] slots;
	public double[] vals = new double[] { 0 };
	public Evaluation evaluation;
	public boolean isValue = false;
	public boolean isVariable = false;
	private String valueValue = "";
	private String name = "";
	
	public Expression(Evaluation eval, Expression[] slotsIn)
	{
		evaluation = eval;
		slots = slotsIn;
		vals = new double[slotsIn.length];
		if (slots.length > 0)
		{
			isValue = false;
			isVariable = false;
		}
		else
		{
			isValue = true;
			isVariable = true;
			vals = new double[] { 0 };
		}
	}

	public Expression(Evaluation eval, int slotNum)
	{
		this(eval, new Expression[slotNum]);
	}
	
	public Expression(String valueValue, boolean isVar)
	{
		this(Evaluations.VAL, 0);
		setValueValue(valueValue, isVar);
	}
	
	/////////////
	
	public void setVals() throws InfiniteCalculationsException
	{
		for (int i = 0; i < slots.length; i++)
		{
			if (slots[i] == null) { continue; }
			vals[i] = slots[i].eval();
		}
	}
	
	public double eval() throws InfiniteCalculationsException
	{
		if (evaluation instanceof Series)
			return computeSeries(this);
		setVals();
		if (isValue)
			vals[0] = evaluation.evaluate(vals);
		return isValue ? vals[0] : evaluation.evaluate(vals);
	}
	
	@FunctionalInterface
	public interface Evaluation
	{
		double evaluate(double[] values);
	}
	
	@FunctionalInterface
	public interface Series extends Evaluation
	{
		double evaluate(double[] values);
	}
	
	/////////////Series
	
	public static double computeSeries(Expression series) throws InfiniteCalculationsException
	{
		if (ArrayUtils.contains(series.slots, null))
			return 0;
		
		Evaluation eval = series.evaluation;
		
		series.setVarsToDoub(series.slots[0].name, series.slots[0].eval());
		double n = series.slots[1].eval();
		double v;
		if (eval == Evaluations.MULT_SERIES || eval == Evaluations.DIVI_SERIES || eval == Evaluations.PWR_SERIES || eval == Evaluations.ROOT_SERIES)
			v = 1;
		else
			v = 0;
		double out = v;
		for (double i = series.slots[0].eval(); i <= n; i += 1)
		{
			series.setVarsToDoub(series.slots[0].name, i);
			n = series.slots[1].eval();
			series.setVarsToDoub(series.slots[1].name, n);
			
			v = series.slots[2].eval();
			out = eval.evaluate(new double[] { v, out });
			
			if (i > ConfigVars.GraphingConfigs.maxSeries)
				throw new InfiniteCalculationsException("Infinite calculations required.");
		}
		return out;
	}
	
	public static class InfiniteCalculationsException extends Exception
	{
		public InfiniteCalculationsException(String string)
		{
			super(string);
		}
		private static final long serialVersionUID = 2703800677404042653L;
	}
	
	/////////////Data Handling
	
	/////Write
	public CharSequence writeToChars()
	{
		String tempChars = "";
		tempChars = this.addToString(tempChars);
		CharSequence seq = tempChars;
		
		return seq;
	}
	
	public String writeToString()
	{
		String str = "";
		str = this.addToString(str);
		
		return str;
	}
	
	private String addToString(String str)
	{
		if (slots == null)
			return str;
		str += slots.length;
		str += Evaluations.stringMap.get(evaluation);
		str = addStart(str);
		for (int i = 0; i < slots.length; i++)
		{
			if (slots[i] != null)
				str = slots[i].addToString(str);
		}
		if (isValue)
		{
			str = addVStart(str);
			str += valueValue;
			str = addSpacer(str);
			str += isVariable ? 1 : 0;
			str = addVEnd(str);
		}
		str = addEnd(str);
		return str;
	}
	private String addEnd(String str)
	{
		return str + END;
	}
	private String addStart(String str)
	{
		return str + START;
	}
	private String addVEnd(String str)
	{
		return str + V_END;
	}
	private String addVStart(String str)
	{
		return str + V_START;
	}
	private String addSpacer(String str)
	{
		return str + SPACER;
	}
	
	/////Read
	
	public static Expression parseFromChars(CharSequence chars)
	{
		String str = (String) chars;
		
		Expression expr = null;
		try
		{			
			expr = readExpr(str, 0);
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		
		return expr;
	}
	
	public static Expression parseFromString(String str)
	{
		Expression expr = null;
		try
		{
			expr = readExpr(str, 0);
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		
		return expr;
	}
	
	private static Expression readExpr(String str, int index) throws NumberFormatException
	{
		Expression expr = null;
		String key = "";
		int subExprCount = 0;
		
		//Search through string
		for (int i = index; i < str.length(); i++)
		{
			if (i == index)
			{
				//get # of sub expressions
				String chr = Character.toString(str.charAt(i));
				subExprCount = Integer.parseInt(chr);
			}
			else if (str.charAt(i) != START)
			{
				//Add character to evaluation name
				key += str.charAt(i);
			}
			else
			{
				//get and use evaluation from key.
				Evaluation eval = Evaluations.evalMap.get(key);
				expr = new Expression(eval, subExprCount);
				parseIndex = i + 1;
				key = "";
				break;
			}
		}
		
		if (expr == null) { return null; }
		//For each sub expression
		for (int i = 0; i < expr.slots.length; i++)
		{
			expr.slots[i] = readExpr(str, parseIndex);
		}
		if (expr.isValue)
		{
			expr.valueValue = readVal(str, parseIndex);
			expr.isVariable = parseIsVariable;
			if (!expr.isVariable) { expr.vals[0] = Double.parseDouble(expr.valueValue); }
		}
		parseIndex += 1;
		return expr;
	}
	private static String readVal(String str, int index)
	{
		String val = "";
		char isVar;
		boolean spacer = false;
		int track = 0;
		for (int i = index; i < str.length(); i++)
		{
			if (str.charAt(i) == V_START)
			{
				track++;
			}
			else if (str.charAt(i) != SPACER && !spacer)
			{
				val += str.charAt(i);
			}
			else if (str.charAt(i) == SPACER)
			{
				spacer = true;
			}
			else if (str.charAt(i) != V_END && spacer)
			{
				isVar = str.charAt(i);
				parseIsVariable = (isVar == "1".charAt(0));
			}
			else if (str.charAt(i) == V_END)
			{
				track--;
			}
			if (track == 0)
			{
				parseIndex = i + 1;
				break;
			}
		}
		return val;
	}
	
	public static Expression cloneExpression(Expression expr)
	{
		Expression newExpr = new Expression(expr.evaluation, expr.slots.length);
		for (int i = 0; i < expr.slots.length; i++)
		{
			if (expr.slots[i] != null)
				newExpr.slots[i] = Expression.cloneExpression(expr.slots[i]);
		}
		newExpr.isValue = expr.isValue;
		newExpr.isVariable = expr.isVariable;
		newExpr.valueValue = expr.valueValue;
		return newExpr;
	}
	
	////////////////////////////Variable Substitution
	
	public int setVarsToDoub(String var, double val)
	{
		var = var.toLowerCase();
		int replaced = 0;
		for (int i = 0; i < slots.length; i++)
		{
			if (slots[i] == null) { continue; }
			if (slots[i].valueValue != null && slots[i].isValue && slots[i].valueValue.equals(var))
			{
				slots[i].vals[0] = val;
				replaced++;
			}
			else
			{
				replaced += slots[i].setVarsToDoub(var, val);
			}
		}
		if (isValue && valueValue.equals(var))
		{
			vals[0] = val;
			replaced++;
		}
		return replaced;
	}
	
	/**
	 * @return The number of names that were indexed, NOT the highest resulting index.
	 */
	public int indexNames(String var, int firstInd)
	{
		var = var.toLowerCase();
		int indexed = 0;
		for (int i = 0; i < slots.length; i++)
		{
			if (slots[i] == null) { continue; }
			if (slots[i].name.equals(var))
			{
				slots[i].name = var + (indexed + firstInd);
				indexed++;
			}
			else
			{
				indexed += slots[i].indexNames(var, indexed + firstInd);
			}
		}
		return indexed;
	}
	
	public void setSeriesNames()
	{
		if (evaluation instanceof Series)
		{
			if (slots[0] != null)
				slots[0].name = "i";
			if (slots[1] != null)
				slots[1].name = "n";
		}
		for (int i = 0; i < slots.length; i++)
		{
			if (slots[i] == null)
				continue;
			slots[i].setSeriesNames();
		}
	}
	
	public void prepareSeries()
	{
		setSeriesNames();
		indexNames("i", 0);
		indexNames("n", 0);
	}
	
	/////////////////////////////Setters / Getters
	
	public void setValueValue(String value, boolean isVariable)
	{
		valueValue = value.toLowerCase();
		this.isVariable = isVariable;
		if (!isVariable)
		{
			vals[0] = Double.parseDouble(value);
		}
	}
	/*public void setValueValue(String value)
	{
		valueValue = value;
	}*/
	public String getValueValue()
	{
		return valueValue;
	}
	
	public void setName(String nameIn)
	{
		name = nameIn;
	}
	
	public String getName()
	{
		return name;
	}
}
