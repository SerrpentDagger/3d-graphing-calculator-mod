package graphingcalculator3d.common.util.events.register;

import net.minecraftforge.common.MinecraftForge;

public class RegisterEventHandlers
{
	public static void registerEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new RegisterItems());
		MinecraftForge.EVENT_BUS.register(new RegisterModels());
		MinecraftForge.EVENT_BUS.register(new RegisterBlocks());
	}
}
