package me.sootysplash.optimalaim.config;

//? if modmenu {
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Entrypoint("modmenu")
@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return OptimalAimConfig::configScreen;
    }
}
//?}
