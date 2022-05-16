package io.github.sdxqw.mixins;

import io.github.sdxqw.SeaCore;
import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public class GuiIngameMixin {
    @Inject(method = "renderGameOverlay", at = @At("RETURN"))
    private void runTick (CallbackInfo info) {
        SeaCore.INSTANCE.moduleManager.renderModules();
    }
}
