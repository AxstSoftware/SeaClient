package io.github.sdxqw.module.settings.impl;

import io.github.sdxqw.module.Module;
import io.github.sdxqw.module.settings.Settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModeSettings extends Settings {

    public int index;
    public List<String> modes;

    public ModeSettings(String name, Module module, String defaultMode, String... modes) {
        this.name = name;
        this.module = module;
        this.modes = Arrays.asList( modes );
        this.index = this.modes.indexOf( defaultMode );
        shouldRender = true;
    }

    public ModeSettings(String name, Module module, boolean shouldRender, String defaultMode, String... modes) {
        this.name = name;
        this.module = module;
        this.modes = Arrays.asList(modes);
        this.index = this.modes.indexOf(defaultMode);
        this.shouldRender = shouldRender;
    }

    public String getMode() {
        return this.modes.get(this.index);
    }

    public void setMode(String mode) {
        this.index = this.modes.indexOf(mode);
    }

    public boolean is(String mode) {
        return (this.index == this.modes.indexOf(mode));
    }

    public void cycle() {
        if (this.index < this.modes.size() - 1) {
            this.index++;
        } else {
            this.index = 0;
        }
    }

    public String getValueName() {
        return this.modes.get(this.index);
    }

    public void increment() {
        if (this.index < this.modes.size() - 1) {
            this.index++;
        } else {
            this.index = 0;
        }
    }
}
