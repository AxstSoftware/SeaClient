package io.github.sdxqw.utils.interfaces;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.world.World;

public interface IHelper {
    Minecraft minecraft = Minecraft.getMinecraft();
    World world = Minecraft.getMinecraft().theWorld;
    EntityPlayerSP thePlayer = Minecraft.getMinecraft().thePlayer;
    FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
}
