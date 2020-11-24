package johan24.examplemod.datagen;

import johan24.examplemod.ExampleMod;
import johan24.examplemod.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class ItemModels extends ItemModelProvider {

    public ItemModels(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ExampleMod.MODID, exFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.OBSIDIANITE_INGOT);
        simpleItem(ModItems.OBSIDIANITE_SCRAP);
        simpleItem(ModItems.OBSIDIANITE_PICKAXE);
        simpleItem(ModItems.OBSIDIANITE_SWORD);
        simpleItem(ModItems.OBSIDIANITE_AXE);
        simpleItem(ModItems.OBSIDIANITE_SHOVEL);
        simpleItem(ModItems.OBSIDIANITE_HOE);
    }

    public void simpleItem(Supplier<? extends Item> itemSupplier) {
        ResourceLocation location = itemSupplier.get().getRegistryName();
        this.getBuilder(location.getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(location.getNamespace(), ITEM_FOLDER + "/" + location.getPath()));
    }
}
