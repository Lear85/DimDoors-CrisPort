package org.dimdev.dimdoors.world.decay;

import java.util.Set;
import java.util.function.Supplier;

import com.mojang.serialization.Lifecycle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;

import org.dimdev.dimdoors.DimensionalDoors;
import org.dimdev.dimdoors.datagen.FluidDecayPredicate;
import org.dimdev.dimdoors.world.decay.predicates.SimpleDecayPredicate;

public interface DecayPredicate {
    Registry<DecayPredicateType<? extends DecayPredicate>> REGISTRY = FabricRegistryBuilder.from(new SimpleRegistry<DecayPredicateType<? extends DecayPredicate>>(RegistryKey.ofRegistry(DimensionalDoors.id("decay_predicate_type")), Lifecycle.stable(), false)).buildAndRegister();

    DecayPredicate NONE = new DecayPredicate() {
        private static final String ID = "none";

        @Override
        public DecayPredicate fromNbt(NbtCompound nbt) {
            return this;
        }

        @Override
        public DecayPredicateType<? extends DecayPredicate> getType() {
            return DecayPredicateType.NONE_PREDICATE_TYPE;
        }

        @Override
        public String getKey() {
            return ID;
        }

        @Override
        public boolean test(World world, BlockPos pos, BlockState origin, BlockState targetBlock, FluidState targetFluid) {
            return false;
        }
	};

    static DecayPredicate deserialize(NbtCompound nbt) {
        Identifier id = Identifier.tryParse(nbt.getString("type"));
        return REGISTRY.getOrEmpty(id).orElse(DecayPredicateType.NONE_PREDICATE_TYPE).fromNbt(nbt);
    }

    static NbtCompound serialize(DecayPredicate modifier) {
        return modifier.toNbt(new NbtCompound());
    }


    DecayPredicate fromNbt(NbtCompound nbt);

    default NbtCompound toNbt(NbtCompound nbt) {
        return this.getType().toNbt(nbt);
    }

    DecayPredicate.DecayPredicateType<? extends DecayPredicate> getType();

    String getKey();

    boolean test(World world, BlockPos pos, BlockState origin, BlockState targetBlock, FluidState targetFluid);

	default Set<Fluid> constructApplicableFluids() {
		return Set.of();
	}

	default Set<Block> constructApplicableBlocks() {
		return Set.of();
	}

    interface DecayPredicateType<T extends DecayPredicate> {
        DecayPredicateType<DecayPredicate> NONE_PREDICATE_TYPE = register(DimensionalDoors.id("none"), () -> NONE);
        DecayPredicateType<SimpleDecayPredicate> SIMPLE_PREDICATE_TYPE = register(DimensionalDoors.id(SimpleDecayPredicate.KEY), SimpleDecayPredicate::new);
		DecayPredicateType<FluidDecayPredicate> FLUID_PREDICATE_TYPE = register(DimensionalDoors.id(FluidDecayPredicate.KEY), FluidDecayPredicate::new);

		DecayPredicate fromNbt(NbtCompound nbt);

		NbtCompound toNbt(NbtCompound nbt);

        static void register() {
            DimensionalDoors.apiSubscribers.forEach(d -> d.registerDecayPredicates(REGISTRY));
        }

        static <U extends DecayPredicate> DecayPredicateType<U> register(Identifier id, Supplier<U> factory) {
            return Registry.register(REGISTRY, id, new DecayPredicateType<U>() {
                @Override
                public DecayPredicate fromNbt(NbtCompound nbt) {
                    return factory.get().fromNbt(nbt);
                }

                @Override
                public NbtCompound toNbt(NbtCompound nbt) {
					nbt.putString("type", id.toString());
                    return nbt;
                }
            });
        }
    }
}
