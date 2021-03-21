package johan24.forge.examplemod;

import johan24.forge.examplemod.client.ClientReference;
import johan24.forge.examplemod.config.ExampleModConfig;
import johan24.forge.examplemod.data.*;
import johan24.forge.examplemod.init.ModBiomes;
import johan24.forge.examplemod.init.ModBlocks;
import johan24.forge.examplemod.init.ModItems;
import johan24.forge.examplemod.init.ModTileEntities;
import johan24.forge.examplemod.server.dedicated.DedicatedServerReference;
import johan24.forge.examplemod.util.ISidedReference;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The main class of the mod Example Mod.
 * @author Johan24
 */
@Mod(ExampleMod.MODID)
public class ExampleMod {

    /**
     * The modid of the Mod Example Mod. Unique to every mod.
     */
    public static final String MODID = "examplemod";
    public static final Logger LOGGER = LogManager.getLogger(ExampleMod.MODID);
    public static final ISidedReference SIDED_SYSTEM = DistExecutor.safeRunForDist(() -> ClientReference::new, () -> DedicatedServerReference::new);

    /**
     * The main constructor of the mod Example Mod.
     * This is where registration of Blocks, Items etc. take place.
     */
    public ExampleMod() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus(), forgeEventBus = MinecraftForge.EVENT_BUS;
        SIDED_SYSTEM.setup(modEventBus, forgeEventBus);
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::gatherData);

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModTileEntities.TILES.register(modEventBus);
        ModBiomes.BIOMES.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ExampleModConfig.CLIENT_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ExampleModConfig.SERVER_SPEC);
    }

    /**
     * Any code inside this method will be executed during
     * the common setup.
     * @param event The FMLCommonSetupEvent
     */
    private void setup(final FMLCommonSetupEvent event) {

    }

    /**
     * This method gets the Data Generators and generates
     * the data for Models, Loot Tables, Language files and
     * Recipes.
     * @param event The GatherDataEvent
     */
    private void gatherData(final GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        if(event.includeClient()) {
            gen.addProvider(new ExampleModLanguageProvider(gen, "en_us"));
            gen.addProvider(new ExampleModItemModelsProvider(gen, helper));
            gen.addProvider(new ExampleModBlockStatesProvider(gen, helper));
        }
        if(event.includeServer()) {
            ExampleModBlockTagsProvider block_tags = new ExampleModBlockTagsProvider(gen, helper);

            gen.addProvider(block_tags);
            gen.addProvider(new ExampleModItemTagsProvider(gen, block_tags, helper));
            gen.addProvider(new ExampleModRecipeProvider(gen));
            gen.addProvider(new ExampleModLootTableProvider(gen));
        }
    }

    /**
     * This is the custom itemGroup of the Mod Example Mod
     */
    public static final ItemGroup TAB = new ItemGroup("example_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.OBSIDIANITE_INGOT.get());
        }
    };
}
