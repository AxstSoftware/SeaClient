package io.github.sdxqw.launch;

import com.google.common.collect.Lists;
import io.github.sdxqw.StartClient;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.List;

public class Tweaker implements ITweaker {

    private final List<String> launchArguments = Lists.newArrayList();

    public final void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        this.launchArguments.addAll(args);
        if (!args.contains("--version") && profile != null) {
            this.launchArguments.add("--version");
            this.launchArguments.add(profile);
        }

        if (!args.contains("--assetDir") && assetsDir != null) {
            this.launchArguments.add("--assetDir");
            this.launchArguments.add(assetsDir.getAbsolutePath());
        }

        if (!args.contains("--gameDir") && gameDir != null) {
            this.launchArguments.add("--gameDir");
            this.launchArguments.add(gameDir.getAbsolutePath());
        }

    }

    public void injectIntoClassLoader(final LaunchClassLoader classLoader) {
        MixinBootstrap.init();
        final MixinEnvironment env = MixinEnvironment.getDefaultEnvironment();
        //Remove StartClient.instance.tweaker and put the same of .json
        //if you don't want that instance there.
        Mixins.addConfiguration("mixins." + StartClient.instance.tweaker + ".json");
        if (env.getObfuscationContext() == null) {
            env.setObfuscationContext("notch");
        }
        env.setSide(MixinEnvironment.Side.CLIENT);
    }

    public String getLaunchTarget() {
        return MixinBootstrap.getPlatform().getLaunchTarget();
    }

    public String[] getLaunchArguments() {
        return this.launchArguments.toArray(new String[0]);
    }
}
