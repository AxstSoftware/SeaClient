package io.github.sdxqw.module;

import io.github.sdxqw.module.misc.TestModule;
import io.github.sdxqw.module.renderer.RenderModule;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    @Getter
    List<Module> modules = new ArrayList<>();

    public TestModule testModule;

    public ModuleManager() {
        registerModules( testModule = new TestModule() );
    }

    public void registerModules(final Module mods) {
        modules.add( mods );
    }

    public void renderModules() {
        this.modules.forEach((mod) -> {
            if (mod.isEnabled() && mod instanceof RenderModule) {
                ((RenderModule)mod).drawModule();
            }
        });
    }
}
