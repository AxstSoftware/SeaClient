package io.github.sdxqw.cosmetics;

import java.util.*;

import lombok.Getter;
import net.minecraft.util.*;

public class CosmeticData {
    @Getter
    private final List<CosmeticsManager.CosmeticList> cosmetics;
    @Getter
    private final String player;
    @Getter
    private final ResourceLocation capeTexture;

    public CosmeticData(final List<CosmeticsManager.CosmeticList> cosmetics, final String player, final ResourceLocation capeTexture) {
        this.cosmetics = cosmetics;
        this.player = player;
        this.capeTexture = capeTexture;
    }
}
