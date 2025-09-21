package me.sootysplash.optimalaim.render;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.deftu.omnicore.api.client.render.DefaultVertexFormats;
import dev.deftu.omnicore.api.client.render.DrawMode;
import dev.deftu.omnicore.api.client.render.pipeline.OmniRenderPipeline;
import dev.deftu.omnicore.api.client.render.stack.OmniMatrixStack;
import dev.deftu.omnicore.api.client.render.stack.OmniMatrixStacks;
import dev.deftu.omnicore.api.client.render.vertex.OmniBufferBuilder;
import dev.deftu.omnicore.api.client.render.vertex.OmniBufferBuilders;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.awt.*;

public class BoxRenderer {
    private final PipelineProvider pipelines;

    public BoxRenderer(PipelineProvider pipelines) {
        this.pipelines = pipelines;
    }

    public void drawBox(PoseStack pose, AABB cube, Color color) {
        AABB local = cube.move(new Vec3(cube.minX, cube.minY, cube.minZ).reverse());

        float x1 = (float) local.minX, y1 = (float) local.minY, z1 = (float) local.minZ;
        float x2 = (float) local.maxX, y2 = (float) local.maxY, z2 = (float) local.maxZ;

        OmniRenderPipeline pipeline = pipelines.get();

        OmniBufferBuilder omniBufferBuilder = OmniBufferBuilders.create(DrawMode.QUADS, pipeline.getVertexFormat());
        addBoxQuads(omniBufferBuilder, OmniMatrixStacks.wrap(pose), x1, y1, z1, x2, y2, z2, color);
        omniBufferBuilder.buildOrNull().draw(pipeline, ignore -> {});
    }

    private void addBoxQuads(
            OmniBufferBuilder builder, OmniMatrixStack stack,
            float x1, float y1, float z1, float x2, float y2, float z2,
            Color color
    ) {
        builder.vertex(stack, x1, y1, z1).color(color).next();
        builder.vertex(stack, x1, y2, z1).color(color).next();
        builder.vertex(stack, x2, y2, z1).color(color).next();
        builder.vertex(stack, x2, y1, z1).color(color).next();

        builder.vertex(stack, x1, y1, z1).color(color).next();
        builder.vertex(stack, x1, y2, z1).color(color).next();
        builder.vertex(stack, x1, y2, z2).color(color).next();
        builder.vertex(stack, x1, y1, z2).color(color).next();

        builder.vertex(stack, x1, y2, z1).color(color).next();
        builder.vertex(stack, x1, y2, z2).color(color).next();
        builder.vertex(stack, x2, y2, z2).color(color).next();
        builder.vertex(stack, x2, y2, z1).color(color).next();

        builder.vertex(stack, x1, y1, z1).color(color).next();
        builder.vertex(stack, x1, y1, z2).color(color).next();
        builder.vertex(stack, x2, y1, z2).color(color).next();
        builder.vertex(stack, x2, y1, z1).color(color).next();

        builder.vertex(stack, x2, y1, z1).color(color).next();
        builder.vertex(stack, x2, y2, z1).color(color).next();
        builder.vertex(stack, x2, y2, z2).color(color).next();
        builder.vertex(stack, x2, y1, z2).color(color).next();

        builder.vertex(stack, x1, y1, z2).color(color).next();
        builder.vertex(stack, x1, y2, z2).color(color).next();
        builder.vertex(stack, x2, y2, z2).color(color).next();
        builder.vertex(stack, x2, y1, z2).color(color).next();
    }
}