package mods.johan24.examplemod.server.dedicated;

import mods.johan24.examplemod.util.ISidedReference;
import net.minecraftforge.eventbus.api.IEventBus;

/**
 * This is where server-side events are registered
 * and fired.
 *
 * @author Johan24
 */
public class DedicatedServerReference implements ISidedReference {
    @Override
    public void setup(IEventBus mod, IEventBus forge) {
    }
}
