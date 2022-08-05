package graphingcalculator3d.common.util.events.register;

import graphingcalculator3d.common.gameplay.items.GCItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegisterModels
{
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event)
	{
		for (Item item : GCItems.gcItemList)
		{
			ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), "Inventory");
			ModelLoader.setCustomModelResourceLocation(item, 0, location);
		}
		
		System.out.println("GraphingCalculator3D item models registered.");
	}
}
