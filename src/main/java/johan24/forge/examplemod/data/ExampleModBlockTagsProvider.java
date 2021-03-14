package johan24.forge.examplemod.data;

import johan24.forge.examplemod.init.ModBlocks;
import johan24.forge.examplemod.tags.ExampleTags;
import johan24.forge.examplemod.ExampleMod;
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
    protected void addTags() {

        tag(Tags.Blocks.ORES).addTag(ExampleTags.Blocks.ORES_OBSIDIANITE);

        tag(ExampleTags.Blocks.ORES_OBSIDIANITE).add(ModBlocks.OBSIDIANITE_ORE.get());

        /* Adds Blocks to vanilla tags. */
        tag(net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS).add(ModBlocks.OBSIDIANITE_BLOCK.get());
    }
}
