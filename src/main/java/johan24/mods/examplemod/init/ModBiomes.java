package johan24.mods.examplemod.init;

import johan24.mods.examplemod.ExampleMod;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.function.Supplier;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class ModBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, ExampleMod.MODID);

    public static final RegistryObject<Biome> CORRUPT_FOREST = BIOMES.register("corrupt_forest",
            () -> Utils.makeCorruptForest(
                    Utils.surfaceBuilder(ModConfiguredSurfaceBuilders.CORRUPT_FOREST), 0.125f, 0.05f, false, false, true
            ));

    private static RegistryKey<Biome> key(final Biome biome) {
        return RegistryKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome), "Biome registry name was null"));
    }

    @Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void setupBiomes(final FMLCommonSetupEvent event) {
            event.enqueueWork(() -> setupBiome(CORRUPT_FOREST.get(), BiomeManager.BiomeType.WARM, 1000, HOT, DRY, LUSH, OVERWORLD));
        }

        private static void setupBiome(Biome biome, BiomeManager.BiomeType biomeType, int weight, BiomeDictionary.Type ... types) {
            BiomeDictionary.addTypes(key(biome), types);
            BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(key(biome), weight));
        }
    }

    public static class Utils {
        private static final Method CALCULATE_SKY_COLOR = ObfuscationReflectionHelper.findMethod(BiomeMaker.class, "calculateSkyColor", float.class);

        public static Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder(final RegistryKey<ConfiguredSurfaceBuilder<?>> key) {
            return () -> WorldGenRegistries.CONFIGURED_SURFACE_BUILDER.getOrThrow(key);
        }

        public static Biome makeCorruptForest(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder,
                                              final float depth,
                                              final float scale,
                                              final boolean hasVillageAndOutpost,
                                              final boolean hasDesertPyramid,
                                              final boolean hasFossils) {
            final MobSpawnInfo.Builder mobSpawnInfoBuilder = new MobSpawnInfo.Builder();
            DefaultBiomeFeatures.monsters(mobSpawnInfoBuilder, 10, 5, 10);

            final BiomeGenerationSettings.Builder biomeGenerationSettingBuilder = new BiomeGenerationSettings.Builder()
                    .surfaceBuilder(surfaceBuilder);

            if (hasVillageAndOutpost) {
                biomeGenerationSettingBuilder.addStructureStart(StructureFeatures.VILLAGE_DESERT);
                biomeGenerationSettingBuilder.addStructureStart(StructureFeatures.PILLAGER_OUTPOST);
            }

            if (hasDesertPyramid) {
                biomeGenerationSettingBuilder.addStructureStart(StructureFeatures.DESERT_PYRAMID);
            }

            if (hasFossils) {
                DefaultBiomeFeatures.addFossilDecoration(biomeGenerationSettingBuilder);
            }

            DefaultBiomeFeatures.addDefaultOverworldLandMesaStructures(biomeGenerationSettingBuilder);
            biomeGenerationSettingBuilder.addStructureStart(StructureFeatures.RUINED_PORTAL_NETHER);
            DefaultBiomeFeatures.addDefaultCarvers(biomeGenerationSettingBuilder);
            DefaultBiomeFeatures.addDesertLakes(biomeGenerationSettingBuilder);
            DefaultBiomeFeatures.addDefaultMonsterRoom(biomeGenerationSettingBuilder);
            DefaultBiomeFeatures.addDefaultUndergroundVariety(biomeGenerationSettingBuilder);
            DefaultBiomeFeatures.addNetherDefaultOres(biomeGenerationSettingBuilder);
            DefaultBiomeFeatures.addDefaultSoftDisks(biomeGenerationSettingBuilder);
            DefaultBiomeFeatures.addDesertVegetation(biomeGenerationSettingBuilder);
            DefaultBiomeFeatures.addDefaultSprings(biomeGenerationSettingBuilder);
            withCrimsonTrees(biomeGenerationSettingBuilder);

            final int skyColour;
            try {
                skyColour = (int) CALCULATE_SKY_COLOR.invoke(null, 2);
            } catch (final IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Unable to get sky colour", e);
            }

            return new Biome.Builder()
                    .precipitation(Biome.RainType.NONE)
                    .biomeCategory(Biome.Category.NETHER)
                    .depth(depth)
                    .scale(scale)
                    .temperature(3)
                    .downfall(0)
                    .specialEffects(new BiomeAmbience.Builder()
                                    .waterColor(0x3f76e4)
                                    .waterFogColor(0x50533)
                                    .fogColor(0xc0d8ff)
                                    .skyColor(skyColour)
                                    .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)
                                    .build()
                    )
                    .mobSpawnSettings(mobSpawnInfoBuilder.build())
                    .generationSettings(biomeGenerationSettingBuilder.build())
                    .build();
        }

        public static void withCrimsonTrees(BiomeGenerationSettings.Builder builder) {
            builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.CRIMSON_FUNGI);
        }
    }
}
