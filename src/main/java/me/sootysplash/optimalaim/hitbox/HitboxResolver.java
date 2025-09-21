package me.sootysplash.optimalaim.hitbox;

import dev.deftu.omnicore.api.data.DistanceMetric;
import dev.deftu.omnicore.api.data.aabb.OmniAABB;
import dev.deftu.omnicore.api.data.vec.OmniVec3d;
import me.sootysplash.optimalaim.math.GeometryUtil;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;

public class HitboxResolver {

    public OmniAABB resolveTargetHitbox(Entity entity, LocalPlayer player, float partialTicks) {
        OmniAABB base = interpolateEntityHitbox(entity, partialTicks);
        if (entity instanceof EnderDragon dragon) {
            OmniVec3d eye = new OmniVec3d(player.getEyePosition(partialTicks));
            OmniAABB closest = closestDragonPartHitbox(dragon, eye, partialTicks);
            return closest != null ? closest : base;
        }
        return base;
    }

    public OmniAABB interpolateEntityHitbox(Entity entity, float partialTicks) {
        return new OmniAABB(entity.getBoundingBox())
                .offset(entity.position().scale(-1))
                .offset(entity.getPosition(partialTicks));
    }

    public OmniAABB closestDragonPartHitbox(EnderDragon dragon, OmniVec3d eye, float partialTicks) {
        OmniAABB closest = null;
        double best = Double.MAX_VALUE;
        for (EnderDragonPart part : dragon.getSubEntities()) {
            OmniAABB box = new OmniAABB(part.getBoundingBox())
                    .offset(part.position().scale(-1))
                    .offset(part.getPosition(partialTicks));
            double d = eye.distanceTo(GeometryUtil.closestPointToBox(box, eye), DistanceMetric.EUCLIDEAN);
            if (d < best) {
                best = d;
                closest = box;
            }
        }
        return closest;
    }
}