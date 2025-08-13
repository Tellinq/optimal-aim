package me.sootysplash.optimalaim.render;

import dev.deftu.omnicore.client.render.pipeline.DrawModes;
import dev.deftu.omnicore.client.render.pipeline.OmniRenderPipeline;
import dev.deftu.omnicore.client.render.pipeline.OmniRenderPipelineBuilder;
import dev.deftu.omnicore.client.render.pipeline.VertexFormats;
import dev.deftu.omnicore.client.render.state.OmniManagedBlendState;
import dev.deftu.omnicore.common.OmniIdentifier;

public final class PipelineProvider {
    private OmniRenderPipeline pipeline;

    public OmniRenderPipeline get() {
        if (pipeline == null) {
            OmniRenderPipelineBuilder builder = OmniRenderPipeline.builderWithDefaultShader(
                    OmniIdentifier.create("testmod", "custom"),
                    VertexFormats.POSITION_COLOR,
                    DrawModes.QUADS
            );
            builder.blendState = OmniManagedBlendState.NORMAL;
            pipeline = builder.build();
        }
        return pipeline;
    }
}
