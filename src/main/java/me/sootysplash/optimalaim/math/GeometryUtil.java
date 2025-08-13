package me.sootysplash.optimalaim.math;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class GeometryUtil {

    public static Vec3 closestPointToBox(AABB box, Vec3 point) {
        double x = Math.min(Math.max(point.x, box.minX), box.maxX);
        double y = Math.min(Math.max(point.y, box.minY), box.maxY);
        double z = Math.min(Math.max(point.z, box.minZ), box.maxZ);
        return new Vec3(x, y, z);
    }

    public AABB buildCubeAround(Vec3 center, double halfSize) {
        Vec3 min = center.add(-halfSize, -halfSize, -halfSize);
        Vec3 max = center.add( halfSize,  halfSize,  halfSize);
        return new AABB(min, max);
    }

    public AABB clampCubeToHitbox(AABB cube, AABB bounds) {
        Vec3 min = new Vec3(cube.minX, cube.minY, cube.minZ);
        Vec3 max = new Vec3(cube.maxX, cube.maxY, cube.maxZ);

        Vec3 minComp = new Vec3(
                -(min.x() - Math.max(min.x(), bounds.minX)),
                -(min.y() - Math.max(min.y(), bounds.minY)),
                -(min.z() - Math.max(min.z(), bounds.minZ))
        );
        Vec3 maxComp = new Vec3(
                -(max.x() - Math.min(max.x(), bounds.maxX)),
                -(max.y() - Math.min(max.y(), bounds.maxY)),
                -(max.z() - Math.min(max.z(), bounds.maxZ))
        );

        Vec3 newMin = min.add(minComp.add(maxComp));
        Vec3 newMax = max.add(maxComp.add(minComp));
        return new AABB(newMin, newMax);
    }
}
