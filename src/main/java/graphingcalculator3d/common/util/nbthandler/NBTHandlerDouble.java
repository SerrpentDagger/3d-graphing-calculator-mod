package graphingcalculator3d.common.util.nbthandler;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NBTHandlerDouble
{
	private final double defaultVal;
	private String name;
	
	public NBTHandlerDouble(String nameIn, double defaultValIn)
	{
		name = nameIn;
		defaultVal = defaultValIn;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double defaultVal()
	{
		return defaultVal;
	}
	
	public NBTTagCompound setValue(NBTTagCompound compound, double value)
	{
		compound.setDouble(name, value);
		return compound;
	}
	
	public double getValue(NBTTagCompound compound)
	{
		return (compound.hasKey(name)) ? compound.getDouble(name) : defaultVal;
	}
	
	public double getValueFromTile(TileEntity tile)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (tile != null) { tile.writeToNBT(compound); }
		return getValue(compound);
	}
	
	public void setValueOfTile(TileEntity tile, double value)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (tile == null) { return; }
		tile.writeToNBT(compound);
		setValue(compound, value);
		tile.readFromNBT(compound);
	}
	
	public double getValueFromItemStack(ItemStack stack)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (stack.hasTagCompound()) { compound = stack.getTagCompound(); }
		return getValue(compound);
	}
	
	public void setValueOfItemStack(ItemStack stack, double value)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (stack.hasTagCompound()) { compound = stack.getTagCompound(); }
		setValue(compound, value);
		stack.setTagCompound(compound);
	}
	
	public double getValueFromPos(BlockPos pos, World worldIn)
	{
		TileEntity tempTile = worldIn.getTileEntity(pos);
		return getValueFromTile(tempTile);
	}
	
	public void setValueOfPos(BlockPos pos, World worldIn, double value)
	{
		TileEntity tempTile = worldIn.getTileEntity(pos);
		setValueOfTile(tempTile, value);
	}
}
