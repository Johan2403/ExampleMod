package johan24.examplemod.data;

import johan24.examplemod.ExampleMod;
import johan24.examplemod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Map;
import java.util.function.Supplier;

/**
 * This is the class in which the BlockStates and Block Models
 * are generated for the Mod Example Mod.
 *
 * @author Johan24
 */
public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
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
    }

    public void simpleBlock(Supplier<? extends Block> blockSupplier) {
        simpleBlock(blockSupplier.get());
    }

    public void templateExtenderHorizontalBlock(Supplier<? extends Block> blockSupplier, Map<String, ResourceLocation> textures) {
        Block block = blockSupplier.get();
        horizontalBlock(block, templateExtender(block, textures));
    }

    /**
     * This creates a Model for the BlockItem.
     * @param block
     * @param model
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

    public BlockModelBuilder templateExtender(Block block, Map<String, ResourceLocation> textures) {
        ResourceLocation name = block.getRegistryName();
        BlockModelBuilder builder = this.models().withExistingParent(name.getPath(), new ResourceLocation(name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/template_" + name.getPath()));
        textures.forEach((key, texture) -> builder.texture(key, texture));
        return builder;
    }
}
