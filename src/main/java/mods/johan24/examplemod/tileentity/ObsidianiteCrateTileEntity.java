package mods.johan24.examplemod.tileentity;

import mods.johan24.examplemod.init.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ObsidianiteCrateTileEntity extends LockableLootTileEntity {
    private NonNullList<ItemStack> crateContents = NonNullList.withSize(54, ItemStack.EMPTY);

    public ObsidianiteCrateTileEntity() {
        super(ModTileEntities.OBSIDIANITE_CRATE_TILE.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if(!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.crateContents);
        }

        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.crateContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if(!this.checkLootAndRead(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.crateContents);
        }
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.crateContents;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.crateContents = itemsIn;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("examplemod.container.obsidianite_crate");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return ChestContainer.createGeneric9X6(id, player, this);
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {
        return 54;
    }
}
