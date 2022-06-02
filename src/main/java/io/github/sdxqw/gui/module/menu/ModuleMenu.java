package io.github.sdxqw.gui.module.menu;

import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class ModuleMenu extends GuiScreen {

    public Panel p;

    @Override
    public void initGui() {
        super.initGui();
        p = new Panel(this.width / 2 - 226, this.height / 2 - 130, this.width / 2 + 225, this.height / 2 + 150);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        p.drawPanel(mouseX, mouseY);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        p.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
