package mods.johan24.examplemod.init;

import mods.johan24.examplemod.ExampleMod;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Registers this mod's {@link net.minecraft.world.gen.feature.Feature}s.
 *
 * @author Johan24
 */
public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, ExampleMod.MODID);
}
