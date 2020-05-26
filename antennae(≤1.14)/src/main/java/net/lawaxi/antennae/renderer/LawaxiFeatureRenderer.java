package net.lawaxi.antennae.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;

@Environment(EnvType.CLIENT)
public class LawaxiFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    public LawaxiFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float g, float h, float i, float j, float k, float l) {
        if ("Lawaxi".equals(abstractClientPlayerEntity.getName().getString()) && abstractClientPlayerEntity.hasSkinTexture() && !abstractClientPlayerEntity.isInvisible()) {
            this.bindTexture(abstractClientPlayerEntity.getSkinTexture());

            for(int m = 0; m < 2; ++m) {
                GlStateManager.pushMatrix();
                getContextModel().head.setTextureOffset(26, 0).addCuboid(-3.0F, -12.0F, -5.0F, 1, 5, 1, 0.0F, false);
                getContextModel().head.setTextureOffset(30, 0).addCuboid(2.0F, -12.0F, -5.0F, 1, 5, 1, 0.0F, false);
                getContextModel().head.render(0.0625F);
                GlStateManager.popMatrix();
            }

        }
    }

    @Override
    public boolean hasHurtOverlay() {
        return true;
    }
}
