package me.sootysplash.optimalaim;

import com.mojang.blaze3d.vertex.*;
import dev.deftu.omnicore.api.client.OmniClient;
import dev.deftu.omnicore.api.client.render.OmniRenderTicks;
import dev.deftu.omnicore.api.data.aabb.OmniAABB;
import dev.deftu.omnicore.api.data.vec.OmniVec3d;
import me.sootysplash.optimalaim.camera.CameraPose;
import me.sootysplash.optimalaim.config.OptimalAimConfig;
import me.sootysplash.optimalaim.hitbox.HitboxResolver;
import me.sootysplash.optimalaim.math.GeometryUtil;
import me.sootysplash.optimalaim.render.BoxRenderer;
import me.sootysplash.optimalaim.render.PipelineProvider;
import me.sootysplash.optimalaim.select.TargetSelector;
//? if fabric {
import net.fabricmc.api.ModInitializer;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
//?}
import net.minecraft.client.Camera;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;

//? if neoforge {
/*import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
*///?}


//? if neoforge {
/*@Mod(value = "@MODID@", dist = Dist.CLIENT)
*///?} else {
@Entrypoint
//?}
public class OptimalAim /*? if fabric {*/ implements ModInitializer /*?}*/ {
    private LocalPlayer player;
    public static OptimalAim INSTANCE;

    private final HitboxResolver hitboxes = new HitboxResolver();
    private final GeometryUtil geom = new GeometryUtil(); // or static methods if you prefer
    private final CameraPose cameraPose = new CameraPose();
    private final BoxRenderer boxRenderer = new BoxRenderer(new PipelineProvider());
    private final TargetSelector selector = new TargetSelector();

    //? if fabric {
    @Override
    public void onInitialize() {
        this.load();
    }
    //?}

    //? if neoforge {
    /*public OptimalAim() {
        this.load();
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (client, parent) -> OptimalAimConfig.configScreen(parent));
    }
	*///?}

    public void load() {
        OptimalAimConfig.CONFIG.load();
        INSTANCE = this;
    }

    public void render(Camera camera) {
        OptimalAimConfig config = OptimalAimConfig.instance();
        if (!config.enabled) return;

        if (player == null) {
            player = OmniClient.getPlayer();
            if (player == null) return;
        }

        float tickDelta = OmniRenderTicks.get(true);
        selector.getNearbyEntities(player, tickDelta).stream()
                .limit(config.entityLimit)
                .forEach(entity -> renderEntityBox(player, entity, camera, tickDelta, config));

    }

    private void renderEntityBox(LocalPlayer player, Entity entity, Camera camera, float tickDelta, OptimalAimConfig config) {
        OmniAABB target =  hitboxes.resolveTargetHitbox(entity, player, tickDelta);
        if (target == null) return;

        OmniVec3d optimal = GeometryUtil.closestPointToBox(target, new OmniVec3d(player.getEyePosition(tickDelta)));
        OmniAABB cube = geom.buildCubeAround(optimal, config.cubeSize / 5.0);
        if (config.hitbox) {
            cube = geom.clampCubeToHitbox(cube, target);
        }

        PoseStack pose = cameraPose.makeCameraAlignedPose(camera, cube);
        boxRenderer.drawBox(pose, cube, config.fillColor);
    }

}
