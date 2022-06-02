package io.github.sdxqw.gui.module.menu.misc;

import io.github.sdxqw.SeaCore;
import io.github.sdxqw.module.Module;
import io.github.sdxqw.utils.GuiUtils;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;

public class ModuleButton extends GuiScreen {

    public int x;
    public int y;
    public int w;
    public int h;

    private final Module m;

    public ModuleButton(int x, int y, int widthIn, int heightIn, Module module) {
        this.x = x;
        this.y = y;
        this.w = widthIn;
        this.h = heightIn;
        this.m = module;
    }

    public void drawButton() {
        GuiUtils.drawSmoothRoundedRect(x, y, x + w, y + h, 3, getColor());
        GuiUtils.drawSelectRoundedOutline(x, y, x + w, y + h, 3, 3, 3, 3, 3, new Color(255,255,255).getRGB());
        SeaCore.getINSTANCE().clientFontBoldSmaller.drawCenteredString(m.name, x + w - 10 - SeaCore.getINSTANCE().clientFontBoldSmaller.getStringWidth(m.name), y + (h >> 1) - (SeaCore.getINSTANCE().clientFontBoldSmaller.getHeight() >> 1) + 3, new Color(255,255,255).getRGB());
    }

    public int getColor() {
        if (m.isEnabled()) {
            return new Color(42, 141, 255, 255).getRGB();
        } else {
            return new Color(103, 103, 103).getRGB();
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
