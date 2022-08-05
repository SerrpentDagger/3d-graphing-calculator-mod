package graphingcalculator3d.client.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class VisibleString extends VisibleBase
{
	public static final int INVALID_WRAP = -1;
	
	public String string;
	public int color;
	
	FontRenderer fontRenderer;
	
	public VisibleString(String str)
	{
		string = str;
		visible = true;
		
		xPos = 0;
		yPos = 0;
		color = 0;
		width = INVALID_WRAP;
		fontRenderer = mc.fontRenderer;
	}
	
	public VisibleString(String str, int index)
	{
		this(str);
		setIndex(index);
	}
	
	public VisibleString(String str, boolean vis)
	{
		this(str);
		visible = vis;
	}
	
	public VisibleString(String str, boolean vis, int xPosIn, int yPosIn)
	{
		this(str, vis);
		xPos = xPosIn;
		yPos = yPosIn;
	}
	
	public VisibleString(String str, boolean vis, int xPosIn, int yPosIn, int index)
	{
		this(str, vis, xPosIn, yPosIn);
		setIndex(index);
	}
	
	public VisibleString(String str, boolean vis, int xPosIn, int yPosIn, float wrapIn)
	{
		this(str, vis, xPosIn, yPosIn);
		width = (int) wrapIn;
	}
	
	public VisibleString(String str, boolean vis, int xPosIn, int yPosIn, int wrapIn, int textColor)
	{
		this(str, vis, xPosIn, yPosIn, wrapIn);
		color = textColor;
	}
	
	@Override
	public void draw()
	{
		if (!visible) { return; }
		if (string.isEmpty()) { return; }
		if (fontRenderer == null) { return; }
		
		if (width != INVALID_WRAP)
		{
			fontRenderer.drawSplitString(string, xPos, yPos, width, color);
		}
		else
		{
			fontRenderer.drawString(string, xPos, yPos, color, false);
		}
	}
	
	///////////
	
	@Override
	public int getPanelHeight()
	{
		return fontRenderer.getWordWrappedHeight(string, width);
	}
	
	@Override
	public VisibleString indented(boolean indented)
	{
		return (VisibleString) super.indented(indented);
	}
	
	@Override
	public VisibleString breaksPage(boolean breaks)
	{
		return (VisibleString) super.breaksPage(breaks);
	}
}
