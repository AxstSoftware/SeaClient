package io.github.sdxqw.module.settings.impl;

import io.github.sdxqw.module.Module;
import io.github.sdxqw.module.settings.Settings;
import lombok.Getter;
import lombok.Setter;

public class BooleanSettings extends Settings {

    @Getter @Setter
    public boolean enabled;

    public BooleanSettings(String name, boolean enabled, Module module) {
        this.name = name;
        this.enabled = enabled;
        this.module = module;
        shouldRender = true;
    }

    public BooleanSettings(String name, boolean enabled, Module module, boolean shouldRender) {
        this.name = name;
        this.enabled = enabled;
        this.module = module;
        this.shouldRender = shouldRender;
    }

}
