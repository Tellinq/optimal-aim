package me.sootysplash.optimalaim.select;

import com.google.common.collect.Streams;
import dev.deftu.omnicore.client.OmniClient;
import dev.deftu.omnicore.client.OmniClientPlayer;
import dev.deftu.omnicore.client.render.OmniGameRendering;
import me.sootysplash.optimalaim.config.OptimalAimConfig;
import me.sootysplash.optimalaim.math.GeometryUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.List;

import static java.lang.Math.atan2;
import static net.minecraft.util.Mth.wrapDegrees;

public class TargetSelector {

    public List<Entity> getNearbyEntities(LocalPlayer player, float tickDelta) {
        ClientLevel world = OmniClient.getInstance().level;
        if (world == null || player == null) return List.of();

        Vec3 eye = player.getEyePosition(tickDelta);
        double maxDist = OptimalAimConfig.instance().distance;
        boolean onlyPlayers = OptimalAimConfig.instance().onlyTargetPlayers;
        boolean showWhileHit = OptimalAimConfig.instance().showWhileHit;

        return Streams.stream(world.entitiesForRendering())
                .filter(e -> isCandidate(player, e, eye, maxDist, onlyPlayers, showWhileHit))
                .sorted(Comparator.comparingDouble(this::yaw))
                .toList();
    }

    private boolean isCandidate(LocalPlayer player, Entity entity, Vec3 eye, double maxDist, boolean onlyPlayers, boolean showWhileHit) {
        if (entity == player) return false;
        if (!(onlyPlayers ? entity instanceof Player : entity instanceof LivingEntity)) return false;
        if (!player.hasLineOfSight(entity)) return false;
        if (!entity.isAlive()) return false;
        if (!entity.isAttackable() || entity.isInvisible() || entity.hasPassenger(player)) return false;
        if (!showWhileHit && entity instanceof LivingEntity le && le.hurtTime > 0) return false;

        Vec3 closest = GeometryUtil.closestPointToBox(entity.getBoundingBox(), eye);
        return eye.distanceTo(closest) <= maxDist;
    }

    public float yaw(Entity entity) {
        Vec3 target = GeometryUtil.closestPointToBox(entity.getBoundingBox(), OmniClientPlayer.getInstance().getEyePosition(OmniGameRendering.getTickDelta(true)));
        float amount = (float) Math.toDegrees(atan2(target.z - OmniClientPlayer.getPosZ(), target.x - OmniClientPlayer.getPosX())) - 90.0f;
        amount = Math.abs(wrapDegrees(amount - OmniClientPlayer.getYaw()));
        return amount;
    }
}
