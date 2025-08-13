package me.sootysplash.optimalaim.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.*;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.gui.ValueFormatters;
import dev.isxander.yacl3.platform.YACLPlatform;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class OptimalAimConfig {
    public static final ConfigClassHandler<OptimalAimConfig> CONFIG = ConfigClassHandler.createBuilder(OptimalAimConfig.class)
            .id(YACLPlatform.rl("optimalaim", "config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(YACLPlatform.getConfigDir().resolve("optimalaim.json5"))
                    .setJson5(true)
                    .build())
            .build();

    public static Screen configScreen(@Nullable Screen parent) {
        return CONFIG.generateGui().generateScreen(parent);
    }

    public static OptimalAimConfig instance() {
        return CONFIG.instance();
    }

    @AutoGen(category = "general", group = "main")
    @MasterTickBox({ "hitbox", "onlyTargetPlayers", "onlyTargetPlayers", "showWhileHit", "cubeSize", "distance", "entityLimit", "fillColor" })
    @CustomName("optimalaim.config.enabled.name")
    @CustomDescription("optimalaim.config.enabled.description")
    @SerialEntry(comment = "This option disables all the other options in this group")
    public boolean enabled = true;

    @AutoGen(category = "general", group = "behavior")
    @Boolean(formatter = dev.isxander.yacl3.config.v2.api.autogen.Boolean.Formatter.YES_NO, colored = true)
    @CustomName("optimalaim.config.hitbox.name")
    @CustomDescription("optimalaim.config.hitbox.description")
    @SerialEntry(comment = "This is a cool comment omg this is amazing")
    public boolean hitbox = true;

    @AutoGen(category = "general", group = "behavior")
    @Boolean(formatter = dev.isxander.yacl3.config.v2.api.autogen.Boolean.Formatter.YES_NO, colored = true)
    @CustomName("optimalaim.config.onlyTargetPlayers.name")
    @CustomDescription("optimalaim.config.onlyTargetPlayers.description")
    @SerialEntry(comment = "This is a cool comment omg this is amazing")
    public boolean onlyTargetPlayers = false;

    @AutoGen(category = "general", group = "behavior")
    @Boolean(formatter = dev.isxander.yacl3.config.v2.api.autogen.Boolean.Formatter.YES_NO, colored = true)
    @CustomName("optimalaim.config.showWhileHit.name")
    @CustomDescription("optimalaim.config.showWhileHit.description")
    @SerialEntry(comment = "This is a cool comment omg this is amazing")
    public boolean showWhileHit = true;

    @AutoGen(category = "general", group = "appearance")
    @DoubleSlider(min = 0.1, max = 1.0, step = 0.05)
    @CustomName("optimalaim.config.cubeSize.name")
    @CustomDescription("optimalaim.config.cubeSize.description")
    @SerialEntry public double cubeSize = 0.6;

    @AutoGen(category = "general", group = "behavior")
    @DoubleSlider(min = 0.1, max = 32.0, step = 0.1)
    @FormatTranslation("optimalaim.config.unit.blocks")
    @CustomName("optimalaim.config.distance.name")
    @CustomDescription("optimalaim.config.distance.description")
    @SerialEntry public double distance = 12.0;

    @AutoGen(category = "general", group = "behavior")
    @IntField(min = 0, max = 100)
    @FormatTranslation("optimalaim.config.unit.entities")
    @CustomName("optimalaim.config.entityLimit.name")
    @CustomDescription("optimalaim.config.entityLimit.description")
    @SerialEntry public int entityLimit = 1;

    @AutoGen(category = "general", group = "appearance")
    @ColorField
    @CustomName("optimalaim.config.fillColor.name")
    @CustomDescription("optimalaim.config.fillColor.description")
    @SerialEntry public Color fillColor = new Color(0x66AF2310, true);
}
