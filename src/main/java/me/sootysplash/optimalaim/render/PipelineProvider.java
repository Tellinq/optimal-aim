package me.sootysplash.optimalaim.render;

import dev.deftu.omnicore.api.OmniIdentifier;
import dev.deftu.omnicore.api.client.render.DefaultVertexFormats;
import dev.deftu.omnicore.api.client.render.DrawMode;
import dev.deftu.omnicore.api.client.render.pipeline.OmniRenderPipeline;
import dev.deftu.omnicore.api.client.render.pipeline.OmniRenderPipelineBuilder;
import dev.deftu.omnicore.api.client.render.pipeline.OmniRenderPipelines;
import dev.deftu.omnicore.api.client.render.state.OmniBlendState;

public final class PipelineProvider {
    private OmniRenderPipeline pipeline;

    public OmniRenderPipeline get() {
        if (pipeline == null) {

            OmniRenderPipelineBuilder builder = OmniRenderPipelines.builderWithDefaultShader(
                    OmniIdentifier.createOrNull("optimalaim", "boxrenderer"),
                    DefaultVertexFormats.POSITION_COLOR,
                    DrawMode.QUADS
            );
            builder.blendState = OmniBlendState.NORMAL;
            pipeline = builder.build();
        }
        return pipeline;
    }
}
