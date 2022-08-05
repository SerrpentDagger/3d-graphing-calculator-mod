package graphingcalculator3d.client;

import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Same as a normal Tessellator, except it uses a GCBufferBuilder, which is public and non-final.
 */
@SideOnly(Side.CLIENT)
public class GCTessellator
{
	public GCBufferBuilder buffer;
	private final WorldVertexBufferUploader vboUploader = new WorldVertexBufferUploader();
	/** The static instance of the Tessellator. */
	private static final GCTessellator INSTANCE = new GCTessellator(2097152);
	
	public static GCTessellator getInstance()
	{
		return INSTANCE;
	}
	
	public GCTessellator(int bufferSize)
    {
        this.buffer = new GCBufferBuilder(bufferSize);
    }
	
	/**
	 * Draws the data set up in this tessellator and resets the state to prepare for new drawing.
	 */
	public void draw()
	{
		this.buffer.finishDrawing();
		this.vboUploader.draw(this.buffer);
	}
	
	public GCBufferBuilder getBuffer()
	{
		return this.buffer;
	}
}
