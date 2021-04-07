package johan24.forge.examplemod.data;

import johan24.forge.examplemod.init.ModItems;
import johan24.forge.examplemod.tags.ExampleTags;
import johan24.forge.examplemod.ExampleMod;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ExampleModItemTagsProvider extends ItemTagsProvider {

    public ExampleModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, ExampleMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {

        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(ExampleTags.Blocks.ORES_OBSIDIANITE, ExampleTags.Items.ORES_OBSIDIANITE);

        tag(Tags.Items.INGOTS).addTag(ExampleTags.Items.INGOT_OBSIDIANITE);

        tag(ExampleTags.Items.INGOT_OBSIDIANITE).add(ModItems.OBSIDIANITE_INGOT.get());

        /* Adds Items to vanilla tags. */
        tag(net.minecraft.tags.ItemTags.BEACON_PAYMENT_ITEMS).add(ModItems.OBSIDIANITE_INGOT.get());
    }
}
