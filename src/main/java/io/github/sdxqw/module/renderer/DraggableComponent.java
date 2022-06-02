package io.github.sdxqw.module.renderer;

import io.github.sdxqw.utils.interfaces.IHelper;
import lombok.Getter;
import lombok.Setter;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;

public class DraggableComponent {
    public final ArrayList<RenderModule> modules = new ArrayList<>();

    @Setter @Getter
    public int x;
    @Setter @Getter
    public int y;

    @Getter
    public int widthIn;
    @Getter
    public int heightIn;

    public int lastX;
    public int lastY;

    public boolean draggingModule;

    protected DraggableComponent(int x, int y, int widthIn, int heightIn) {
        this.x = x;
        this.y = y;
        this.widthIn = widthIn;
        this.heightIn = heightIn;
    }

    public boolean isDraggingModule(int mouseX, int mouseY) {
        isDragging();
        if(draggingModule) {
            x = mouseX + lastX;
            y = mouseY + lastY;
            if(!Mouse.isButtonDown( 0 )) {
                draggingModule = false;
            }
        }

        if (mouseX >= this.x && mouseX <= this.x + this.widthIn && mouseY >= this.y && mouseY <= this.y + this.heightIn && Mouse.isButtonDown(0) && !this.draggingModule) {
            x = mouseX - lastX;
            y = mouseY - lastY;
            draggingModule = true;
        }
        return draggingModule;
    }

    public void isDragging() {
        int displayHeight = IHelper.minecraft.displayHeight;
        int displayWith = IHelper.minecraft.displayWidth;
        for (RenderModule module : modules) {
            if (module.isEnabled()) {
                if (module.x - module.getHeightIn() / 2 < 0) {
                    module.x = module.getHeightIn() / 2;
                }

                if (module.x + module.getWidthIn() / 2 > displayWith / 2) {
                    module.x = displayWith / 2 - module.getWidthIn() / 2;
                }

                if (module.y - module.getHeightIn() / 2 < 0) {
                    module.y = module.getHeightIn() / 2;
                }

                if (module.y + module.getHeightIn() / 2 > displayHeight / 2) {
                    module.y = displayHeight / 2 - module.getHeightIn() / 2;
                }
            }
        }
    }
}
