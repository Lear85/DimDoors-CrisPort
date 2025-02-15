package org.dimdev.dimdoors.block;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class AncientFabricBlock extends Block {
	public AncientFabricBlock(DyeColor color) {
		super(BlockBehaviour.Properties.of(Material.STONE, color).strength(-1.0F, 3600000.0F).dropsLike(Blocks.AIR).lightLevel(state -> 15));
	}
}
