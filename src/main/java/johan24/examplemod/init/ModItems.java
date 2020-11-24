package johan24.examplemod.init;

import johan24.examplemod.ExampleMod;
import johan24.examplemod.util.enums.ModItemTier;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * This is the class where Items, Tools, Armor and BlockItems are
 * initialized and registered.
 *
 * @author Johan24
 */
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MODID);

    // Items
    public static final RegistryObject<Item> OBSIDIANITE_INGOT = ITEMS.register("obsidianite_ingot",
            () -> new Item(new Item.Properties().group(ExampleMod.TAB)));
    public static final RegistryObject<Item> OBSIDIANITE_SCRAP = ITEMS.register("obsidianite_scrap",
            () -> new Item(new Item.Properties().group(ExampleMod.TAB)));

    // BlockItems
    public static final RegistryObject<BlockItem> OBSIDIANITE_BLOCK_ITEM = ITEMS.register("obsidianite_block",
            () -> new BlockItem(ModBlocks.OBSIDIANITE_BLOCK.get(), new Item.Properties().group(ExampleMod.TAB)));

    // Tools
    public static final RegistryObject<SwordItem> OBSIDIANITE_SWORD = ITEMS.register("obsidianite_sword",
            () -> new SwordItem(ModItemTier.OBSIDIANITE, 3, -2.4F, new Item.Properties().group(ExampleMod.TAB)));
    public static final RegistryObject<PickaxeItem> OBSIDIANITE_PICKAXE = ITEMS.register("obsidianite_pickaxe",
            () -> new PickaxeItem(ModItemTier.OBSIDIANITE, 1, -2.8F, new Item.Properties().group(ExampleMod.TAB)));
    public static final RegistryObject<ShovelItem> OBSIDIANITE_SHOVEL = ITEMS.register("obsidianite_shovel",
            () -> new ShovelItem(ModItemTier.OBSIDIANITE, 0.5F, -3.0F, new Item.Properties().group(ExampleMod.TAB)));
    public static final RegistryObject<AxeItem> OBSIDIANITE_AXE = ITEMS.register("obsidianite_axe",
            () -> new AxeItem(ModItemTier.OBSIDIANITE, 5, -3.1F, new Item.Properties().group(ExampleMod.TAB)));
    public static final RegistryObject<HoeItem> OBSIDIANITE_HOE = ITEMS.register("obsidianite_hoe",
            () -> new HoeItem(ModItemTier.OBSIDIANITE, -3, -1.0F, new Item.Properties().group(ExampleMod.TAB)));
}
