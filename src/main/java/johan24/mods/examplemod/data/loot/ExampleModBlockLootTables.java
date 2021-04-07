package johan24.mods.examplemod.data.loot;

import johan24.mods.examplemod.init.ModBlocks;
import johan24.mods.examplemod.init.ModItems;
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
        this.dropSelf(ModBlocks.OBSIDIANITE_BLOCK.get());
        this.dropSelf(ModBlocks.OBSIDIANITE_CRATE.get());
        this.add(ModBlocks.OBSIDIANITE_ORE.get(), (obsidianite_ore) -> createOreDrop(obsidianite_ore, ModItems.OBSIDIANITE_INGOT.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
