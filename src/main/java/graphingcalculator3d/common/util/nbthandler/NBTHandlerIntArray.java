package graphingcalculator3d.common.util.nbthandler;

import java.util.Arrays;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NBTHandlerIntArray
{
	private final int[] defaultVal;
	private String name;
	
	public NBTHandlerIntArray(String nameIn, int[] defaultValIn)
	{
		name = nameIn;
		defaultVal = defaultValIn;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int[] defaultVal()
	{
		return Arrays.copyOf(defaultVal, defaultVal.length);
	}
	
	public NBTTagCompound setValue(NBTTagCompound compound, int[] value)
	{
		compound.setIntArray(name, value);
		return compound;
	}
	
	public int[] getValue(NBTTagCompound compound)
	{
		return (compound.hasKey(name)) ? compound.getIntArray(name) : Arrays.copyOf(defaultVal, defaultVal.length);
	}
	
	public int[] getValueFromTile(TileEntity tile)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (tile != null) { tile.writeToNBT(compound); }
		return getValue(compound);
	}
	
	public void setValueOfTile(TileEntity tile, int[] value)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (tile == null) { return; }
		tile.writeToNBT(compound);
		setValue(compound, value);
		tile.readFromNBT(compound);
	}
	
	public int[] getValueFromItemStack(ItemStack stack)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (stack.hasTagCompound()) { compound = stack.getTagCompound(); }
		return getValue(compound);
	}
	
	public void setValueOfItemStack(ItemStack stack, int[] value)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (stack.hasTagCompound()) { compound = stack.getTagCompound(); }
		setValue(compound, value);
		stack.setTagCompound(compound);
	}
	
	public int[] getValueFromPos(BlockPos pos, World worldIn)
	{
		TileEntity tempTile = worldIn.getTileEntity(pos);
		return getValueFromTile(tempTile);
	}
	
	public void setValueOfPos(BlockPos pos, World worldIn, int[] value)
	{
		TileEntity tempTile = worldIn.getTileEntity(pos);
		setValueOfTile(tempTile, value);
	}
}
