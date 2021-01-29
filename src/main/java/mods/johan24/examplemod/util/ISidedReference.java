package mods.johan24.examplemod.util;

import net.minecraftforge.eventbus.api.IEventBus;

/**
 * The interface helps in the setup of the Event Buses.
 * @author Johan24
 */
public interface ISidedReference {
    void setup(final IEventBus mod, final IEventBus forge);
}
