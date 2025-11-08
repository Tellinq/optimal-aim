package me.sootysplash.optimalaim.render;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.deftu.omnicore.api.client.render.DefaultVertexFormats;
import dev.deftu.omnicore.api.client.render.DrawMode;
import dev.deftu.omnicore.api.client.render.pipeline.OmniRenderPipeline;
import dev.deftu.omnicore.api.client.render.stack.OmniPoseStack;
import dev.deftu.omnicore.api.client.render.stack.OmniPoseStacks;
import dev.deftu.omnicore.api.client.render.vertex.OmniBufferBuilder;
import dev.deftu.omnicore.api.client.render.vertex.OmniBufferBuilders;
import dev.deftu.omnicore.api.data.aabb.OmniAABB;
import dev.deftu.omnicore.api.data.vec.OmniVec3d;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.awt.*;

public class BoxRenderer {
    private final PipelineProvider pipelines;

    public BoxRenderer(PipelineProvider pipelines) {
        this.pipelines = pipelines;
    }

    public void drawBox(PoseStack pose, OmniAABB cube, Color color) {
        OmniAABB local = cube.offset(new OmniVec3d(cube.getMinX(), cube.getMinY(), cube.getMinZ()).toVanilla().reverse());

        float x1 = (float) local.getMinX(), y1 = (float) local.getMinY(), z1 = (float) local.getMinZ();
        float x2 = (float) local.getMaxX(), y2 = (float) local.getMaxY(), z2 = (float) local.getMaxZ();

        OmniRenderPipeline pipeline = pipelines.get();

        OmniBufferBuilder omniBufferBuilder = OmniBufferBuilders.create(DrawMode.QUADS, pipeline.getVertexFormat());
        addBoxQuads(omniBufferBuilder, OmniPoseStacks.wrap(pose), x1, y1, z1, x2, y2, z2, color);
        omniBufferBuilder.buildOrNull().drawAndClose(pipeline);
    }

    private void addBoxQuads(
            OmniBufferBuilder builder, OmniPoseStack pose,
            float x1, float y1, float z1, float x2, float y2, float z2,
            Color color
    ) {
        builder.vertex(pose, x1, y1, z1).color(color).next();
        builder.vertex(pose, x1, y2, z1).color(color).next();
        builder.vertex(pose, x2, y2, z1).color(color).next();
        builder.vertex(pose, x2, y1, z1).color(color).next();

        builder.vertex(pose, x1, y1, z1).color(color).next();
        builder.vertex(pose, x1, y2, z1).color(color).next();
        builder.vertex(pose, x1, y2, z2).color(color).next();
        builder.vertex(pose, x1, y1, z2).color(color).next();

        builder.vertex(pose, x1, y2, z1).color(color).next();
        builder.vertex(pose, x1, y2, z2).color(color).next();
        builder.vertex(pose, x2, y2, z2).color(color).next();
        builder.vertex(pose, x2, y2, z1).color(color).next();

        builder.vertex(pose, x1, y1, z1).color(color).next();
        builder.vertex(pose, x1, y1, z2).color(color).next();
        builder.vertex(pose, x2, y1, z2).color(color).next();
        builder.vertex(pose, x2, y1, z1).color(color).next();

        builder.vertex(pose, x2, y1, z1).color(color).next();
        builder.vertex(pose, x2, y2, z1).color(color).next();
        builder.vertex(pose, x2, y2, z2).color(color).next();
        builder.vertex(pose, x2, y1, z2).color(color).next();

        builder.vertex(pose, x1, y1, z2).color(color).next();
        builder.vertex(pose, x1, y2, z2).color(color).next();
        builder.vertex(pose, x2, y2, z2).color(color).next();
        builder.vertex(pose, x2, y1, z2).color(color).next();
    }
}