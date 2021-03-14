package johan24.forge.examplemod.data;

import johan24.forge.examplemod.init.ModBlocks;
import johan24.forge.examplemod.ExampleMod;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

/**
 * This is the class in which the BlockStates and Block Models
 * are generated for the Mod Example Mod.
 *
 * @author Johan24
 */
public class ExampleModBlockStatesProvider extends BlockStateProvider {

    public ExampleModBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ExampleMod.MODID, exFileHelper);
    }

    /**
     * This method registers the BlockStates and Block
     * models. Just use the methods(templates) to create BlockStates and
     * Block models.
     */
    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.OBSIDIANITE_BLOCK);
        simpleBlock(ModBlocks.OBSIDIANITE_ORE);
        obsidianiteCrateBlock();
    }

    public void simpleBlock(Supplier<? extends Block> blockSupplier) {
        simpleBlock(blockSupplier.get());
    }

    /**
     * This creates a Model for the BlockItem.
     * @param block - Block
     * @param model - ModelFile of the block
     */
    @Override
    public void simpleBlock(Block block, ModelFile model) {
        super.simpleBlock(block, model);
        this.simpleBlockItem(block, model);
    }

    @Override
    public void horizontalBlock(Block block, ModelFile model) {
        super.horizontalBlock(block, model);
        this.simpleBlockItem(block, model);
    }

    public void obsidianiteCrateBlock() {
        ResourceLocation name = ModBlocks.OBSIDIANITE_CRATE.get().getRegistryName();
        assert name != null;
        BlockModelBuilder builder = this.models().withExistingParent(name.getPath(), "block/cube_bottom_top");
        builder.texture("top", modLoc("block/obsidianite_crate_top"));
        builder.texture("bottom", modLoc("block/obsidianite_block"));
        builder.texture("side", modLoc("block/obsidianite_block"));

        this.simpleBlockItem(ModBlocks.OBSIDIANITE_CRATE.get(), builder);
    }

    /*
    public BlockModelBuilder templateExtender(Block block, Map<String, ResourceLocation> textures) {
        ResourceLocation name = block.getRegistryName();
        assert name != null;
        BlockModelBuilder builder = this.models().withExistingParent(name.getPath(), new ResourceLocation(name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/template_" + name.getPath()));
        textures.forEach(builder::texture);
        return builder;
    }

     */
}
