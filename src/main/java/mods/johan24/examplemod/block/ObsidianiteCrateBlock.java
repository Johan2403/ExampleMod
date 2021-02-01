package mods.johan24.examplemod.block;

import mods.johan24.examplemod.tileentity.ObsidianiteCrateTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class ObsidianiteCrateBlock extends ContainerBlock {
    public static final DirectionProperty PROPERTY_FACING = BlockStateProperties.FACING;

    public ObsidianiteCrateBlock() {
        super(Block.Properties.create(Material.IRON)
                              .hardnessAndResistance(6.0F, 6000.0F)
                              .harvestTool(ToolType.PICKAXE)
                              .harvestLevel(2)
                              .sound(SoundType.METAL)
                              .setRequiresTool());
        this.setDefaultState(this.getStateContainer().getBaseState().with(PROPERTY_FACING, Direction.NORTH));
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof ObsidianiteCrateTileEntity) {
                player.openContainer((ObsidianiteCrateTileEntity) tileEntity);
                player.addStat(Stats.OPEN_CHEST);
                PiglinTasks.func_234478_a_(player, true);
            }
            return ActionResultType.CONSUME;
        }
        return ActionResultType.SUCCESS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if(!state.isIn(newState.getBlock())) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof IInventory) {
                InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileEntity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }

            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new ObsidianiteCrateTileEntity();
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if(stack.hasDisplayName()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof ObsidianiteCrateTileEntity) {
                ((ObsidianiteCrateTileEntity) tileEntity).setCustomName(stack.getDisplayName());
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.with(PROPERTY_FACING, direction.rotate(state.get(PROPERTY_FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(PROPERTY_FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(PROPERTY_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(PROPERTY_FACING, context.getNearestLookingDirection().getOpposite());
    }
}
