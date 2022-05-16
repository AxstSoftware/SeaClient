package io.github.sdxqw;

import io.github.sdxqw.discord.DiscordIPC;
import io.github.sdxqw.gui.HudScreen;
import io.github.sdxqw.module.ModuleManager;
import io.github.sdxqw.utils.SessionChanger;
import io.github.sdxqw.utils.interfaces.IHelper;
import io.github.sdxqw.utils.interfaces.ILogger;
import io.github.sdxqw.utils.interfaces.IReference;
import io.github.sdxqw.utils.Keybindings;
import lombok.Getter;

public class SeaCore implements IReference {

    public static SeaCore INSTANCE;
    public Keybindings keybindings;
    public ModuleManager moduleManager;

    public void onPreInitialize() {
        ILogger.info("PreInitializing Client");
    }

    public void onInitialize() {
        ILogger.info("Initializing Client");
        moduleManager = new ModuleManager();
        keybindings = new Keybindings();
        SessionChanger.getInstance().setUserOffline("SuchSpeed");
        DiscordIPC.INSTANCE.init();
    }

    public void onKeyPress() {
        if(Keybindings.HUD_SCREEN.isPressed()) {
            IHelper.minecraft.displayGuiScreen( new HudScreen() );
        }
    }

    public void onShutdown() {
        ILogger.info("Shutdown Client");
        DiscordIPC.INSTANCE.shutdown();
    }

    static {
        INSTANCE = new SeaCore();
    }
}
