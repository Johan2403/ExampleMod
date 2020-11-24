package johan24.examplemod.client;

import johan24.examplemod.util.ISidedReference;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientReference implements ISidedReference {
    @Override
    public void setup(IEventBus modEventBus, IEventBus forgeEventBus) {
        modEventBus.addListener(this::clientSetup);
    }

    private void clientSetup(final FMLClientSetupEvent event) {

    }
}
