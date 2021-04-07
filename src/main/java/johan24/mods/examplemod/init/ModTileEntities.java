package johan24.mods.examplemod.init;

import johan24.mods.examplemod.ExampleMod;
import johan24.mods.examplemod.tileentity.ObsidianiteCrateTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ExampleMod.MODID);

    public static final RegistryObject<TileEntityType<ObsidianiteCrateTileEntity>> OBSIDIANITE_CRATE_TILE = TILES.register("obsidianite_crate",
            () -> TileEntityType.Builder.of(ObsidianiteCrateTileEntity::new, ModBlocks.OBSIDIANITE_CRATE.get()).build(null));
}
