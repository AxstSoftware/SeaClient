package io.github.sdxqw.gui.module.menu;

import io.github.sdxqw.SeaCore;
import io.github.sdxqw.gui.module.menu.misc.ModuleButton;
import io.github.sdxqw.module.Module;
import io.github.sdxqw.utils.interfaces.IHelper;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class Panel {

    public final List<ModuleButton> mb = new ArrayList<>();

    public int x;
    public int y;
    public int w;
    public int h;

    public int scrollAmount = 0;

    public PanelType pt = PanelType.MODULE;
    public PanelType.ModuleType mt = PanelType.ModuleType.NORMAL;
    public Module m;

    protected Panel(int x, int y, int widthIn, int heightIn) {
        this.x = x;
        this.y = y;
        this.w = widthIn;
        this.h = heightIn;
    }

    public void drawPanel(int mouseX, int mouseY) {

        if(this.mt == PanelType.ModuleType.NORMAL) {
            if (this.pt == PanelType.MODULE) {
                mb.clear();

                int xAdd = 0;
                int xFactor = 100;
                int yAdd = 0;
                int spots = 0;

                while ((spots * xFactor) < (350)) {
                    spots++;
                }

                for(Module module : SeaCore.getINSTANCE().getModuleManager().getModules()) {

                    if (xAdd == (spots * xFactor) && xAdd != 0) {
                        xAdd = 0;
                        yAdd += 40;
                    }

                    mb.add(new ModuleButton(x + 30 + xAdd, y + 30 + yAdd + scrollAmount, 60, 20, module));

                    xAdd += xFactor;
                }

                GL11.glEnable(GL11.GL_SCISSOR_TEST);
                this.glScissor(x + 2, y + 25, w - 2, h - 2);
                mb.forEach(ModuleButton::drawButton);
                GL11.glDisable(GL11.GL_SCISSOR_TEST);
            }
        } else if (this.mt == PanelType.ModuleType.LARGE) {
            IHelper.font.drawString("test", 100,100,-1);
        }

    }

    public void setPanelType(PanelType panelType, Module module) {
        if (panelType == PanelType.MODULE) {
            this.pt = PanelType.MODULE;
            this.m = null;
        } else if (panelType == PanelType.SETTINGS) {
            this.pt = PanelType.SETTINGS;
            this.m = module;
        }
    }

    private void glScissor(double x, double y, double width, double height) {

        y += height;

        ScaledResolution scaledResolution = new ScaledResolution(IHelper.minecraft);

        GL11.glScissor((int) ((x * IHelper.minecraft.displayWidth) / scaledResolution.getScaledWidth()),
                (int) (((scaledResolution.getScaledHeight() - y) * IHelper.minecraft.displayHeight) / scaledResolution.getScaledHeight()),
                (int) (width * IHelper.minecraft.displayWidth / scaledResolution.getScaledWidth()),
                (int) (height * IHelper.minecraft.displayHeight / scaledResolution.getScaledHeight()));
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for(ModuleButton moduleButton : mb) {
            moduleButton.buttonClicked(mouseX, mouseY, mouseButton);
        }
    }
}
