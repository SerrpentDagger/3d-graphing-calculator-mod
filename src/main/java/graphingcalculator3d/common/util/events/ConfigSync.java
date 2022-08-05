package graphingcalculator3d.common.util.events;

import graphingcalculator3d.common.GraphingCalculator3D;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.PostConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigSync
{
	public ConfigSync()
	{
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void configChange(PostConfigChangedEvent event)
	{
		if (event.getModID().equals(GraphingCalculator3D.MODID))
		{
			ConfigManager.sync(GraphingCalculator3D.MODID, Type.INSTANCE);
			Event.triggerER(GCEvents.GC_CONFIG, (Object[]) null);
		}
	}
}
