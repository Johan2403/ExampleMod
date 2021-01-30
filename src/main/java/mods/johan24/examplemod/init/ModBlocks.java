package mods.johan24.examplemod.init;

import mods.johan24.examplemod.ExampleMod;
import mods.johan24.examplemod.block.ObsidianiteCrateBlock;
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
            () -> new Block(Block.Properties.create(Material.IRON)
                                            .hardnessAndResistance(6.0F, 6000.0F)
                                            .harvestTool(ToolType.PICKAXE)
                                            .harvestLevel(2)
                                            .sound(SoundType.METAL)
                                            .setRequiresTool()));

    public static final RegistryObject<Block> OBSIDIANITE_ORE = BLOCKS.register("obsidianite_ore",
            () -> new Block(Block.Properties.create(Material.ROCK)
                                            .hardnessAndResistance(4.5F, 6000.0F)
                                            .harvestTool(ToolType.PICKAXE)
                                            .harvestLevel(2)
                                            .sound(SoundType.STONE)
                                            .setRequiresTool()));

    public static final RegistryObject<ObsidianiteCrateBlock> OBSIDIANITE_CRATE = BLOCKS.register("obsidianite_crate", ObsidianiteCrateBlock::new);
}
