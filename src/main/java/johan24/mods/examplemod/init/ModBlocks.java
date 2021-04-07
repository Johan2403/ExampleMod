package johan24.forge.examplemod.init;

import johan24.forge.examplemod.ExampleMod;
import johan24.forge.examplemod.block.ObsidianiteCrateBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * This is the class where Blocks are initialized and registered.
 *
 * @author Johan24
 */
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MODID);

    public static final RegistryObject<Block> OBSIDIANITE_BLOCK = BLOCKS.register("obsidianite_block",
            () -> new Block(Block.Properties.of(Material.METAL)
                                            .strength(6.0F, 6000.0F)
                                            .harvestTool(ToolType.PICKAXE)
                                            .harvestLevel(2)
                                            .sound(SoundType.METAL)
                                            .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> OBSIDIANITE_ORE = BLOCKS.register("obsidianite_ore",
            () -> new Block(Block.Properties.of(Material.STONE)
                                            .strength(4.5F, 6000.0F)
                                            .harvestTool(ToolType.PICKAXE)
                                            .harvestLevel(2)
                                            .sound(SoundType.STONE)
                                            .requiresCorrectToolForDrops()));

    public static final RegistryObject<ObsidianiteCrateBlock> OBSIDIANITE_CRATE = BLOCKS.register("obsidianite_crate", ObsidianiteCrateBlock::new);
}
