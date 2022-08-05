package graphingcalculator3d.proxy;


import graphingcalculator3d.client.FastTESRGC;
import graphingcalculator3d.client.gui.GuiGC;
import graphingcalculator3d.common.gameplay.tile.TileGCBase;
import graphingcalculator3d.common.util.math.expression.Expression;
import graphingcalculator3d.common.util.networking.packets.PacketGC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy implements IProxy
{
	Minecraft mc = Minecraft.getMinecraft();
	TextureAtlasSprite sprite;
	
	@Override
	public void preInit()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileGCBase.class, new FastTESRGC());
	}

	@Override
	public void init()
	{
		
	}

	@Override
	public void postInit()
	{
	}

	@Override
	public void sayToClient(String text, World world)
	{
		if (world.isRemote) { Minecraft.getMinecraft().player.sendMessage(new TextComponentString(text)); }
	}

	@Override
	public void openGuiGC(World worldIn, TileGCBase tile)
	{
		if (worldIn.isRemote)
			mc.displayGuiScreen(new GuiGC(tile));
	}

	@Override
	public double[] getUV(ResourceLocation tex)
	{
		mc.renderEngine.bindTexture(tex);
		sprite = mc.getTextureMapBlocks().getAtlasSprite(tex.toString());
		
		double uMin = sprite.getMinU();
		double uMax = sprite.getMaxU();
		double vMin = sprite.getMinV();
		double vMax = sprite.getMaxV();
		double[] uv = new double[] { uMin, uMax, vMin, vMax };
		
		return uv;
	}

	@Override
	public void handleGCPacket(PacketGC message, MessageContext ctx)
	{
		if (ctx.side == Side.CLIENT)
		{
			WorldClient world = Minecraft.getMinecraft().world;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			BlockPos pos = new BlockPos(x, y, z);
			
			if (world.isBlockLoaded(pos))
			{
				if (world.getTileEntity(pos) instanceof TileGCBase)
				{
					Minecraft.getMinecraft().addScheduledTask(() ->
					{
						TileGCBase tile = (TileGCBase) world.getTileEntity(pos);
						
						Expression function = message.function;
						String tex = message.tex;
						boolean crop = message.crop;
						int[] rgba = message.rgba;
						boolean colorSlope = message.colorSlope;
						int tileCount = message.tileCount;
						double[] domainX = message.domainX;
						double[] range = message.range;
						double[] domainZ = message.domainZ;
						double[] scale = message.scale;
						double[] translation = message.translation;
						double[] rotation = message.rotation;
						double resolution = message.resolution;
						double discThresh = message.discThresh;
						double aggDiscThresh = message.aggDiscThresh;
						boolean collision = message.collision;
						
						tile.setErrored(false);
						tile.renderReady = false;
						
						tile.setFunction(function);
						tile.tex = tex;
						
						tile.setResolution(resolution);
						tile.tileCount = tileCount;
						
						tile.domainA = domainX;
						tile.range = range;
						tile.domainB = domainZ;
						
						tile.scale = scale;
						tile.translation = translation;
						tile.rotation = rotation;
						
						tile.rgba = rgba;
						tile.colorSlope = colorSlope;
						
						tile.cropToRange(crop);
						tile.setDiscThresh(discThresh);
						tile.setAggDiscThresh(aggDiscThresh);
						
						tile.collision = collision;
						
						tile.genMesh();
						
						tile.markDirty();
					});
				}
			}
		}
		else if (ctx.side == Side.SERVER)
		{
			WorldServer world = ctx.getServerHandler().player.getServerWorld();
			int x = message.x;
			int y = message.y;
			int z = message.z;
			BlockPos pos = new BlockPos(x, y, z);
			
			if (world.isBlockLoaded(pos))
			{
				if (world.getTileEntity(pos) instanceof TileGCBase)
				{
					world.addScheduledTask(() ->
					{
						TileGCBase tile = (TileGCBase) world.getTileEntity(pos);
						
						Expression function = message.function;
						String tex = message.tex;
						boolean crop = message.crop;
						int[] rgba = message.rgba;
						boolean colorSlope = message.colorSlope;
						int tileCount = message.tileCount;
						double[] domainX = message.domainX;
						double[] range = message.range;
						double[] domainZ = message.domainZ;
						double[] scale = message.scale;
						double[] translation = message.translation;
						double[] rotation = message.rotation;
						double resolution = message.resolution;
						double discThresh = message.discThresh;
						double aggDiscThresh = message.aggDiscThresh;
						boolean collision = message.collision;
						
						tile.setErrored(false);
						tile.renderReady = false;
						
						tile.setFunction(function);
						tile.tex = tex;
						
						tile.setResolution(resolution);
						tile.tileCount = tileCount;
						
						tile.domainA = domainX;
						tile.range = range;
						tile.domainB = domainZ;
						
						tile.scale = scale;
						tile.translation = translation;
						tile.rotation = rotation;
						
						tile.rgba = rgba;
						tile.colorSlope = colorSlope;
						
						tile.cropToRange(crop);
						tile.setDiscThresh(discThresh);
						tile.setAggDiscThresh(aggDiscThresh);
						
						tile.collision = collision;
						
						tile.genMesh();
						
						tile.markDirty();
					});
				}
			}
		}
	}

	@Override
	public void deleteVertexData(TileGCBase te)
	{
		TileEntitySpecialRenderer<TileGCBase> tesr = TileEntityRendererDispatcher.instance.<TileGCBase>getRenderer(te);
		if (tesr != null && te.hasWorld() && te.getWorld().isRemote)
			((FastTESRGC) tesr).deleteVertexData(te);
	}
}
