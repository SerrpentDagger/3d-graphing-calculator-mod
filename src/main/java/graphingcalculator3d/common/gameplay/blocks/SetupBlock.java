package graphingcalculator3d.common.gameplay.blocks;

import graphingcalculator3d.common.GraphingCalculator3D;
import graphingcalculator3d.common.util.Utilities;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class SetupBlock
{	
	public static final CreativeTabs DEFAULT_TAB = GraphingCalculator3D.GC_TAB_MAIN;
	
	public static Block basicBlock(Block block, String name)
	{
		setNameAndTab(block, name);
		//generateDefaultJsons(name);
		return block;
	}
	
	public static Block basicBlockBottomTop(Block block, String name)
	{
		setNameAndTab(block, name);
		//generateBottomTopJsons(name);
		return block;
	}
	
	public static Block setName(Block block, String name)
	{
		block.setRegistryName(name).setUnlocalizedName(block.getRegistryName().toString());
		return block;
	}
	
	public static Block setNameAndTab(Block block, String name)
	{
		setName(block, name);
		return block.setCreativeTab(DEFAULT_TAB);
	}
	
	public static void generateDefaultJsons(String name)
	{
		Utilities.generateJson(name, "blockstates", "blockstate_simple", name, null, null);
		Utilities.generateJson(name, "models\\block", "block_model_simple", name, null, null);
		Utilities.generateJson(name, "models\\item", "item_block_simple", name, null, null);
	}
	
	public static void generateBottomTopJsons(String name)
	{
		Utilities.generateJson(name, "models\\block", "block_model_bottom_top", name + "_bottom", name + "_top", name + "_side");
		//generateDefaultJsons(name);
	}
	
	public static void generateCalc(String name)
	{
		Utilities.generateJson(name, "models\\block", "block_model_calc", name, null, null);
		//generateDefaultJsons(name);
	}
	
	public static Block calculator(Block block, String name)
	{
		//generateCalc(name);
		block.setHardness(1.5f).setResistance(6000).setHarvestLevel("pickaxe", 1);
		return basicBlock(block, name);
	}
}
