package me.sootysplash.optimalaim.math;

import dev.deftu.omnicore.api.data.aabb.OmniAABB;
import dev.deftu.omnicore.api.data.vec.OmniVec3d;
import dev.deftu.omnicore.api.data.vec.OmniVec3i;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class GeometryUtil {

    public static OmniVec3d closestPointToBox(OmniAABB box, Vec3 point) {
        double x = Math.min(Math.max(point.x, box.getMinX()), box.getMaxX());
        double y = Math.min(Math.max(point.y, box.getMinY()), box.getMaxY());
        double z = Math.min(Math.max(point.z, box.getMinZ()), box.getMaxZ());
        return new OmniVec3d(x, y, z);
    }

    public OmniAABB buildCubeAround(OmniVec3d center, double halfSize) {
        OmniVec3d min = center.plus(-halfSize);
        OmniVec3d max = center.plus(halfSize);
        return new OmniAABB(min, max);
    }

    public OmniAABB clampCubeToHitbox(OmniAABB cube, OmniAABB bounds) {

        OmniVec3d min = cube.getMin();
        OmniVec3d max = cube.getMax();

        OmniVec3d minComp = new OmniVec3d(
                -(min.getX() - Math.max(min.getX(), bounds.getMinX())),
                -(min.getY() - Math.max(min.getY(), bounds.getMinY())),
                -(min.getZ() - Math.max(min.getZ(), bounds.getMinZ()))
        );
        OmniVec3d maxComp = new OmniVec3d(
                -(max.getX() - Math.min(max.getX(), bounds.getMaxX())),
                -(max.getY() - Math.min(max.getY(), bounds.getMaxY())),
                -(max.getZ() - Math.min(max.getZ(), bounds.getMaxZ()))
        );

        OmniVec3d newMin = min.plus(minComp.plus(maxComp));
        OmniVec3d newMax = max.plus(maxComp.plus(minComp));
        return new OmniAABB(newMin, newMax);
    }
}
