package graphingcalculator3d.common.gameplay.items;

import java.util.List;

import graphingcalculator3d.common.gameplay.tile.TileGCBase;
import graphingcalculator3d.common.util.nbthandler.GCNBT;
import graphingcalculator3d.common.util.networking.GCPacketHandler;
import graphingcalculator3d.common.util.networking.packets.PacketGC;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMemoryCard extends Item
{
	public static final String WRITTEN = "Carrying Block Data.";
	public static final String EMPTY = "Empty";
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		TileEntity tile = worldIn.getTileEntity(pos);
		TileGCBase tileGC;
		ItemStack stack = player.getHeldItem(hand);
		NBTTagCompound compound = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
		
		if (tile instanceof TileGCBase)
			tileGC = (TileGCBase) tile;
		else
			return EnumActionResult.PASS;
		
		if (player.isSneaking())
		{
			compound = tileGC.writeRelevant(compound);
			GCNBT.MEMORY_TIP.setValue(compound, WRITTEN);
			stack.setTagCompound(compound);
			String tempF = (tileGC.getFunction() == null) ? this.getDefaultInstance().getDisplayName() : tileGC.getFunction().writeToString();
			stack.setStackDisplayName(tempF);
		}
		else
		{
			tileGC.readRelevant(compound);
			if (worldIn.isRemote)
				GCPacketHandler.GRAPH_SYNC.sendToServer(new PacketGC(tileGC));
		}
		return EnumActionResult.SUCCESS;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(GCNBT.MEMORY_TIP.getValueFromItemStack(stack));
	}
}
