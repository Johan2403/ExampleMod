package johan24.examplemod;

import johan24.examplemod.client.ClientReference;
import johan24.examplemod.init.ModBlocks;
import johan24.examplemod.init.ModItems;
import johan24.examplemod.server.dedicated.DedicatedServerReference;
import johan24.examplemod.util.ISidedReference;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ExampleMod.MODID)
public class ExampleMod {

    public static final String MODID = "examplemod";
    public static final Logger LOGGER = LogManager.getLogger(ExampleMod.MODID);
    public static final ISidedReference SIDED_SYSTEM = DistExecutor.safeRunForDist(() -> ClientReference::new, () -> DedicatedServerReference::new);

    public ExampleMod() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus(), forgeEventBus = MinecraftForge.EVENT_BUS;
        SIDED_SYSTEM.setup(modEventBus, forgeEventBus);
        modEventBus.addListener(this::setup);

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    public static final ItemGroup TAB = new ItemGroup("exampleTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.OBSIDIANITE_INGOT.get());
        }
    };
}
