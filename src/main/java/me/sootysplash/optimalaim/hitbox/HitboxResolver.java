package me.sootysplash.optimalaim.hitbox;

import me.sootysplash.optimalaim.math.GeometryUtil;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class HitboxResolver {

    public AABB resolveTargetHitbox(Entity entity, LocalPlayer player, float partialTicks) {
        AABB base = interpolateEntityHitbox(entity, partialTicks);
        if (entity instanceof EnderDragon dragon) {
            Vec3 eye = player.getEyePosition(partialTicks);
            AABB closest = closestDragonPartHitbox(dragon, eye, partialTicks);
            return closest != null ? closest : base;
        }
        return base;
    }

    public AABB interpolateEntityHitbox(Entity entity, float partialTicks) {
        return entity.getBoundingBox()
                .move(entity.position().scale(-1))
                .move(entity.getPosition(partialTicks));
    }

    public AABB closestDragonPartHitbox(EnderDragon dragon, Vec3 eye, float partialTicks) {
        AABB closest = null;
        double best = Double.MAX_VALUE;
        for (EnderDragonPart part : dragon.getSubEntities()) {
            AABB box = part.getBoundingBox()
                    .move(part.position().scale(-1))
                    .move(part.getPosition(partialTicks));
            double d = eye.distanceTo(GeometryUtil.closestPointToBox(box, eye));
            if (d < best) {
                best = d;
                closest = box;
            }
        }
        return closest;
    }
}