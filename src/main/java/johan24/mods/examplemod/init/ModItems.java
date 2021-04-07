package johan24.mods.examplemod.init;

import johan24.mods.examplemod.item.ObsidianiteAppleItem;
import johan24.mods.examplemod.util.enums.ModArmorMaterial;
import johan24.mods.examplemod.util.enums.ModItemTier;
import johan24.mods.examplemod.ExampleMod;
import net.minecraft.inventory.EquipmentSlotType;
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
            () -> new Item(new Item.Properties().tab(ExampleMod.TAB)));

    public static final RegistryObject<Item> OBSIDIANITE_SCRAP = ITEMS.register("obsidianite_scrap",
            () -> new Item(new Item.Properties().tab(ExampleMod.TAB)));


    // BlockItems
    public static final RegistryObject<BlockItem> OBSIDIANITE_BLOCK_ITEM = ITEMS.register("obsidianite_block",
            () -> new BlockItem(ModBlocks.OBSIDIANITE_BLOCK.get(), new Item.Properties().tab(ExampleMod.TAB)));

    public static final RegistryObject<BlockItem> OBSIDIANITE_ORE_ITEM = ITEMS.register("obsidianite_ore",
            () -> new BlockItem(ModBlocks.OBSIDIANITE_ORE.get(), new Item.Properties().tab(ExampleMod.TAB)));

    public static final RegistryObject<BlockItem> OBSIDIANITE_CRATE_ITEM = ITEMS.register("obsidianite_crate",
            () -> new BlockItem(ModBlocks.OBSIDIANITE_CRATE.get(), new Item.Properties().tab(ExampleMod.TAB)));


    // Tools
    public static final RegistryObject<SwordItem> OBSIDIANITE_SWORD = ITEMS.register("obsidianite_sword",
            () -> new SwordItem(ModItemTier.OBSIDIANITE, 3, -2.4F, new Item.Properties().tab(ExampleMod.TAB)));

    public static final RegistryObject<PickaxeItem> OBSIDIANITE_PICKAXE = ITEMS.register("obsidianite_pickaxe",
            () -> new PickaxeItem(ModItemTier.OBSIDIANITE, 1, -2.8F, new Item.Properties().tab(ExampleMod.TAB)));

    public static final RegistryObject<ShovelItem> OBSIDIANITE_SHOVEL = ITEMS.register("obsidianite_shovel",
            () -> new ShovelItem(ModItemTier.OBSIDIANITE, 0.5F, -3.0F, new Item.Properties().tab(ExampleMod.TAB)));

    public static final RegistryObject<AxeItem> OBSIDIANITE_AXE = ITEMS.register("obsidianite_axe",
            () -> new AxeItem(ModItemTier.OBSIDIANITE, 5, -3.1F, new Item.Properties().tab(ExampleMod.TAB)));

    public static final RegistryObject<HoeItem> OBSIDIANITE_HOE = ITEMS.register("obsidianite_hoe",
            () -> new HoeItem(ModItemTier.OBSIDIANITE, -3, -1.0F, new Item.Properties().tab(ExampleMod.TAB)));


    // Armor
    public static final RegistryObject<ArmorItem> OBSIDIANITE_HELMET = ITEMS.register("obsidianite_helmet",
            () -> new ArmorItem(ModArmorMaterial.OBSIDIANITE, EquipmentSlotType.HEAD, new Item.Properties().tab(ExampleMod.TAB)));

    public static final RegistryObject<ArmorItem> OBSIDIANITE_CHESTPLATE = ITEMS.register("obsidianite_chestplate",
            () -> new ArmorItem(ModArmorMaterial.OBSIDIANITE, EquipmentSlotType.CHEST, new Item.Properties().tab(ExampleMod.TAB)));

    public static final RegistryObject<ArmorItem> OBSIDIANITE_LEGGINGS = ITEMS.register("obsidianite_leggings",
            () -> new ArmorItem(ModArmorMaterial.OBSIDIANITE, EquipmentSlotType.LEGS, new Item.Properties().tab(ExampleMod.TAB)));

    public static final RegistryObject<ArmorItem> OBSIDIANITE_BOOTS = ITEMS.register("obsidianite_boots",
            () -> new ArmorItem(ModArmorMaterial.OBSIDIANITE, EquipmentSlotType.FEET, new Item.Properties().tab(ExampleMod.TAB)));

    // Food
    public static final RegistryObject<ObsidianiteAppleItem> OBSIDIANITE_APPLE = ITEMS.register("obsidianite_apple", ObsidianiteAppleItem::new);
}
