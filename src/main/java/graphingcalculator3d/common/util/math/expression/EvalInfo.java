package graphingcalculator3d.common.util.math.expression;

import graphingcalculator3d.common.util.math.expression.Expression.Evaluation;

public class EvalInfo
{
	public final String string;
	public final int[] rgb;
	public final int slots;
	public final Evaluation eval;
	public final String tooltip;
	
	public EvalInfo(int slots, Evaluation eval, String forShow, int[] rgb, String tooltip)
	{
		string = forShow;
		this.rgb = rgb;
		this.slots = slots;
		this.eval = eval;
		this.tooltip = tooltip;
	}
	
	public EvalInfo(int slots, Evaluation eval, String forShow, int r, int g, int b, String tooltip)
	{
		this(slots, eval, forShow, new int[] { r, g, b }, tooltip);
	}
}
