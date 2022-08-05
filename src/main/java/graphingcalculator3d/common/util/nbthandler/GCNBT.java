package graphingcalculator3d.common.util.nbthandler;

import graphingcalculator3d.common.GraphingCalculator3D;
import graphingcalculator3d.common.gameplay.items.ItemMemoryCard;
import graphingcalculator3d.common.util.arrays.Arrays;

public class GCNBT
{
	//////////////////////////////////////// Related Values
	
	public static final double[] GC_DOM_CIAN = new double[] { -8, 8 };
	public static final double[] GC_DOM_1_PI_POS = new double[] { 0, Math.PI + 0.05 };
	public static final double[] GC_DOM_2_PI_POS = new double[] { 0, 2 * Math.PI + 0.05 };
	public static final double[] GC_DOM_R = new double[] { 0, 8 };
	public static final double[] GC_DOM_1_PI_POS_EXCL = new double[] { 0.03, Math.PI - 0.03 };
	public static final double[] GC_DOM_2_PI_POS_EXCL = new double[] { 0.03, 2 * Math.PI - 0.03 };
	
	public static int[] copy(int[] array)
	{
		return Arrays.copyOf(array, array.length);
	}
	public static double[] copy(double[] array)
	{
		return Arrays.copyOf(array, array.length);
	}
	
	//////////////////////////////////////// Graphing Calculators
	
	public static final NBTHandlerString GC_FUNCTION = new NBTHandlerString(GraphingCalculator3D.MODID + "_stored_function", "");
	public static final NBTHandlerString GC_TEXTURE = new NBTHandlerString(GraphingCalculator3D.MODID + "_graph_texture",
			GraphingCalculator3D.MODID + ":blocks/block_mesh_grid");
	
	public static final NBTHandlerBoolean GC_CROP_TO_RANGE = new NBTHandlerBoolean(GraphingCalculator3D.MODID + "_graph_crop_to_range", true);
	public static final NBTHandlerBoolean GC_COLOR_SLOPE = new NBTHandlerBoolean(GraphingCalculator3D.MODID + "_graph_color_by_slope", false);
	public static final NBTHandlerBoolean GC_COLLISION = new NBTHandlerBoolean(GraphingCalculator3D.MODID + "_graph_collision_enabled", false);
	
	public static final NBTHandlerIntArray GC_RGBA = new NBTHandlerIntArray(GraphingCalculator3D.MODID + "_graph_rgba", new int[]
	{ 0, 200, 255, 255, 0 });
	
	public static final NBTHandlerInt GC_TILE_COUNT = new NBTHandlerInt(GraphingCalculator3D.MODID + "_graph_tile_count", 3);
	public static final NBTHandlerDouble GC_RESOLUTION = new NBTHandlerDouble(GraphingCalculator3D.MODID + "_graph_resolution", 10);
	public static final NBTHandlerDouble GC_DISC_THRESH = new NBTHandlerDouble(GraphingCalculator3D.MODID + "_graph_discontinuity_threshold", 50);
	public static final NBTHandlerDouble GC_AGG_DISC_THRESH = new NBTHandlerDouble(GraphingCalculator3D.MODID + "_aggressive_discontinuity_threshold", 0);
	
	public static final NBTHandlerDoubleArray GC_SCALE = new NBTHandlerDoubleArray(GraphingCalculator3D.MODID + "_graph_scale", new double[]
	{ 1, 1, 1 });
	public static final NBTHandlerDoubleArray GC_TRANSLATION = new NBTHandlerDoubleArray(GraphingCalculator3D.MODID + "_graph_translation",
			new double[]
			{ 0, 0, 0 });
	public static final NBTHandlerDoubleArray GC_ROTATION = new NBTHandlerDoubleArray(GraphingCalculator3D.MODID + "_graph_rotation", new double[]
	{ 0, 0, 0 });
	public static final NBTHandlerDoubleArray GC_DOMAIN_A = new NBTHandlerDoubleArray(GraphingCalculator3D.MODID + "_graph_domain_x", new double[]
	{ -2 * Math.PI, 2 * Math.PI });
	public static final NBTHandlerDoubleArray GC_RANGE = new NBTHandlerDoubleArray(GraphingCalculator3D.MODID + "_graph_range", new double[]
	{ -100, 100 });
	public static final NBTHandlerDoubleArray GC_DOMAIN_B = new NBTHandlerDoubleArray(GraphingCalculator3D.MODID + "_graph_domain_z", new double[]
	{ -2 * Math.PI, 2 * Math.PI });
	
	////////////////////////////////////////Memory card
	
	public static final NBTHandlerString MEMORY_TIP = new NBTHandlerString(GraphingCalculator3D.MODID + "_memory_tip", ItemMemoryCard.EMPTY);
}
