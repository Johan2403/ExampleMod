package johan24.mods.examplemod.server.dedicated;

import johan24.mods.examplemod.ExampleMod;
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
