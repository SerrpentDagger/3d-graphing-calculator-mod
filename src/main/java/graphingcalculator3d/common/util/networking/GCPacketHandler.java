package graphingcalculator3d.common.util.networking;

import graphingcalculator3d.common.util.networking.packets.PacketGC;
import graphingcalculator3d.common.util.networking.packets.PacketGC.PacketGCHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class GCPacketHandler
{
	public static final SimpleNetworkWrapper GRAPH_SYNC = NetworkRegistry.INSTANCE.newSimpleChannel("gc_3d_graph_sync");
	
	///////////////////////
	
	static int dsc = 0;
	public static void registerPackets()
	{
		GRAPH_SYNC.registerMessage(PacketGCHandler.class, PacketGC.class, dsc++, Side.CLIENT);
		GRAPH_SYNC.registerMessage(PacketGCHandler.class, PacketGC.class, dsc++, Side.SERVER);
	}
}
