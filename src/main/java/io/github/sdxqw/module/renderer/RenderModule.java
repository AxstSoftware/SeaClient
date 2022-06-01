package io.github.sdxqw.module.renderer;

import io.github.sdxqw.module.Module;
import io.github.sdxqw.module.renderer.DraggableComponent;
import io.github.sdxqw.utils.GuiUtils;
import lombok.Getter;

import java.awt.*;

public abstract class RenderModule extends Module {

    public int x;
    public int y;

    @Getter
    public int widthIn;
    @Getter
    public int heightIn;

    @Getter
    private final DraggableComponent draggableComponent;

    public RenderModule(String name, String description, int x, int y) {
        super( name, description );
        this.x = x;
        this.y = y;
        draggableComponent = new DraggableComponent( x, y, getWidthIn(), getWidthIn() );
    }

    public abstract void drawModule();

    public void renderModule(int mouseX, int mouseY) {
        boolean hovered = mouseX >= this.getX() && mouseX <= this.getX() + this.getWidthIn() && mouseY >= this.getY() && mouseY <= this.getY() + this.getHeightIn();
        if (hovered) {
            GuiUtils.drawHollowRect(this.getX() - 2, this.getY() - 2, this.getWidthIn() + 3, this.getHeightIn() + 2, (new Color( 0, 204, 255, 152)).getRGB());
        }

        GuiUtils.drawHollowRect(this.getX() - 2, this.getY() - 2, this.getWidthIn() + 3, this.getHeightIn() + 2, (new Color(170, 170, 170, 100)).getRGB());
    }

    public int getX() {
        return draggableComponent.getX();
    }

    public int getY() {
        return draggableComponent.getY();
    }
}

