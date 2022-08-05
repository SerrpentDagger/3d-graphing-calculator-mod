package graphingcalculator3d.client.gui;

import graphingcalculator3d.common.GraphingCalculator3D;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TextButton extends GuiButton
{

	public int defaultWidth = 120;
	public int defaultHeight = 10;
	public boolean hasVisibleItemButton;
	protected static final ResourceLocation textButton = new ResourceLocation(GraphingCalculator3D.MODID + ":textures/gui/text_button.png");
	
	/**
	 * @param buttonId
	 * @param x
	 * @param y
	 * @param widthIn
	 * @param heightIn
	 * @param buttonText
	 */
	public TextButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText)
	{
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		if (widthIn == 0) { width = defaultWidth; }
		if (heightIn == 0) { height = defaultHeight; }
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(textButton);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.drawTexturedModalRect(this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
            int j = 90909;

            if (packedFGColour != 0)
            {
                j = packedFGColour;
            }
            else
            if (!this.enabled)
            {
                j = 10526880;
            }
            else if (this.hovered)
            {
                j = 15132390;
            }

            fontrenderer.drawString(this.displayString, x, y, j);
        }
    }

	
}
