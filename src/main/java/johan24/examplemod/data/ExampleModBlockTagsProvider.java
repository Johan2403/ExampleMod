package johan24.examplemod.data;

import johan24.examplemod.ExampleMod;
import johan24.examplemod.init.ModBlocks;
import johan24.examplemod.tags.ExampleTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ExampleModBlockTagsProvider extends BlockTagsProvider {
    public ExampleModBlockTagsProvider(DataGenerator generatorIn, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, ExampleMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerTags() {

        getOrCreateBuilder(Tags.Blocks.ORES).addTag(ExampleTags.Blocks.ORES_OBSIDIANITE);

        getOrCreateBuilder(ExampleTags.Blocks.ORES_OBSIDIANITE).add(ModBlocks.OBSIDIANITE_ORE.get());

        /* Adds Blocks to vanilla tags. */
        getOrCreateBuilder(net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS).add(ModBlocks.OBSIDIANITE_BLOCK.get());
    }
}
