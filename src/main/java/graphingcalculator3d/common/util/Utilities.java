package graphingcalculator3d.common.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

import graphingcalculator3d.common.util.json.JsonGeneratingStrings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public class Utilities
{
	static Random ran = new Random();
	public static final boolean GEN_DATA = false;
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	public static double particleSpeed()		//random particle speed
	{
		return ((ran.nextInt(50) - 25) / 25);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	
	public static void generateJson(String name, String pathPastAA, String jsonType, String texture1, String texture2, String texture3)
	{
		if (GEN_DATA)
		{
			String tempName = name + ".json";
			String workingDir = System.getProperty("user.dir");
			File tempFile = new File(workingDir);
			tempFile = tempFile.getParentFile().getParentFile();
			String aaPath = tempFile.getAbsolutePath() + "\\Workspace\\GraphingCalculator3D\\src\\main\\resources\\assets\\graphingcalculator3d\\" + pathPastAA + "\\" + tempName;
			try
			{
				tempFile = new File(aaPath);
				if (!tempFile.exists())
				{
					System.out.println("Generating JSON of type \"" + jsonType + "\" for \"" + name + "\" at path: " + tempFile.getAbsolutePath());
					tempFile.createNewFile();
					writeJson(tempFile, JsonGeneratingStrings.generateJson(jsonType, texture1, texture2, texture3));
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private static void writeJson(File tempFile, String jsonContents) throws IOException
	{
		RandomAccessFile file = new RandomAccessFile(tempFile, "rw");
		file.writeBytes(jsonContents);
		file.close();
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	public static int getItemBurnTime(ItemStack stack)		//returns burn time in ticks.
    {
        if (stack.isEmpty())
        {
            return 0;
        }
        else
        {
            int burnTime = net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack);
            if (burnTime >= 0) return burnTime;
            Item item = stack.getItem();

            if (item == Item.getItemFromBlock(Blocks.WOODEN_SLAB))
            {
                return 150;
            }
            else if (item == Item.getItemFromBlock(Blocks.WOOL))
            {
                return 100;
            }
            else if (item == Item.getItemFromBlock(Blocks.CARPET))
            {
                return 67;
            }
            else if (item == Item.getItemFromBlock(Blocks.LADDER))
            {
                return 300;
            }
            else if (item == Item.getItemFromBlock(Blocks.WOODEN_BUTTON))
            {
                return 100;
            }
            else if (Block.getBlockFromItem(item).getDefaultState().getMaterial() == Material.WOOD)
            {
                return 300;
            }
            else if (item == Item.getItemFromBlock(Blocks.COAL_BLOCK))
            {
                return 16000;
            }
            else if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName()))
            {
                return 200;
            }
            else if (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName()))
            {
                return 200;
            }
            else if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName()))
            {
                return 200;
            }
            else if (item == Items.STICK)
            {
                return 100;
            }
            else if (item != Items.BOW && item != Items.FISHING_ROD)
            {
                if (item == Items.SIGN)
                {
                    return 200;
                }
                else if (item == Items.COAL)
                {
                    return 1600;
                }
                else if (item == Items.LAVA_BUCKET)
                {
                    return 20000;
                }
                else if (item != Item.getItemFromBlock(Blocks.SAPLING) && item != Items.BOWL)
                {
                    if (item == Items.BLAZE_ROD)
                    {
                        return 2400;
                    }
                    else if (item instanceof ItemDoor && item != Items.IRON_DOOR)
                    {
                        return 200;
                    }
                    else
                    {
                        return item instanceof ItemBoat ? 400 : 0;
                    }
                }
                else
                {
                    return 100;
                }
            }
            else
            {
                return 300;
            }
        }
    }

	public int getBurnTimeOfEntity(Entity entityIn)
	{
		if (entityIn instanceof EntityItem)
		{
			ItemStack stack = ((EntityItem) entityIn).getItem();
			int stackSize = stack.getCount();
			int stackBurn = (getItemBurnTime(stack) * stackSize);
			
			return stackBurn;
		}
		return 0;
	}
	
}
