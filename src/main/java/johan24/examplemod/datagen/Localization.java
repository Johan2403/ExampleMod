package johan24.examplemod.datagen;

import johan24.examplemod.ExampleMod;
import johan24.examplemod.init.ModBlocks;
import johan24.examplemod.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

/**
 * This is the class which generates Language file which converts
 * translation keys into meaningful names for the mod Example Mod.
 *
 * @author Johan24
 */
public class Localization extends LanguageProvider {

    public Localization(DataGenerator gen, String locale) {
        super(gen, ExampleMod.MODID, locale);
    }

    /**
     * Adds the translations for Items, Blocks, ItemGroups etc. Translates it into
     * en_us locale.
     */
    @Override
    protected void addTranslations() {
        // Items
        addItem(ModItems.OBSIDIANITE_INGOT, "Obsidianite Ingot");
        addItem(ModItems.OBSIDIANITE_SCRAP, "Obsidianite Scrap");
        addItem(ModItems.OBSIDIANITE_PICKAXE, "Obsidianite Pickaxe");
        addItem(ModItems.OBSIDIANITE_SWORD, "Obsidianite Sword");
        addItem(ModItems.OBSIDIANITE_AXE, "Obsidianite Axe");
        addItem(ModItems.OBSIDIANITE_SHOVEL, "Obsidianite Shovel");
        addItem(ModItems.OBSIDIANITE_HOE, "Obsidianite Hoe");

        // Blocks
        addBlock(ModBlocks.OBSIDIANITE_BLOCK, "Block of Obsidianite");

        // ItemGroup
        add("itemGroup." + ExampleMod.TAB.getPath(), "Example Tab");
    }
}
