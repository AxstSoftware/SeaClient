package io.github.sdxqw.gui;

import io.github.sdxqw.SeaCore;
import io.github.sdxqw.module.Module;
import io.github.sdxqw.module.renderer.RenderModule;
import io.github.sdxqw.utils.GuiUtils;
import io.github.sdxqw.utils.interfaces.IHelper;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;

public class HudScreen extends GuiScreen implements IHelper {

    private int lastDraggedMod = 0;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawBackground();

        boolean doDrag = true;

        for (Module module : SeaCore.INSTANCE.moduleManager.getModules()) {
            if (module.isEnabled() && module instanceof RenderModule) {
                ((RenderModule)module).renderModule(mouseX, mouseY);
                if (module.hashCode() == this.lastDraggedMod && ((RenderModule)module).draggableComponent.isDraggingModule(mouseX, mouseY)) {
                    doDrag = false;
                }
            }
        }

        for (Module module : SeaCore.INSTANCE.moduleManager.getModules()) {
            if (doDrag && module.isEnabled() && module instanceof RenderModule && ((RenderModule)module).draggableComponent.isDraggingModule(mouseX, mouseY)) {
                doDrag = false;
                this.lastDraggedMod = module.hashCode();
            }
        }

        super.drawScreen( mouseX, mouseY, partialTicks );
    }

    public void drawBackground() {
        this.drawRectBackground(0);
    }


    public void drawRectBackground(int tint) {
        if (this.mc.theWorld != null) {
            this.drawGradientRect(0, 0, this.width, this.height, (new Color(0, 0, 0, 120)).getRGB(), (new Color(0, 0, 0, 105)).getRGB());
        } else {
            this.drawBackground(tint);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
