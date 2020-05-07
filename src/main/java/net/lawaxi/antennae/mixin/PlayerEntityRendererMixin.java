package net.lawaxi.antennae.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lawaxi.antennae.renderer.LawaxiFeatureRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin extends LivingEntityRendererMixin{

	@Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/client/render/entity/EntityRenderDispatcher;Z)V")
	private void onFeatureAdd(CallbackInfo info) {
		this.addFeature(new LawaxiFeatureRenderer((PlayerEntityRenderer)(Object)this));
	}
}
