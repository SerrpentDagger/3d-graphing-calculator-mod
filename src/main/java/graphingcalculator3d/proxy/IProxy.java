package graphingcalculator3d.proxy;

import graphingcalculator3d.common.gameplay.tile.TileGCBase;
import graphingcalculator3d.common.util.networking.packets.PacketGC;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public interface IProxy
{
	public void preInit();
	public void init();
	public void postInit();
	
	public void handleGCPacket(PacketGC message, MessageContext ctx);
	
	public void sayToClient(String text, World world);
	public double[] getUV(ResourceLocation tex);
	public void openGuiGC(World worldIn, TileGCBase tile);
	public void deleteVertexData(TileGCBase te);
}
