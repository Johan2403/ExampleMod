package johan24.examplemod.datagen;

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

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ExampleMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.OBSIDIANITE_BLOCK);
    }

    public void simpleBlock(Supplier<? extends Block> blockSupplier) {
        simpleBlock(blockSupplier.get());
    }

    public void templateExtenderHorizontalBlock(Supplier<? extends Block> blockSupplier, Map<String, ResourceLocation> textures) {
        Block block = blockSupplier.get();
        horizontalBlock(block, templateExtender(block, textures));
    }

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
