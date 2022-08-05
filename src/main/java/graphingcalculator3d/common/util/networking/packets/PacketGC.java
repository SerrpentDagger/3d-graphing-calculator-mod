package graphingcalculator3d.common.util.networking.packets;

import java.nio.charset.Charset;

import graphingcalculator3d.common.GraphingCalculator3D;
import graphingcalculator3d.common.gameplay.tile.TileGCBase;
import graphingcalculator3d.common.util.math.expression.Expression;
import graphingcalculator3d.common.util.nbthandler.GCNBT;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketGC implements IMessage
{
	public PacketGC()
	{
	}
	
	public int x, y, z;
	public Expression function;
	public String tex = "";
	public boolean crop = GCNBT.GC_CROP_TO_RANGE.defaultVal();
	public int[] rgba = GCNBT.GC_RGBA.defaultVal();
	public boolean colorSlope = GCNBT.GC_COLOR_SLOPE.defaultVal();
	public int tileCount = GCNBT.GC_TILE_COUNT.defaultVal();
	public double[] domainX = GCNBT.GC_DOMAIN_A.defaultVal();
	public double[] range = GCNBT.GC_RANGE.defaultVal();
	public double[] domainZ = GCNBT.GC_DOMAIN_B.defaultVal();
	public double[] scale = GCNBT.GC_SCALE.defaultVal();
	public double[] translation = GCNBT.GC_TRANSLATION.defaultVal();
	public double[] rotation = GCNBT.GC_ROTATION.defaultVal();
	public double resolution = GCNBT.GC_RESOLUTION.defaultVal();
	public double discThresh = GCNBT.GC_DISC_THRESH.defaultVal();
	public double aggDiscThresh = GCNBT.GC_AGG_DISC_THRESH.defaultVal();
	public boolean collision = GCNBT.GC_COLLISION.defaultVal();
	
	private boolean badPos = false;
	
	public PacketGC(TileGCBase tile)
	{
		x = tile.getPos().getX();
		y = tile.getPos().getY();
		z = tile.getPos().getZ();
		
		if (!(tile.hasWorld() && tile.getWorld().isBlockLoaded(new BlockPos(x, y, z))))
			badPos = true;
		
		function = tile.getFunction();
		tex = tile.tex;
		crop = tile.cropToRange();
		rgba = tile.rgba;
		colorSlope = tile.colorSlope;
		tileCount = tile.tileCount;
		domainX = tile.domainA;
		range = tile.range;
		domainZ = tile.domainB;
		scale = tile.scale;
		translation = tile.translation;
		rotation = tile.rotation;
		resolution = tile.getResolution();
		discThresh = tile.getDiscThresh();
		aggDiscThresh = tile.getAggDiscThresh();
		collision = tile.collision;
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		if (!badPos)
		{
			try
			{
				Charset charset = Charset.defaultCharset();
				
				buf.writeInt(x);
				buf.writeInt(y);
				buf.writeInt(z);
				
				if (function == null)
					buf.writeInt(0);
				else
				{
					String fString = function.writeToString();
					int l = fString.length();
					buf.writeInt(l);
					buf.writeCharSequence(fString, charset);
				}
				
				if (tex == null)
					buf.writeInt(0);
				else
				{
					int l = tex.length();
					buf.writeInt(l);
					buf.writeCharSequence(tex, charset);
				}
				
				buf.writeBoolean(crop);
				for (int i = 0; i < 5; i++)
				{
					buf.writeInt(rgba[i]);
				}
				buf.writeBoolean(colorSlope);
				buf.writeBoolean(collision);
				
				buf.writeInt(tileCount);
				buf.writeDouble(resolution);
				buf.writeDouble(discThresh);
				buf.writeDouble(aggDiscThresh);
				
				for (int i = 0; i < 3; i++)
				{
					buf.writeDouble(scale[i]);
				}
				for (int i = 0; i < 3; i++)
				{
					buf.writeDouble(translation[i]);
				}
				for (int i = 0; i < 3; i++)
				{
					buf.writeDouble(rotation[i]);
				}
				for (int i = 0; i < 2; i++)
				{
					buf.writeDouble(domainX[i]);
				}
				for (int i = 0; i < 2; i++)
				{
					buf.writeDouble(range[i]);
				}
				for (int i = 0; i < 2; i++)
				{
					buf.writeDouble(domainZ[i]);
				}
			}
			catch (IndexOutOfBoundsException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		try
		{
			Charset charset = Charset.defaultCharset();
			
			x = buf.readInt();
			y = buf.readInt();
			z = buf.readInt();
			
			int l = buf.readInt();
			if (l > 0)
			{
				CharSequence fChars = buf.readCharSequence(l, charset);
				function = Expression.parseFromChars(fChars);
			}
			else
				function = null;
			
			l = buf.readInt();
			if (l > 0)
			{
				CharSequence tChars = buf.readCharSequence(l, charset);
				tex = tChars.toString();
			}
			else
				tex = "";
			
			crop = buf.readBoolean();
			for (int i = 0; i < 5; i++)
			{
				rgba[i] = buf.readInt();
			}
			colorSlope = buf.readBoolean();
			collision = buf.readBoolean();
			
			tileCount = buf.readInt();
			resolution = buf.readDouble();
			discThresh = buf.readDouble();
			aggDiscThresh = buf.readDouble();
			
			for (int i = 0; i < 3; i++)
			{
				scale[i] = buf.readDouble();
			}
			for (int i = 0; i < 3; i++)
			{
				translation[i] = buf.readDouble();
			}
			for (int i = 0; i < 3; i++)
			{
				rotation[i] = buf.readDouble();
			}
			for (int i = 0; i < 2; i++)
			{
				domainX[i] = buf.readDouble();
			}
			for (int i = 0; i < 2; i++)
			{
				range[i] = buf.readDouble();
			}
			for (int i = 0; i < 2; i++)
			{
				domainZ[i] = buf.readDouble();
			}
		}
		catch (IndexOutOfBoundsException | NumberFormatException e)
		{
			e.printStackTrace();
		}
	}
	
	public static class PacketGCHandler implements IMessageHandler<PacketGC, IMessage>
	{
		@Override
		public IMessage onMessage(PacketGC message, MessageContext ctx)
		{
			GraphingCalculator3D.proxy.handleGCPacket(message, ctx);
			
			return null;
		}
	}
}
