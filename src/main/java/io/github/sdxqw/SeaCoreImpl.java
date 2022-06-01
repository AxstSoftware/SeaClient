package io.github.sdxqw;

import com.google.common.eventbus.Subscribe;
import io.github.sdxqw.discord.DiscordIPC;
import io.github.sdxqw.events.SCEventBus;
import io.github.sdxqw.events.type.TickEvent;
import io.github.sdxqw.gui.module.HudScreen;
import io.github.sdxqw.module.ModuleManager;
import io.github.sdxqw.utils.Keybindings;
import io.github.sdxqw.utils.SessionChanger;
import io.github.sdxqw.utils.font.CFontRenderer;
import io.github.sdxqw.utils.interfaces.IHelper;
import io.github.sdxqw.utils.interfaces.IReference;
import lombok.Getter;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class SeaCoreImpl implements IReference {

    @Getter
    private final SCEventBus EVENT_BUS = new SCEventBus();
    @Getter
    private final ModuleManager moduleManager = new ModuleManager();


    public CFontRenderer clientFont;
    public CFontRenderer clientFontSmaller;
    public CFontRenderer clientFontBoldSmaller;

    public void initializeAllMethods() {
        Keybindings.registeredKeyBinding();
        moduleManager.registeredModules();
        SessionChanger.getInstance().setUserOffline("SuchSpeed");
        loadFonts();
        DiscordIPC.getINSTANCE().init();
    }

    protected void loadFonts() {
        clientFont = new CFontRenderer(new ResourceLocation("seaclient/font/Lato-Bold.ttf"), 20);
        clientFontSmaller = new CFontRenderer(new ResourceLocation("seaclient/font/Lato-Thin.ttf"), 18);
        clientFontBoldSmaller = new CFontRenderer( new ResourceLocation("seaclient/font/Lato-Regular.ttf"), 22 );
    }

    @Subscribe
    protected void TickClient(TickEvent event) {
    }

    public void onKeyPress() {
        if(Keybindings.HUD_SCREEN.isPressed()) {
            IHelper.minecraft.displayGuiScreen( new HudScreen() );
        }
    }
}
