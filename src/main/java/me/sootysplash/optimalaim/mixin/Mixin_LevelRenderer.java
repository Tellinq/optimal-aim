package me.sootysplash.optimalaim.mixin;

import me.sootysplash.optimalaim.OptimalAim;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class Mixin_LevelRenderer {
    @Inject(method = "renderLevel", at = @At("RETURN"))
    private void afterRender(CallbackInfo ci) {
        OptimalAim.INSTANCE.render();
    }
}
