package graphingcalculator3d.common.gameplay.blocks;

import java.util.List;

import graphingcalculator3d.common.GraphingCalculator3D;
import graphingcalculator3d.common.gameplay.items.GCItems;
import graphingcalculator3d.common.gameplay.tile.TileGCBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGC extends Block
{
	public final Class<? extends TileGCBase> TEGC;
	
	public BlockGC(Class<? extends TileGCBase> tileClass, Material materialIn)
	{
		super(materialIn);
		TEGC = tileClass;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		
		tooltip.add(BlockToolTips.graphingCalculator3D);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState state)
	{
		try
		{
			return TEGC.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			TileGCBase temp = new TileGCBase();
			temp.setErrored(true, "" + (e instanceof InstantiationException ? "InstantiationException" : "IllegalAccessException") + " caught."
					+ " This is a bug, and the TileEntity won't work as expected. See stacktrace for more information.");
			e.printStackTrace();
			return temp;
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX,
			float hitY, float hitZ)
	{
		if (playerIn.getHeldItem(hand).getItem() == GCItems.item_memory_card)
			return false;
		TileEntity tile = worldIn.getTileEntity(pos);
		if (!(tile instanceof TileGCBase))
			return true;
		GraphingCalculator3D.proxy.openGuiGC(worldIn, (TileGCBase) tile);
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.SOLID;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return true;
	}
}
