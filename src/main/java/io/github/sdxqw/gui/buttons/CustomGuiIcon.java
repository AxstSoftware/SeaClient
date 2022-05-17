package io.github.sdxqw.gui.buttons;

import io.github.sdxqw.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class CustomGuiIcon extends GuiButton {

    public ResourceLocation iconButton;

    public CustomGuiIcon(int buttonId, int x, int y, String iconButton) {
        super( buttonId, x, y, 25, 25, "" );
        this.iconButton = new ResourceLocation( "seaclient/icons/" + iconButton );
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            GlStateManager.pushMatrix();
            GlStateManager.enableAlpha();
            this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
            GuiUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 9f, new Color( 81, 81, 81, 167 ).getRGB());
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            GL11.glColor3f(255, 255, 255);
            mc.getTextureManager().bindTexture(iconButton);
            Gui.drawModalRectWithCustomSizedTexture(this.xPosition + (this.width - 16) / 2, this.yPosition + (this.height - 16) / 2, 0, 0, 16, 16, (float) 16, (float) 16);
            GlStateManager.popMatrix();
            this.mouseDragged(mc, mouseX, mouseY);
        }
    }
}
