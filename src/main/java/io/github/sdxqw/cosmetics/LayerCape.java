package io.github.sdxqw.cosmetics;

import io.github.sdxqw.utils.interfaces.IHelper;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.MathHelper;

public class LayerCape extends ModelBase implements LayerRenderer<AbstractClientPlayer>, IHelper {

    private final ModelRenderer bipedCape;

    public LayerCape() {
        (this.bipedCape = new ModelRenderer(this, 0, 0).setTextureSize(22, 17).setTextureOffset(0, 0)).addBox(-5.0f, 0.0f, -1.0f, 10, 16, 1);
    }

    @Override
    public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
        final AbstractClientPlayer player = (AbstractClientPlayer) entitylivingbaseIn;
        if (player.hasPlayerInfo() && !player.isInvisible() && player.isWearing(EnumPlayerModelParts.CAPE) && CosmeticsManager.hasCape(player.getUniqueID().toString())) {
            this.render(entitylivingbaseIn, p_177141_2_, p_177141_3_, 0.0f, p_177141_5_, partialTicks, scale);
        }
    }

    public void render(final Entity entityIn, final float p_78088_2_, final float p_78088_3_, final float p_78088_4_, final float p_78088_5_, final float partialTicks, final float scale) {
        final AbstractClientPlayer entitylivingbaseIn = (AbstractClientPlayer) entityIn;
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        minecraft.getTextureManager().bindTexture(CosmeticsManager.cosmetics.get(entitylivingbaseIn.getName()).getCapeTexture());
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 0.0F, 0.125F);
        double d0 = entitylivingbaseIn.prevChasingPosX
                + (entitylivingbaseIn.chasingPosX - entitylivingbaseIn.prevChasingPosX) * (double) partialTicks
                - (entitylivingbaseIn.prevPosX
                + (entitylivingbaseIn.posX - entitylivingbaseIn.prevPosX) * (double) partialTicks);
        double d1 = entitylivingbaseIn.prevChasingPosY
                + (entitylivingbaseIn.chasingPosY - entitylivingbaseIn.prevChasingPosY) * (double) partialTicks
                - (entitylivingbaseIn.prevPosY
                + (entitylivingbaseIn.posY - entitylivingbaseIn.prevPosY) * (double) partialTicks);
        double d2 = entitylivingbaseIn.prevChasingPosZ
                + (entitylivingbaseIn.chasingPosZ - entitylivingbaseIn.prevChasingPosZ) * (double) partialTicks
                - (entitylivingbaseIn.prevPosZ
                + (entitylivingbaseIn.posZ - entitylivingbaseIn.prevPosZ) * (double) partialTicks);
        float f = entitylivingbaseIn.prevRenderYawOffset
                + (entitylivingbaseIn.renderYawOffset - entitylivingbaseIn.prevRenderYawOffset) * partialTicks;
        double d3 = (double) MathHelper.sin(f * (float) Math.PI / 180.0F);
        double d4 = (double) (-MathHelper.cos(f * (float) Math.PI / 180.0F));
        float f1 = (float) d1 * 10.0F;
        f1 = MathHelper.clamp_float(f1, -3.0F, 12.0F);
        float f2 = (float) (d0 * d3 + d2 * d4) * 100.0F;
        float f3 = (float) (d0 * d4 - d2 * d3) * 100.0F;

        if (f2 < 0.0F) {
            f2 = 0.0F;
        }

        float f4 = entitylivingbaseIn.prevCameraYaw
                + (entitylivingbaseIn.cameraYaw - entitylivingbaseIn.prevCameraYaw) * partialTicks;
        f1 = f1 + MathHelper.sin((entitylivingbaseIn.prevDistanceWalkedModified
                + (entitylivingbaseIn.distanceWalkedModified - entitylivingbaseIn.prevDistanceWalkedModified)
                * partialTicks)
                * 6.0F) * 32.0F * f4;

        if (entitylivingbaseIn.isSneaking()) {

            f1 += 35.0F;
            GlStateManager.translate(0.0, 0.07, -0.2);
        }

        GlStateManager.rotate(6.0F + f2 / 2.0F + f1, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(f3 / 2.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(-f3 / 2.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);

        this.bipedCape.render(scale);

        GlStateManager.popMatrix();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
