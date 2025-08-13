package me.sootysplash.optimalaim.camera;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class CameraPose {
    public PoseStack makeCameraAlignedPose(LocalPlayer player, AABB cube, float partialTicks) {
        PoseStack stack = new PoseStack();
        stack.mulPose(Axis.XP.rotationDegrees(player.getXRot()));
        stack.mulPose(Axis.YP.rotationDegrees(player.getYRot() + 180.0F));
        Vec3 camPos = player.getEyePosition(partialTicks);
        Vec3 targetPos = new Vec3(cube.minX, cube.minY, cube.minZ).subtract(camPos);
        stack.translate(targetPos.x, targetPos.y, targetPos.z);
        return stack;
    }
}
