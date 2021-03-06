package johan24.mods.examplemod.world.gen;

import johan24.mods.examplemod.init.ModConfiguredFeatures;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

@Mod.EventBusSubscriber
public class ModWorldGen {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void addFeaturesToBiomes(final BiomeLoadingEvent event) {
        final BiomeGenerationSettings.Builder generation = event.getGeneration();

        final RegistryKey<Biome> biomeRegistryKey = RegistryKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(event.getName(), "Biome registry name was null"));

        if(BiomeDictionary.hasType(biomeRegistryKey, BiomeDictionary.Type.OVERWORLD)) {
            generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, getFeature(ModConfiguredFeatures.OBSIDIANITE_ORE));
        }
    }

    private static ConfiguredFeature<?, ?> getFeature(final RegistryKey<ConfiguredFeature<?, ?>> key) {
        return WorldGenRegistries.CONFIGURED_FEATURE.getOrThrow(key);
    }
}
