package io.github.sdxqw.gui;

import io.github.sdxqw.SeaCore;
import io.github.sdxqw.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import java.awt.*;

public class CustomGuiButton extends GuiButton {

    public CustomGuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super( buttonId, x, y, widthIn, heightIn, buttonText );
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
        GuiUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 9f, new Color( 81, 81, 81, 167 ).getRGB());
        SeaCore.INSTANCE.clientFontBoldSmaller.drawCenteredString(this.displayString, this.xPosition + (this.width >> 1), this.yPosition + ((this.height - 8) >> 1) + 4, -1);
        this.mouseDragged( mc, mouseX, mouseY );
    }
}
