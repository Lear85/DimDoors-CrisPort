package org.dimdev.dimdoors.world.feature.gateway;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;

import org.dimdev.dimdoors.block.ModBlocks;
import org.dimdev.dimdoors.block.entity.ModBlockEntityTypes;
import org.dimdev.dimdoors.rift.targets.EscapeTarget;
import org.dimdev.dimdoors.world.ModDimensions;

public enum LimboGateway implements Gateway {
    INSTANCE;

    @Override
    public void generate(StructureWorldAccess world, BlockPos pos) {
        if (!this.isLocationValid(world, pos)) {
            return;
        }
        BlockState unravelledFabric = ModBlocks.UNRAVELLED_FABRIC.getDefaultState();
        // Build the gateway out of Unraveled Fabric. Since nearly all the blocks in Limbo are of
        // that type, there is no point replacing the ground.
        world.setBlockState(pos.add(1, 3, 0), unravelledFabric, 2);
        world.setBlockState(pos.add(-1, 3, 0), unravelledFabric, 2);

        // Build the columns around the door
        world.setBlockState(pos.add(-1, 2, 0), unravelledFabric, 2);
        world.setBlockState(pos.add(1, 2, 0), unravelledFabric, 2);
        world.setBlockState(pos.add(1, 1, 0), unravelledFabric, 2);
        world.setBlockState(pos.add(1, 1, 0), unravelledFabric, 2);

        this.placePortal(world, pos.add(0, 1, 0), Direction.NORTH);
    }

    @Override
    public boolean isLocationValid(StructureWorldAccess world, BlockPos pos) {
        return ModDimensions.isLimboDimension(world.toServerWorld());
    }

    private void placePortal(StructureWorldAccess world, BlockPos pos, Direction facing) {
        world.setBlockState(pos, ModBlocks.DIMENSIONAL_PORTAL.getDefaultState(), 2);
    }
}
