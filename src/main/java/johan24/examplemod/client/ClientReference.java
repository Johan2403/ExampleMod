package johan24.examplemod.client;

import johan24.examplemod.util.ISidedReference;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * This is where client-side events are registered
 * and fired.
 *
 * @author Johan24
 */
public class ClientReference implements ISidedReference {
    @Override
    public void setup(IEventBus modEventBus, IEventBus forgeEventBus) {
        modEventBus.addListener(this::clientSetup);
    }

    /**
     * The code inside this method will be executed during
     * the client setup.
     * @param event
     */
    private void clientSetup(final FMLClientSetupEvent event) {

    }
}
