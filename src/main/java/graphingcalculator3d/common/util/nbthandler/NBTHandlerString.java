package graphingcalculator3d.common.util.nbthandler;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NBTHandlerString
{
	private final String defaultVal;
	private String name;
	
	public NBTHandlerString(String nameIn, String defaultValIn)
	{
		name = nameIn;
		defaultVal = defaultValIn;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String defaultVal()
	{
		return defaultVal;
	}
	
	public NBTTagCompound setValue(NBTTagCompound compound, String value)
	{
		compound.setString(name, value);
		return compound;
	}
	
	public String getValue(NBTTagCompound compound)
	{
		return (compound.hasKey(name)) ? compound.getString(name) : defaultVal;
	}
	
	public String getValueFromTile(TileEntity tile)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (tile != null) { tile.writeToNBT(compound); }
		return getValue(compound);
	}
	
	public void setValueOfTile(TileEntity tile, String value)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (tile == null) { return; }
		tile.writeToNBT(compound);
		setValue(compound, value);
		tile.readFromNBT(compound);
	}
	
	public String getValueFromItemStack(ItemStack stack)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (stack.hasTagCompound()) { compound = stack.getTagCompound(); }
		return getValue(compound);
	}
	
	public void setValueOfItemStack(ItemStack stack, String value)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (stack.hasTagCompound()) { compound = stack.getTagCompound(); }
		setValue(compound, value);
		stack.setTagCompound(compound);
	}
	
	public String getValueFromPos(BlockPos pos, World worldIn)
	{
		TileEntity tempTile = worldIn.getTileEntity(pos);
		return getValueFromTile(tempTile);
	}
	
	public void setValueOfPos(BlockPos pos, World worldIn, String value)
	{
		TileEntity tempTile = worldIn.getTileEntity(pos);
		setValueOfTile(tempTile, value);
	}
}
