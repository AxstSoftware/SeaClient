package io.github.sdxqw;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import io.github.sdxqw.discord.DiscordIPC;
import io.github.sdxqw.events.TickEvent;
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
    public final EventBus eventBus = new EventBus();
    public Keybindings keybindings;
    public ModuleManager moduleManager;
    public CFontRenderer clientFont;
    public CFontRenderer clientFontSmaller;
    public CFontRenderer clientFontBoldSmaller;

    public void onPreInitialize() {
        ILogger.info("PreInitializing Client");
    }

    public void onInitialize() {
        eventBus.register( INSTANCE );
        ILogger.info("Initializing Client");
        SessionChanger.getInstance().setUserOffline("SuchSpeed");
        registerInstances();
        loadFonts();
        DiscordIPC.INSTANCE.init();
    }

    public void onKeyPress() {
        if(Keybindings.HUD_SCREEN.isPressed()) {
            IHelper.minecraft.displayGuiScreen( new HudScreen() );
        }
    }

    @Subscribe
    public void TickClient(TickEvent event) {
        ILogger.info( "this event work!" );
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
