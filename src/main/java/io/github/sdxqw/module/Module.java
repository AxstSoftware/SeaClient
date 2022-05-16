package io.github.sdxqw.module;

import io.github.sdxqw.utils.interfaces.IHelper;
import lombok.Getter;
import lombok.Setter;

public class Module implements IHelper {

    @Getter
    public String name, description;

    @Getter @Setter
    public boolean enabled;

    public Module(String name, String description) {
        this.name = name;
        this.description = description;
        setEnabled( true );
    }

    public void toggleModule() {
        this.setEnabled(!this.enabled);
    }

    public boolean isDisabled() {
        return !enabled;
    }

}
