package johan24.examplemod.init;

import johan24.examplemod.ExampleMod;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

/**
 * Registers this mod's {@link net.minecraft.world.gen.feature.ConfiguredFeature}s.
 *
 * @author Johan24
 */
public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> OBSIDIANITE_ORE = key("obsidianite_ore");

    /**
     * Creates or gets the registry key for the Configured Features.
     * @param name - Path of the registry key.
     * @return - registry key.
     */
    private static RegistryKey<ConfiguredFeature<?, ?>> key(final String name) {
        return RegistryKey.getOrCreateKey(Registry.CONFIGURED_FEATURE_KEY, new ResourceLocation(ExampleMod.MODID, name));
    }

    @Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Bus.MOD)
    public static class RegistrationHandler {
        // Ensure this is run after the DeferredRegister.
        @SubscribeEvent(priority = EventPriority.LOW)
        public static void register(final RegistryEvent.Register<Feature<?>> event) {
            register(OBSIDIANITE_ORE, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, ModBlocks.OBSIDIANITE_ORE.get().getDefaultState(), 9))
            .range(16)
            .square()
            .func_242731_b(20));
        }

        private static void register(final RegistryKey<ConfiguredFeature<?, ?>> key, final ConfiguredFeature<?, ?> configuredFeature) {
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key.getLocation(), configuredFeature);
        }
    }
}