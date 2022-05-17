package io.github.sdxqw.gui.module;

import io.github.sdxqw.module.Module;
import io.github.sdxqw.utils.GuiUtils;
import io.github.sdxqw.utils.interfaces.IHelper;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class ModuleButton {
    public int x, y, w, h;
    public Module m;

    public ModuleButton(int x, int y, int w, int h, Module m) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.m = m;
    }

    public void draw() {
        GuiUtils.drawSmoothRoundedRect(x, y, x + w, y + h, 10, new Color(55, 55, 55).getRGB());
        Gui.drawRect(x + w - 20, y, x + w, y + h, getColor());
        IHelper.font.drawString(m.name, x + w - 20 - IHelper.font.getStringWidth(m.getName()) - 5, y + h / 2 - IHelper.font.FONT_HEIGHT / 2, -1);
    }

    private int getColor() {
        if(m.isEnabled()) {
            return new Color(32, 189, 45, 255).getRGB();
        } else {
            return new Color(189, 32, 32).getRGB();
        }
    }


    public void onClick(int mouseX, int mouseY, int button) {
        if(mouseX >= x && mouseY >= y && mouseX < x + w && mouseY < y + h) {
            if(button == 0) {
                m.toggleModule();
            }
        }

    }
}
