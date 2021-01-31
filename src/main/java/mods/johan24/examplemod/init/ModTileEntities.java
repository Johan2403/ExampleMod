package mods.johan24.examplemod.init;

import mods.johan24.examplemod.ExampleMod;
import mods.johan24.examplemod.tileentity.ObsidianiteCrateTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ExampleMod.MODID);

    public static final RegistryObject<TileEntityType<ObsidianiteCrateTileEntity>> OBSIDIANITE_CRATE_TILE = TILES.register("obsidianite_crate",
            () -> TileEntityType.Builder.create(ObsidianiteCrateTileEntity::new, ModBlocks.OBSIDIANITE_CRATE.get()).build(null));
}
