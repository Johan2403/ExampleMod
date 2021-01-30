package mods.johan24.examplemod.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class ObsidianiteCrateBlock extends Block {

    public ObsidianiteCrateBlock() {
        super(Block.Properties.create(Material.IRON)
                              .hardnessAndResistance(6.0F, 6000.0F)
                              .harvestTool(ToolType.PICKAXE)
                              .harvestLevel(2)
                              .sound(SoundType.METAL)
                              .setRequiresTool());
    }
}
