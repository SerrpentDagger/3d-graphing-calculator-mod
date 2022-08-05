package graphingcalculator3d.common.gameplay.creativetabs;

import graphingcalculator3d.common.gameplay.blocks.GCBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabGC extends CreativeTabs
{
	public TabGC(String label)
	{
		super(label);
	}
	
	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(GCBlocks.block_gc_cartesian);
	}
}
