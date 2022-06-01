package io.github.sdxqw.utils;

import io.github.sdxqw.utils.interfaces.IHelper;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;

public class Keybindings {
    public static final KeyBinding HUD_SCREEN = new KeyBinding("Hud Screen", Keyboard.KEY_RSHIFT, "Rebel Client");

    public static void registeredKeyBinding() {
        registerKeyBinding(HUD_SCREEN);
    }

    public static void registerKeyBinding(KeyBinding keyBinding) {
        IHelper.minecraft.gameSettings.keyBindings = ArrayUtils.add(IHelper.minecraft.gameSettings.keyBindings, keyBinding);
    }
}
