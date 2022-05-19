package io.github.sdxqw;

import io.github.sdxqw.database.SeaDatabase;
import io.github.sdxqw.discord.DiscordIPC;
import io.github.sdxqw.gui.module.HudScreen;
import io.github.sdxqw.module.ModuleManager;
import io.github.sdxqw.utils.Keybindings;
import io.github.sdxqw.utils.SessionChanger;
import io.github.sdxqw.utils.font.CFontRenderer;
import io.github.sdxqw.utils.interfaces.IHelper;
import io.github.sdxqw.utils.interfaces.ILogger;
import io.github.sdxqw.utils.interfaces.IReference;
import net.minecraft.util.ResourceLocation;

public class SeaCore implements IReference {

    public static SeaCore INSTANCE;
    public Keybindings keybindings;
    public ModuleManager moduleManager;
    public CFontRenderer clientFont;
    public CFontRenderer clientFontSmaller;
    public CFontRenderer clientFontBoldSmaller;

    public void onPreInitialize() {
        ILogger.info("PreInitializing Client");
    }

    public void onInitialize() {
        ILogger.info("Initializing Client");
        SessionChanger.getInstance().setUserOffline("SuchSpeed");
        new Thread( SeaDatabase.INSTANCE::startDatabase, "Database Fetcher").start();
        registerInstances();
        loadFonts();
        DiscordIPC.INSTANCE.init();
    }

    public void onKeyPress() {
        if(Keybindings.HUD_SCREEN.isPressed()) {
            IHelper.minecraft.displayGuiScreen( new HudScreen() );
        }
    }

    public void registerInstances() {
        moduleManager = new ModuleManager();
        keybindings = new Keybindings();
    }

    public void loadFonts() {
        clientFont = new CFontRenderer(new ResourceLocation("seaclient/font/Lato-Bold.ttf"), 20);
        clientFontSmaller = new CFontRenderer(new ResourceLocation("seaclient/font/Lato-Thin.ttf"), 18);
        clientFontBoldSmaller = new CFontRenderer( new ResourceLocation("seaclient/font/Lato-Regular.ttf"), 22 );
    }

    public void onShutdown() {
        ILogger.info("Shutdown Client");
        DiscordIPC.INSTANCE.shutdown();
    }

    static {
        INSTANCE = new SeaCore();
    }
}
