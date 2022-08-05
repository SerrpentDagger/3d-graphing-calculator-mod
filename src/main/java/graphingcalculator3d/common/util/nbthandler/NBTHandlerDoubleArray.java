package graphingcalculator3d.common.util.nbthandler;

import graphingcalculator3d.common.util.arrays.Arrays;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NBTHandlerDoubleArray
{
	private final double[] defaultVal;
	private String name;
	
	public NBTHandlerDoubleArray(String nameIn, double[] defaultValIn)
	{
		name = nameIn;
		defaultVal = defaultValIn;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double[] defaultVal()
	{
		return Arrays.copyOf(defaultVal, defaultVal.length);
	}
	
	public NBTTagCompound setValue(NBTTagCompound compound, double[] value)
	{
		for (int i = 0; i < value.length; i++)
		{
			compound.setDouble(name + "_" + i, value[i]);
		}
		return compound;
	}
	
	public double[] getValue(NBTTagCompound compound, int length)
	{
		double[] temp = new double[length];
		for (int i = 0; i < length; i++)
		{
			temp[i] = compound.hasKey(name + "_" + i) ? compound.getDouble(name + "_" + i) : defaultVal.length > i ? defaultVal[i] : defaultVal[0];
		}
		return temp;
	}
	
	public double[] getValueFromTile(TileEntity tile, int length)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (tile != null) { tile.writeToNBT(compound); }
		return getValue(compound, length);
	}
	
	public void setValueOfTile(TileEntity tile, double[] value)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (tile == null) { return; }
		tile.writeToNBT(compound);
		setValue(compound, value);
		tile.readFromNBT(compound);
	}
	
	public double[] getValueFromItemStack(ItemStack stack, int length)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (stack.hasTagCompound()) { compound = stack.getTagCompound(); }
		return getValue(compound, length);
	}
	
	public void setValueOfItemStack(ItemStack stack, double[] value)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (stack.hasTagCompound()) { compound = stack.getTagCompound(); }
		setValue(compound, value);
		stack.setTagCompound(compound);
	}
	
	public double[] getValueFromPos(BlockPos pos, World worldIn, int length)
	{
		TileEntity tempTile = worldIn.getTileEntity(pos);
		return getValueFromTile(tempTile, length);
	}
	
	public void setValueOfPos(BlockPos pos, World worldIn, double[] value)
	{
		TileEntity tempTile = worldIn.getTileEntity(pos);
		setValueOfTile(tempTile, value);
	}
}
