package johan24.forge.examplemod.init;

import johan24.forge.examplemod.ExampleMod;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModConfiguredSurfaceBuilders {
    public static final RegistryKey<ConfiguredSurfaceBuilder<?>> CORRUPT_FOREST = key("corrupt_forest");

    private static RegistryKey<ConfiguredSurfaceBuilder<?>> key(final String name) {
        return RegistryKey.create(Registry.CONFIGURED_SURFACE_BUILDER_REGISTRY, new ResourceLocation(ExampleMod.MODID, name));
    }

    @Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {
        @SubscribeEvent(priority = EventPriority.LOW)
        public static void register(final RegistryEvent.Register<SurfaceBuilder<?>> event) {
            register(CORRUPT_FOREST, SurfaceBuilder.SWAMP.configured(new SurfaceBuilderConfig(Blocks.CRIMSON_NYLIUM.defaultBlockState(), Blocks.NETHERRACK.defaultBlockState(), Blocks.NETHERRACK.defaultBlockState())));
        }

        private static void register(final RegistryKey<ConfiguredSurfaceBuilder<?>> key, final ConfiguredSurfaceBuilder<?> configuredSurfaceBuilder) {
            Registry.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, key.location(), configuredSurfaceBuilder);
        }
    }
}
