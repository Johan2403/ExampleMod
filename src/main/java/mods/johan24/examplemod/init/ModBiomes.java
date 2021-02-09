package mods.johan24.examplemod.init;

import mods.johan24.examplemod.ExampleMod;
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
        return RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome), "Biome registry name was null"));
    }

    @Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void setupBiomes(final FMLCommonSetupEvent event) {
            event.enqueueWork(() -> {
                setupBiome(CORRUPT_FOREST.get(), BiomeManager.BiomeType.WARM, 1000, HOT, DRY, LUSH, OVERWORLD);
            });
        }

        private static void setupBiome(Biome biome, BiomeManager.BiomeType biomeType, int weight, BiomeDictionary.Type ... types) {
            BiomeDictionary.addTypes(key(biome), types);
            BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(key(biome), weight));
        }
    }

    public static class Utils {
        private static final Method GET_SKY_COLOR_WITH_TEMPERATURE_MODIFIER = ObfuscationReflectionHelper.findMethod(BiomeMaker.class, "func_244206_a", float.class);

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
            DefaultBiomeFeatures.withHostileMobs(mobSpawnInfoBuilder, 10, 5, 10);

            final BiomeGenerationSettings.Builder biomeGenerationSettingBuilder = new BiomeGenerationSettings.Builder()
                    .withSurfaceBuilder(surfaceBuilder);

            if (hasVillageAndOutpost) {
                biomeGenerationSettingBuilder.withStructure(StructureFeatures.VILLAGE_DESERT);
                biomeGenerationSettingBuilder.withStructure(StructureFeatures.PILLAGER_OUTPOST);
            }

            if (hasDesertPyramid) {
                biomeGenerationSettingBuilder.withStructure(StructureFeatures.DESERT_PYRAMID);
            }

            if (hasFossils) {
                DefaultBiomeFeatures.withFossils(biomeGenerationSettingBuilder);
            }

            DefaultBiomeFeatures.withStrongholdAndMineshaft(biomeGenerationSettingBuilder);
            biomeGenerationSettingBuilder.withStructure(StructureFeatures.RUINED_PORTAL_NETHER);
            DefaultBiomeFeatures.withCavesAndCanyons(biomeGenerationSettingBuilder);
            DefaultBiomeFeatures.withLavaLakes(biomeGenerationSettingBuilder);
            DefaultBiomeFeatures.withMonsterRoom(biomeGenerationSettingBuilder);
            DefaultBiomeFeatures.withCommonOverworldBlocks(biomeGenerationSettingBuilder);
            DefaultBiomeFeatures.withOverworldOres(biomeGenerationSettingBuilder);
            DefaultBiomeFeatures.withDisks(biomeGenerationSettingBuilder);
            DefaultBiomeFeatures.withNormalMushroomGeneration(biomeGenerationSettingBuilder);
            DefaultBiomeFeatures.withDesertVegetation(biomeGenerationSettingBuilder);
            DefaultBiomeFeatures.withLavaAndWaterSprings(biomeGenerationSettingBuilder);
            withCrimsonTrees(biomeGenerationSettingBuilder);

            final int skyColour;
            try {
                skyColour = (int) GET_SKY_COLOR_WITH_TEMPERATURE_MODIFIER.invoke(null, 2);
            } catch (final IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Unable to get sky colour", e);
            }

            return new Biome.Builder()
                    .precipitation(Biome.RainType.NONE)
                    .category(Biome.Category.NETHER)
                    .depth(depth)
                    .scale(scale)
                    .temperature(3)
                    .downfall(0)
                    .setEffects(new BiomeAmbience.Builder()
                                    .setWaterColor(0x3f76e4)
                                    .setWaterFogColor(0x50533)
                                    .setFogColor(0xc0d8ff)
                                    .withSkyColor(skyColour)
                                    .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                                    .build()
                    )
                    .withMobSpawnSettings(mobSpawnInfoBuilder.copy())
                    .withGenerationSettings(biomeGenerationSettingBuilder.build())
                    .build();
        }

        public static void withCrimsonTrees(BiomeGenerationSettings.Builder builder) {
            builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.CRIMSON_FUNGI);
        }
    }
}
