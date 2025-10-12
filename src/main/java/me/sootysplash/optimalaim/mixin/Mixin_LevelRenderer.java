package me.sootysplash.optimalaim.mixin;

//? if >=1.21.6
import com.mojang.blaze3d.buffers.GpuBufferSlice;
//? if >=1.21.2
import com.mojang.blaze3d.resource.GraphicsResourceAllocator;
//? if <=1.20.4
/*import com.mojang.blaze3d.vertex.PoseStack;*/
import me.sootysplash.optimalaim.OptimalAim;
import net.minecraft.client.Camera;
//? if >=1.21
import net.minecraft.client.DeltaTracker;
//? if <1.21.6
/*import net.minecraft.client.renderer.GameRenderer;*/
import net.minecraft.client.renderer.LevelRenderer;
//? if <1.21.4
/*import net.minecraft.client.renderer.LightTexture;*/
import org.joml.Matrix4f;
//? if >=1.21.6
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class Mixin_LevelRenderer {
    @Inject(method = "renderLevel", at = @At("RETURN"))
    private void afterRender(
            //? if <=1.20.4
            /*PoseStack poseStack, float partialTick, long finishNanoTime,*/
            //? if >=1.21.2
            GraphicsResourceAllocator graphicsResourceAllocator,
            //? if >=1.21
            DeltaTracker deltaTracker,
            boolean renderBlockOutline, Camera camera,
            //? if <1.21.6
            /*GameRenderer gameRenderer,*/
            //? if <1.21.4
            /*LightTexture lightTexture,*/
            //? if >=1.21
            Matrix4f frustumMatrix,
            Matrix4f projectionMatrix,
            //? if >=1.21.9
            /*Matrix4f cullingProjectionMatrix,*/
            //? if >=1.21.6
            GpuBufferSlice fogBuffer, Vector4f fogColor, boolean renderSky,
            CallbackInfo ci
    ) {
        OptimalAim.INSTANCE.render(camera);
    }
}
