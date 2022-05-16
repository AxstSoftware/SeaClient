package io.github.sdxqw.module.misc;

import io.github.sdxqw.module.renderer.RenderModule;

import java.awt.*;

public class TestModule extends RenderModule {
    public TestModule() {
        super( "test", "test module", 100, 100 );
    }


    @Override
    public void drawModule() {
        font.drawString( getName(), getX(), getY(), new Color( 255,255,255 ).getRGB() );
    }

    @Override
    public void renderModule(int mouseX, int mouseY) {
        font.drawString( getName(), getX(), getY(), new Color( 255,255,255 ).getRGB() );
        super.renderModule( mouseX, mouseY );
    }

    @Override
    public int getWidthIn() {
        return font.getStringWidth( getName() );
    }

    @Override
    public int getHeightIn() {
        return font.FONT_HEIGHT;
    }
}
