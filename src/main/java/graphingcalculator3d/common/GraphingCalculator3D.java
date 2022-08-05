package graphingcalculator3d.common;

import graphingcalculator3d.common.computercraft.CCDep;
import graphingcalculator3d.common.computercraft.CCDepDummy;
import graphingcalculator3d.common.gameplay.creativetabs.TabGC;
import graphingcalculator3d.common.gameplay.recipes.Recipes;
import graphingcalculator3d.common.util.events.ConfigSync;
import graphingcalculator3d.common.util.events.GCEvents;
import graphingcalculator3d.common.util.events.register.RegisterEventHandlers;
import graphingcalculator3d.common.util.events.register.RegisterTileEntities;
import graphingcalculator3d.common.util.math.expression.Evaluations;
import graphingcalculator3d.common.util.networking.GCPacketHandler;
import graphingcalculator3d.proxy.IProxy;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = GraphingCalculator3D.MODID, name = GraphingCalculator3D.NAME, version = GraphingCalculator3D.VERSION, useMetadata = true)
public class GraphingCalculator3D
{
	////////////////////////////////Mod Variables
	
	public static final String MODID = "graphingcalculator3d";
	public static final String NAME = "3D Graphing Calculators";
	public static final String VERSION = "1.9.1";
	
	/////////////////////////////////////////////////
	
	@SidedProxy(clientSide = "graphingcalculator3d.proxy.ClientProxy",
			serverSide = "graphingcalculator3d.proxy.ServerProxy")
	public static IProxy proxy;
	
	@Instance(MODID)
	public static GraphingCalculator3D instance;
	public static CCDep ccDep;

	/////////////////////////////////////////////////
	
	public static final TabGC GC_TAB_MAIN = new TabGC(MODID);
	
	public static ConfigSync CONFIG_SYNC;
	
	////////////////////
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		RegisterEventHandlers.registerEventHandlers();
		RegisterTileEntities.registerTiles();
		GCPacketHandler.registerPackets();
		GCEvents.setupDomain();
		CONFIG_SYNC = new ConfigSync();
		try
		{
			Evaluations.loadMappings();
		}
		catch (IllegalArgumentException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		proxy.preInit();
		
		// CC:Tweaked dependency instantiation
		if (Loader.isModLoaded("computercraft"))
			try
			{
				ccDep = Class.forName("graphingcalculator3d.common.computercraft.CCDepImpl").asSubclass(CCDep.class).newInstance();
			}
			catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		else
			ccDep = new CCDepDummy();
		
		ccDep.preInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
		Recipes.loadRecipes();
		ccDep.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit();
		ccDep.postInit();
	}
}
