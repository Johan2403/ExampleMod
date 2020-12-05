package johan24.examplemod.data;

import johan24.examplemod.ExampleMod;
import johan24.examplemod.init.ModBlocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class BlockTags extends BlockTagsProvider {
    public BlockTags(DataGenerator generatorIn, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, ExampleMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerTags() {

        /* Adds Blocks to vanilla tags. */
        getOrCreateBuilder(net.minecraft.tags.BlockTags.BEACON_BASE_BLOCKS).add(ModBlocks.OBSIDIANITE_BLOCK.get());
    }
}
