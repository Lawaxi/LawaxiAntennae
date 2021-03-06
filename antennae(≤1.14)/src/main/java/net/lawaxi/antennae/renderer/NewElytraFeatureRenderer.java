package net.lawaxi.antennae.renderer;


import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lawaxi.antennae.utils.Capes;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.ElytraEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class NewElytraFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    private static final Identifier SKIN = new Identifier("textures/entity/elytra.png");
    private final ElytraEntityModel<T> elytra = new ElytraEntityModel();

    public NewElytraFeatureRenderer(FeatureRendererContext<T, M> context) {
        super(context);
    }

    public void render(T livingEntity, float f, float g, float h, float i, float j, float k, float l) {
        ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
        if (itemStack.getItem() == Items.ELYTRA) {
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            if (livingEntity instanceof AbstractClientPlayerEntity) {
                AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)livingEntity;
                if (abstractClientPlayerEntity.canRenderElytraTexture() && abstractClientPlayerEntity.getElytraTexture() != null) {
                    this.bindTexture(abstractClientPlayerEntity.getElytraTexture());
                } else if (abstractClientPlayerEntity.canRenderCapeTexture() && abstractClientPlayerEntity.getCapeTexture() != null && abstractClientPlayerEntity.isPartVisible(PlayerModelPart.CAPE)) {
                    this.bindTexture(abstractClientPlayerEntity.getCapeTexture());
                } else {

                    Identifier id = Capes.get(abstractClientPlayerEntity.getUuidAsString().replace("-",""));
                    if(id==null)
                        this.bindTexture(SKIN);
                    else
                        this.bindTexture(id);
                }
            } else {
                this.bindTexture(SKIN);
            }

            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.0F, 0.0F, 0.125F);
            this.elytra.setAngles(livingEntity, f, g, i, j, k, l);
            this.elytra.render(livingEntity, f, g, i, j, k, l);
            if (itemStack.hasEnchantments()) {
                ArmorFeatureRenderer.renderEnchantedGlint(this::bindTexture, livingEntity, this.elytra, f, g, h, i, j, k, l);
            }

            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean hasHurtOverlay() {
        return false;
    }
}