package graphingcalculator3d.client.gui;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SetupGui
{
	public static int centerVal(int negBound, int posBound, int size)
	{
		return negBound + (((posBound - negBound) - size) / 2);
	}
}
