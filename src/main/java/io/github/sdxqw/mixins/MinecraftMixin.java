package io.github.sdxqw.mixins;

import io.github.sdxqw.SeaCore;
import io.github.sdxqw.utils.interfaces.IHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.InputStream;
import java.nio.ByteBuffer;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Shadow
    @Final
    private DefaultResourcePack mcDefaultResourcePack;

    @Inject(method = "startGame", at = @At("HEAD"))
    public void injectPreInitialize(CallbackInfo ci) {
        SeaCore.INSTANCE.onPreInitialize();
    }

    @Inject(method = "startGame", at = @At("RETURN"))
    public void injectInitialize(CallbackInfo ci) {
        SeaCore.INSTANCE.onInitialize();
    }

    @Inject(method = "shutdownMinecraftApplet", at = @At("HEAD"))
    public void injectShutdown(CallbackInfo ci) {
        SeaCore.INSTANCE.onShutdown();
    }

    @ModifyArg( method = "createDisplay", at = @At( value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V", remap = false ) )
    public String modifySetTitle(final String p0) {
        return  SeaCore.clientName + " (" + SeaCore.clientVersion + ")";
    }

    @Inject(method = { "dispatchKeypresses" }, at = { @At("HEAD") })
    private void dispatchKeypresses(final CallbackInfo ci) {
        SeaCore.INSTANCE.onKeyPress();
    }

    /**
     * Overwrite the methods
     * @author sdxqw
     */
    @Overwrite
    private void setWindowIcon()
    {
        if (Util.getOSType() != Util.EnumOS.OSX)
        {
            try {
                InputStream inputStream = MinecraftMixin.class.getResourceAsStream( "/assets/minecraft/seaclient/logo.png" );
                InputStream inputStream1 = MinecraftMixin.class.getResourceAsStream( "/assets/minecraft/seaclient/logo.png" );

                if (inputStream == null) {
                    inputStream = this.mcDefaultResourcePack.getInputStreamAssets( new ResourceLocation( "icons/icon_16x16.png" ) );
                }

                if (inputStream1 == null) {
                    inputStream1 = this.mcDefaultResourcePack.getInputStreamAssets( new ResourceLocation( "icons/icon_32x32.png" ) );
                }
                Display.setIcon(new ByteBuffer[] { this.readImageToBuffer(inputStream), this.readImageToBuffer(inputStream1) });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Shadow
    protected abstract ByteBuffer readImageToBuffer(final InputStream p0);
}
