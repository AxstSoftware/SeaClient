package io.github.sdxqw;

import io.github.sdxqw.gui.HudScreen;
import io.github.sdxqw.module.ModuleManager;
import io.github.sdxqw.utils.interfaces.IHelper;
import io.github.sdxqw.utils.interfaces.ILogger;
import io.github.sdxqw.utils.interfaces.IReference;
import io.github.sdxqw.utils.Keybindings;

public class SeaCore implements ILogger, IReference {

    public static SeaCore INSTANCE;
    public Keybindings keybindings;
    public ModuleManager moduleManager;

    public void onPreInitialize() {
        info("PreInitializing Client");
    }

    public void onInitialize() {
        info("Initializing Client");
        moduleManager = new ModuleManager();
        keybindings = new Keybindings();
    }

    public void onKeyPress() {
        if(Keybindings.HUD_SCREEN.isPressed()) {
            IHelper.minecraft.displayGuiScreen( new HudScreen() );
        }
    }

    public void onShutdown() {
        info("Shutdown Client");
    }

    static {
        INSTANCE = new SeaCore();
    }
}
