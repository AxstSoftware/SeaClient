package io.github.sdxqw.mixins;

import io.github.sdxqw.SeaCore;
import io.github.sdxqw.gui.CustomGuiButton;
import io.github.sdxqw.gui.CustomGuiIcon;
import io.github.sdxqw.utils.GuiUtils;
import io.github.sdxqw.utils.interfaces.IHelper;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.awt.*;

@Mixin(GuiMainMenu.class)
public class GuiMainMenuMixin extends GuiScreen implements IHelper {

    public ResourceLocation background = new ResourceLocation( "seaclient/bg.png" );
    public ResourceLocation logo = new ResourceLocation( "seaclient/logo.png" );

    /**
     * @author sdxqw
     */
    @Overwrite
    public void initGui() {
        final ScaledResolution sr = new ScaledResolution( mc );
        this.buttonList.add(new CustomGuiButton(1, sr.getScaledWidth() / 2 - 65, sr.getScaledHeight() / 2 - 10, 130, 25, "SINGLEPLAYER"));
        this.buttonList.add(new CustomGuiButton(2, sr.getScaledWidth() / 2 - 65, sr.getScaledHeight() / 2 + 20, 130, 25, "MULTIPLAYER"));
        this.buttonList.add(new CustomGuiIcon( 0, sr.getScaledWidth() / 2 - 10, sr.getScaledHeight() / 2 + 65, "web.png" ) );
        this.buttonList.add(new CustomGuiIcon( 4, sr.getScaledWidth() / 2 - 50, sr.getScaledHeight() / 2 + 65, "yt.png" ) );
        this.buttonList.add(new CustomGuiIcon( 5, sr.getScaledWidth() / 2 - 90, sr.getScaledHeight() / 2 + 65, "logo.png" ) );
        this.buttonList.add(new CustomGuiIcon( 6, sr.getScaledWidth() / 2 + 30, sr.getScaledHeight() / 2 + 65, "discord.png" ) );
        this.buttonList.add(new CustomGuiIcon( 7, sr.getScaledWidth() / 2 + 70, sr.getScaledHeight() / 2 + 65, "settings.png" ) );
        this.buttonList.add(new CustomGuiIcon( 8,  sr.getScaledWidth() - 34,7, "exit.png" ) );
        super.initGui();
    }

    /**
     * @author sdxqw
     */
    @Overwrite
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(this.mc);
        GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F,1.0F);
        minecraft.getTextureManager().bindTexture(background);
        Gui.drawModalRectWithCustomSizedTexture(-22 + (Mouse.getX() / 90), ((Mouse.getY() * -1 / 90)), 0, 0, width + 20, height + 18, width + 21, height + 20);
        GuiUtils.draw2DImage(logo, sr.getScaledWidth() / 2 - 40, sr.getScaledHeight() / 2 - 100, 80, 80, new Color(255,255,255));
        GlStateManager.popMatrix();
        String watermark = "Sea Client (1.8.9-v7)";
        SeaCore.INSTANCE.clientFontBoldSmaller.drawString(watermark,4, this.height - 14, new Color( 230, 230, 230, 157 ).getRGB());
        String copyright = "Copyright Mojang AB. Do not distribute!";
        SeaCore.INSTANCE.clientFontBoldSmaller.drawString(copyright, this.width - this.fontRendererObj.getStringWidth(copyright) - 14, this.height - 14, new Color( 230, 230, 230, 157 ).getRGB());
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
