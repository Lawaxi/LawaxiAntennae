package net.lawaxi.antennae.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lawaxi.antennae.renderer.LawaxiFeatureRenderer;
import net.lawaxi.antennae.renderer.NewCapeFeatureRenderer;
import net.lawaxi.antennae.renderer.NewElytraFeatureRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin extends LivingEntityRendererMixin{

	@Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/client/render/entity/EntityRenderDispatcher;Z)V")
	private void onFeatureAdd(CallbackInfo info) {
		this.addFeature(new LawaxiFeatureRenderer((PlayerEntityRenderer)(Object)this));
		this.addFeature(new NewCapeFeatureRenderer((PlayerEntityRenderer)(Object)this));
		this.addFeature(new NewElytraFeatureRenderer((PlayerEntityRenderer)(Object)this));

		//去除原版鞘翅
		for(FeatureRenderer a: this.features){
			if(a instanceof ElytraFeatureRenderer){
				this.features.remove(a);
				return;
			}
		}
	}
}
