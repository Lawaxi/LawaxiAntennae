package net.lawaxi.antennae.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class LawaxiFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    public LawaxiFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float g, float h, float j, float k, float l) {


        if ("Lawaxi".equals(abstractClientPlayerEntity.getName().getString()) && abstractClientPlayerEntity.hasSkinTexture() && !abstractClientPlayerEntity.isInvisible()) {
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntitySolid(abstractClientPlayerEntity.getSkinTexture()));
            int m = LivingEntityRenderer.getOverlay(abstractClientPlayerEntity, 0.0F);

            matrixStack.push();
            this.getContextModel().head.setTextureOffset(26, 0).addCuboid(-3.0F, -12.0F, -5.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
            this.getContextModel().head.setTextureOffset(30, 0).addCuboid(2.0F, -12.0F, -5.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
            this.getContextModel().head.render(matrixStack,vertexConsumer,i,m);
            matrixStack.pop();

        }
    }
}
