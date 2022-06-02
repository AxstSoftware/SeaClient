package io.github.sdxqw.gui.module.menu.misc;

import io.github.sdxqw.SeaCore;
import io.github.sdxqw.module.Module;
import io.github.sdxqw.utils.GuiUtils;
import io.github.sdxqw.utils.interfaces.IHelper;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class ModuleButton {

    public int x;
    public int y;
    public int w;
    public int h;

    private Module m;

    public ModuleButton(int x, int y, int widthIn, int heightIn, Module module) {
        this.x = x;
        this.y = y;
        this.w = widthIn;
        this.h = heightIn;
        this.m = module;
    }

    public void drawButton() {
        GuiUtils.drawSmoothRoundedRect(x, y, x + w, y + h, 10, new Color(55, 55, 55).getRGB());
        Gui.drawRect(x + w - 20, y, x + w, y + h, getColor());
        IHelper.font.drawString(m.name, x + w - 20 - IHelper.font.getStringWidth(m.getName()) - 5, y + h / 2 - IHelper.font.FONT_HEIGHT / 2, -1);
    }

    public int getColor() {
        if (m.isEnabled()) {
            return new Color(32, 189, 45, 255).getRGB();
        } else {
            return new Color(189, 32, 32).getRGB();
        }
    }

    public void buttonClicked(int mouseX, int mouseY, int button) {
        if (mouseX >= x && mouseY >= y && mouseX < x + w && mouseY < y + h) {
            if (button == 0) {
                m.toggleModule();
            }
        }
    }
}
