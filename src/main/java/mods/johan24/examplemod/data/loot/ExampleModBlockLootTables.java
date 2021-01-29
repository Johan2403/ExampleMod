package mods.johan24.examplemod.data.loot;

import mods.johan24.examplemod.init.ModBlocks;
import mods.johan24.examplemod.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraftforge.fml.RegistryObject;

/**
 * Generates the mod's block loot tables.
 *
 * @author Johan24
 */
public class ExampleModBlockLootTables extends BlockLootTables {
    @Override
    protected void addTables() {
        this.registerDropSelfLootTable(ModBlocks.OBSIDIANITE_BLOCK.get());
        this.registerLootTable(ModBlocks.OBSIDIANITE_ORE.get(), (obsidianite_ore) -> droppingItemWithFortune(obsidianite_ore, ModItems.OBSIDIANITE_INGOT.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
