package io.github.sdxqw.cosmetics;

import java.util.*;

import lombok.Getter;
import net.minecraft.util.*;

public class CosmeticData {
    @Getter
    private final List<CosmeticsManager.CosmeticList> cosmetics;
    @Getter
    private final String data;
    @Getter
    private final ResourceLocation capeTexture;

    public CosmeticData(final List<CosmeticsManager.CosmeticList> cosmetics, final String data, final ResourceLocation capeTexture) {
        this.cosmetics = cosmetics;
        this.data = data;
        this.capeTexture = capeTexture;
    }
}
