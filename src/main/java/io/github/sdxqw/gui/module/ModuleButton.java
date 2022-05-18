package io.github.sdxqw.gui.module;

import io.github.sdxqw.module.Module;
import io.github.sdxqw.utils.GuiUtils;
import io.github.sdxqw.utils.interfaces.IHelper;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class ModuleButton {
    public int x, y, withIn, heightIn;
    public Module module;

    public ModuleButton(int x, int y, int withIn, int heightIn, Module module) {
        this.x = x;
        this.y = y;
        this.withIn = withIn;
        this.heightIn = heightIn;
        this.module = module;
    }

    public void draw() {
        GuiUtils.drawSmoothRoundedRect(x, y, x + withIn, y + heightIn, 10, new Color(55, 55, 55).getRGB());
        Gui.drawRect(x + withIn - 20, y, x + withIn, y + heightIn, getColor());
        IHelper.font.drawString(module.name, x + withIn - 20 - IHelper.font.getStringWidth(module.getName()) - 5, y + heightIn / 2 - IHelper.font.FONT_HEIGHT / 2, -1);
    }

    private int getColor() {
        if(module.isEnabled()) {
            return new Color(32, 189, 45, 255).getRGB();
        } else {
            return new Color(189, 32, 32).getRGB();
        }
    }


    public void onClick(int mouseX, int mouseY, int button) {
        if(mouseX >= x && mouseY >= y && mouseX < x + withIn && mouseY < y + heightIn) {
            if(button == 0) {
                module.toggleModule();
            }
        }

    }
}
