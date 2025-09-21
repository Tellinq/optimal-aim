package me.sootysplash.optimalaim.camera;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.deftu.omnicore.api.data.aabb.OmniAABB;
import dev.deftu.omnicore.api.data.vec.OmniVec3d;
import net.minecraft.client.Camera;

public class CameraPose {
    public PoseStack makeCameraAlignedPose(Camera camera, OmniAABB cube) {
        PoseStack stack = new PoseStack();
        stack.mulPose(Axis.XP.rotationDegrees(camera.getXRot()));
        stack.mulPose(Axis.YP.rotationDegrees(camera.getYRot() + 180.0F));
        OmniVec3d camPos = new OmniVec3d(camera.getPosition());
        OmniVec3d targetPos = new OmniVec3d(cube.getMinX(), cube.getMinY(), cube.getMinZ()).minus(camPos);
        stack.translate(targetPos.getX(), targetPos.getY(), targetPos.getZ());
        return stack;
    }
}
