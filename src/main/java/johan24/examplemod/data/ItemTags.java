package johan24.examplemod.data;

import johan24.examplemod.ExampleMod;
import johan24.examplemod.init.ModItems;
import johan24.examplemod.tags.ExampleTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ItemTags extends ItemTagsProvider {

    public ItemTags(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, ExampleMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerTags() {

        /* Adds custom item tags to vanilla. */
        getOrCreateBuilder(Tags.Items.INGOTS).addTag(ExampleTags.Items.INGOT_OBSIDIANITE);

        /* Adds Items to our tags. */
        getOrCreateBuilder(ExampleTags.Items.INGOT_OBSIDIANITE).add(ModItems.OBSIDIANITE_INGOT.get());

        /* Adds Items to vanilla tags. */
        getOrCreateBuilder(net.minecraft.tags.ItemTags.BEACON_PAYMENT_ITEMS).add(ModItems.OBSIDIANITE_INGOT.get());
    }
}
