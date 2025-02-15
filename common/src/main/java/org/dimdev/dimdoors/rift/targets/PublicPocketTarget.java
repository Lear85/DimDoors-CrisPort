package org.dimdev.dimdoors.rift.targets;

import net.minecraft.nbt.CompoundTag;
import org.dimdev.dimdoors.api.util.Location;
import org.dimdev.dimdoors.pockets.PocketGenerator;
import org.dimdev.dimdoors.world.level.registry.DimensionalRegistry;
import org.dimdev.dimdoors.world.pocket.VirtualLocation;
import org.dimdev.dimdoors.world.pocket.type.Pocket;

public class PublicPocketTarget extends RestoringTarget {
	private VirtualTarget wrappedDestination = null;

	private PublicPocketTarget(VirtualTarget wrappedDestination) {
		this.wrappedDestination = wrappedDestination;
	}

	public PublicPocketTarget() {
	}

	@Override
	protected VirtualTarget getTarget() {
		return this.wrappedDestination;
	}

	@Override
	protected void setTarget(VirtualTarget target) {
		this.wrappedDestination = target;
	}

	@Override
	public Location makeLinkTarget() {
		VirtualLocation riftVirtualLocation = VirtualLocation.fromLocation(this.location);
		VirtualLocation newVirtualLocation;
		int depth = riftVirtualLocation.getDepth() + 1;
		newVirtualLocation = new VirtualLocation(riftVirtualLocation.getWorld(), riftVirtualLocation.getX(), riftVirtualLocation.getZ(), depth);
		Pocket pocket = PocketGenerator.generatePublicPocketV2(newVirtualLocation, new GlobalReference(this.location), null);

		return DimensionalRegistry.getRiftRegistry().getPocketEntrance(pocket);
	}

	@Override
	public VirtualTargetType<? extends VirtualTarget> getType() {
		return VirtualTargetType.PUBLIC_POCKET.get();
	}

	@Override
	public VirtualTarget copy() {
		return new PublicPocketTarget(wrappedDestination);
	}

	public static CompoundTag toNbt(PublicPocketTarget target) {
		CompoundTag nbt = new CompoundTag();
		if (target.wrappedDestination != null)
			nbt.put("wrappedDestination", VirtualTarget.toNbt(target.wrappedDestination));
		return nbt;
	}

	public static PublicPocketTarget fromNbt(CompoundTag nbt) {
		PublicPocketTarget target = new PublicPocketTarget();
		if (nbt.contains("wrappedDestination"))
			target.wrappedDestination = VirtualTarget.fromNbt(nbt.getCompound("wrappedDestination"));
		return target;
	}
}
