package graphingcalculator3d.common.util.nbthandler;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NBTHandlerBoolean
{
	private final boolean defaultVal;
	private String name;
	
	public NBTHandlerBoolean(String nameIn, boolean defaultValIn)
	{
		name = nameIn;
		defaultVal = defaultValIn;
	}
	
	public boolean defaultVal()
	{
		return defaultVal;
	}
	
	public String getName()
	{
		return name;
	}
	
	public NBTTagCompound setValue(NBTTagCompound compound, boolean value)
	{
		compound.setBoolean(name, value);
		return compound;
	}
	
	public boolean getValue(NBTTagCompound compound)
	{
		return (compound.hasKey(name)) ? compound.getBoolean(name) : defaultVal;
	}
	
	public boolean getValueFromTile(TileEntity tile)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (tile != null) { tile.writeToNBT(compound); }
		return getValue(compound);
	}
	
	public void setValueOfTile(TileEntity tile, boolean value)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (tile == null) { return; }
		tile.writeToNBT(compound);
		setValue(compound, value);
		tile.readFromNBT(compound);
	}
	
	public boolean getValueFromItemStack(ItemStack stack)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (stack.hasTagCompound()) { compound = stack.getTagCompound(); }
		return getValue(compound);
	}
	
	public void setValueOfItemStack(ItemStack stack, boolean value)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (stack.hasTagCompound()) { compound = stack.getTagCompound(); }
		setValue(compound, value);
		stack.setTagCompound(compound);
	}
	
	public boolean getValueFromPos(BlockPos pos, World worldIn)
	{
		TileEntity tempTile = worldIn.getTileEntity(pos);
		return getValueFromTile(tempTile);
	}
	
	public void setValueOfPos(BlockPos pos, World worldIn, boolean value)
	{
		TileEntity tempTile = worldIn.getTileEntity(pos);
		setValueOfTile(tempTile, value);
	}
}
