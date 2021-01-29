package mods.johan24.examplemod.server.dedicated;

import mods.johan24.examplemod.ExampleMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

/**
 * Contains events that fire server side for this mod.
 *
 * @author Johan24
 */
@EventBusSubscriber(modid = ExampleMod.MODID, bus = Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class ModServerEvents {
    // Server Events Here.
}
