package johan24.mods.examplemod.data;

import johan24.mods.examplemod.init.ModItems;
import johan24.mods.examplemod.ExampleMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

/**
 * This is the class which generates Item Models for
 * the Mod Example Mod.
 *
 * @author Johan24
 */
public class ExampleModItemModelsProvider extends ItemModelProvider {

    public ExampleModItemModelsProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ExampleMod.MODID, exFileHelper);
    }

    /**
     * Registers the Item Models.
     */
    @Override
    protected void registerModels() {
        simpleItem(ModItems.OBSIDIANITE_INGOT);
        simpleItem(ModItems.OBSIDIANITE_SCRAP);

        // Tools
        toolItem(ModItems.OBSIDIANITE_PICKAXE);
        toolItem(ModItems.OBSIDIANITE_SWORD);
        toolItem(ModItems.OBSIDIANITE_AXE);
        toolItem(ModItems.OBSIDIANITE_SHOVEL);
        toolItem(ModItems.OBSIDIANITE_HOE);

        // Armor
        simpleItem(ModItems.OBSIDIANITE_HELMET);
        simpleItem(ModItems.OBSIDIANITE_CHESTPLATE);
        simpleItem(ModItems.OBSIDIANITE_LEGGINGS);
        simpleItem(ModItems.OBSIDIANITE_BOOTS);

        // Food
        simpleItem(ModItems.OBSIDIANITE_APPLE);
    }

    /**
     * Creates a simple item model with a parent minecraft:item/generated.
     * Use it for non-tool items.
     * @param itemSupplier - The item supplier
     */
    public void simpleItem(Supplier<? extends Item> itemSupplier) {
        ResourceLocation location = itemSupplier.get().getRegistryName();
        assert location != null;
        this.getBuilder(location.getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(location.getNamespace(), ITEM_FOLDER + "/" + location.getPath()));
    }

    /**
     * Creates a simple tool item model with a parent minecraft:item/handheld.
     * Use it for tool items.
     * @param itemSupplier - The item supplier
     */
    public void toolItem(Supplier<? extends Item> itemSupplier) {
        ResourceLocation location = itemSupplier.get().getRegistryName();
        assert location != null;
        this.getBuilder(location.getPath())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", new ResourceLocation(location.getNamespace(), ITEM_FOLDER + "/" + location.getPath()));
    }
}
