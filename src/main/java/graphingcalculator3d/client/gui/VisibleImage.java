package graphingcalculator3d.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class VisibleImage extends VisibleBase
{
	public static final int INVALID_VALUE = -10;
	
	private Gui parentGui;
	
	private int tX = INVALID_VALUE, tY = INVALID_VALUE, panelWidth = INVALID_VALUE, panelHeight = INVALID_VALUE;
	private ResourceLocation image;
		
	public VisibleImage()
	{
		super();
		visible = true;
		width = INVALID_VALUE;
		height = INVALID_VALUE;
	}

	public VisibleImage(Gui parent)
	{
		this();
		parentGui = parent;
	}
	
	public VisibleImage(int index)
	{
		this();
		setIndex(index);
	}
	
	public VisibleImage(ResourceLocation imageResource, int index)
	{
		this(index);
		image = imageResource;
	}
	
	public VisibleImage(ResourceLocation imageResource, int index, int widthHeight)
	{
		this(imageResource, index);
		panelWidth = widthHeight;
		panelHeight = widthHeight;
	}
	
	public VisibleImage(ResourceLocation imageResource, int index, int width, int height)
	{
		this(imageResource, index);
		panelWidth = width;
		panelHeight = height;
	}
	
	public VisibleImage(int x, int y)
	{
		this();
		xPos = x;
		yPos = y;
	}
	
	public VisibleImage(int x, int y, ResourceLocation imageResource)
	{
		this(x, y);
		image = imageResource;
	}
	
	public VisibleImage(int x, int y, ResourceLocation imageResorurce, boolean visibleIn)
	{
		this(x, y, imageResorurce);
		visible = visibleIn;
	}
	
	public VisibleImage(int x, int y, ResourceLocation imageResorurce, boolean visibleIn, int index)
	{
		this(x, y, imageResorurce, visibleIn);
		setIndex(index);
	}
	
	public VisibleImage(int x, int y, ResourceLocation imageResource, boolean visibleIn, int widthIn, int heightIn)
	{
		this(x, y, imageResource, visibleIn);
		width = widthIn;
		height = heightIn;
	}
	
	public VisibleImage(int x, int y, ResourceLocation imageResource, boolean visibleIn, int widthIn, int heightIn, int textureX, int textureY)
	{
		this(x, y, imageResource, visibleIn, widthIn, heightIn);
		tX = textureX;
		tY = textureY;
	}
	
	@Override
	public void draw()
	{
		if (!visible) { return; }
		if (image != null)
		{
			mc.renderEngine.bindTexture(image);
			
			if (width == INVALID_VALUE)
			{
				width = GlStateManager.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_WIDTH);
				height = GlStateManager.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_HEIGHT);
			}
			if (tX == INVALID_VALUE)
			{
				tX = 0;
				tY = 0;
			}
			if (panelWidth == INVALID_VALUE)
			{
				panelWidth = width;
				panelHeight = height;
			}
			
			GlStateManager.color(1, 1, 1);
			GlStateManager.enableBlend();
			Gui.drawScaledCustomSizeModalRect(xPos, yPos, tX, tY, width, height, panelWidth, panelHeight, width, height);
			GlStateManager.disableAlpha();
		}
		else if (!errorLogged)
		{
			System.out.println("ERROR: Image is null in VisibleImage object: " + this.toString() + "  Draw failed.");
			errorLogged = true;
		}
	}
	
	/////////////////////////////////////////////
	
	public ResourceLocation getImage()
	{
		return image;
	}
	
	public int getTextureX()
	{
		return tX;
	}
	
	public int getTextureY()
	{
		return tY;
	}
	
	public Gui getParent()
	{
		return parentGui;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	@Override
	public int getPanelWidth()
	{
		return panelWidth;
	}
	
	@Override
	public int getPanelHeight()
	{
		return panelHeight;
	}
	
	////////////////////////////////////////////
	
	public void setImage(ResourceLocation imageResource)
	{
		image = imageResource;
	}
	
	public void setTextureX(int textureX)
	{
		tX = textureX;
	}
	
	public void setTextureY(int textureY)
	{
		tY = textureY;
	}
	
	public void setParent(Gui parent)
	{
		parentGui = parent;
	}
	
	public void setWidth(int wdth)
	{
		width = wdth;
	}
	
	public void setHeight(int hght)
	{
		height = hght;
	}
	
	@Override
	public VisibleImage indented(boolean indented)
	{
		return (VisibleImage) super.indented(indented);
	}
	
	@Override
	public VisibleImage breaksPage(boolean breaks)
	{
		return (VisibleImage) super.breaksPage(breaks);
	}
}
