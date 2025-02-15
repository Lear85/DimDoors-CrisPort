package org.dimdev.dimdoors.world.level.registry.fabric;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import org.dimdev.dimdoors.world.level.registry.DimensionalRegistry;

import static net.minecraft.world.level.Level.OVERWORLD;

public class DimensionalRegistryImpl implements ComponentV3 {
    private final boolean isOverworld;

    public DimensionalRegistryImpl(boolean isOverworld) {

        this.isOverworld = isOverworld;
    }

    @Override
    public void readFromNbt(CompoundTag compoundTag) {
        if(isOverworld) DimensionalRegistry.readFromNbt(compoundTag);
    }

    @Override
    public void writeToNbt(CompoundTag compoundTag) {
        if (isOverworld) DimensionalRegistry.writeToNbt(compoundTag);
    }

    public static DimensionalRegistryImpl createImpl(Level level) {
        return new DimensionalRegistryImpl(level.dimension().equals(OVERWORLD));
    }
}
