package io.github.sdxqw;

import io.github.sdxqw.discord.DiscordIPC;
import io.github.sdxqw.utils.interfaces.ILogger;
import lombok.Getter;

public final class SeaCore extends SeaCoreImpl {

    @Getter
    private static final SeaCore INSTANCE = new SeaCore();

    public void onPreInitialize() {
        ILogger.info("PreInitializing Client");
        SeaCore.getINSTANCE().getEVENT_BUS().register( INSTANCE );
    }

    public void onInitialize() {
        ILogger.info("Initializing Client");
        SeaCore.getINSTANCE().initializeAllMethods();
    }

    public void onShutdown() {
        ILogger.info("Shutdown Client");
        DiscordIPC.getINSTANCE().shutdown();
    }
}
