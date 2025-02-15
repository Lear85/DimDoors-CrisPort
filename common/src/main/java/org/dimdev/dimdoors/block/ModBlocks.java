package org.dimdev.dimdoors.block;

import dev.architectury.core.block.ArchitecturyLiquidBlock;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.dimdev.dimdoors.DimensionalDoors;
import org.dimdev.dimdoors.block.door.DimensionalDoorBlock;
import org.dimdev.dimdoors.block.door.DimensionalTrapdoorBlock;
import org.dimdev.dimdoors.fluid.ModFluids;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static net.minecraft.world.level.block.Blocks.*;
import static org.dimdev.dimdoors.item.ModItems.DIMENSIONAL_DOORS;

public final class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(DimensionalDoors.MOD_ID, Registries.BLOCK);
	public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(DimensionalDoors.MOD_ID, Registries.ITEM);

	public static final Map<DyeColor, RegistrySupplier<Block>> FABRIC_BLOCKS = new HashMap<DyeColor, RegistrySupplier<Block>>();

	private static final Map<DyeColor, RegistrySupplier<Block>> ANCIENT_FABRIC_BLOCKS = new HashMap<DyeColor, RegistrySupplier<Block>>();

	public static final RegistrySupplier<Block> STONE_PLAYER = registerWithoutTab("stone_player", () -> new Block(of(Material.STONE).strength(0.5F).noOcclusion()));

	public static final RegistrySupplier<Block> GOLD_DOOR = register("gold_door", () -> new DoorBlock(of(Material.METAL, MaterialColor.GOLD).strength(5.0F).requiresCorrectToolForDrops().noCollission(), BlockSetType.IRON));

	public static final RegistrySupplier<Block> STONE_DOOR = register("stone_door", () -> new DoorBlock(of(Material.METAL, MaterialColor.WOOD).strength(5.0F).requiresCorrectToolForDrops().noOcclusion(), BlockSetType.IRON));

	public static final RegistrySupplier<Block> QUARTZ_DOOR = register("quartz_door", () -> new DoorBlock(of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).strength(5.0F).requiresCorrectToolForDrops().noOcclusion(), BlockSetType.IRON));

	public static final RegistrySupplier<Block> OAK_DIMENSIONAL_TRAPDOOR = registerWithoutTab("wood_dimensional_trapdoor", () -> new DimensionalTrapdoorBlock(of(Blocks.OAK_TRAPDOOR).lightLevel(state -> 10), BlockSetType.OAK));

	public static final RegistrySupplier<Block> DIMENSIONAL_PORTAL = registerWithoutTab("dimensional_portal", () -> new DimensionalPortalBlock(of(Material.AIR).noCollission().strength(-1.0F, 3600000.0F).noOcclusion().dropsLike(AIR).lightLevel(blockState -> 10)));

	public static final RegistrySupplier<Block> DETACHED_RIFT = registerWithoutTab("detached_rift", () -> new DetachedRiftBlock(of(Material.AIR, MaterialColor.COLOR_BLACK).strength(-1.0F, 3600000.0F).noCollission().noOcclusion()));

	public static final RegistrySupplier<Block> WHITE_FABRIC = registerFabric(DyeColor.WHITE);

	public static final RegistrySupplier<Block> ORANGE_FABRIC = registerFabric(DyeColor.ORANGE);

	public static final RegistrySupplier<Block> MAGENTA_FABRIC = registerFabric(DyeColor.MAGENTA);

	public static final RegistrySupplier<Block> LIGHT_BLUE_FABRIC = registerFabric(DyeColor.LIGHT_BLUE);

	public static final RegistrySupplier<Block> YELLOW_FABRIC = registerFabric(DyeColor.YELLOW);

	public static final RegistrySupplier<Block> LIME_FABRIC = registerFabric(DyeColor.LIME);

	public static final RegistrySupplier<Block> PINK_FABRIC = registerFabric(DyeColor.PINK);

	public static final RegistrySupplier<Block> GRAY_FABRIC = registerFabric(DyeColor.GRAY);

	public static final RegistrySupplier<Block> LIGHT_GRAY_FABRIC = registerFabric(DyeColor.LIGHT_GRAY);

	public static final RegistrySupplier<Block> CYAN_FABRIC = registerFabric(DyeColor.CYAN);

	public static final RegistrySupplier<Block> PURPLE_FABRIC = registerFabric(DyeColor.PURPLE);

	public static final RegistrySupplier<Block> BLUE_FABRIC = registerFabric(DyeColor.BLUE);

	public static final RegistrySupplier<Block> BROWN_FABRIC = registerFabric(DyeColor.BROWN);

	public static final RegistrySupplier<Block> GREEN_FABRIC = registerFabric(DyeColor.GREEN);

	public static final RegistrySupplier<Block> RED_FABRIC = registerFabric(DyeColor.RED);

	public static final RegistrySupplier<Block> BLACK_FABRIC = registerFabric(DyeColor.BLACK);


	public static final RegistrySupplier<Block> WHITE_ANCIENT_FABRIC = registerAncientFabric(DyeColor.WHITE);

	public static final RegistrySupplier<Block> ORANGE_ANCIENT_FABRIC = registerAncientFabric(DyeColor.ORANGE);

	public static final RegistrySupplier<Block> MAGENTA_ANCIENT_FABRIC = registerAncientFabric(DyeColor.MAGENTA);

	public static final RegistrySupplier<Block> LIGHT_BLUE_ANCIENT_FABRIC = registerAncientFabric(DyeColor.LIGHT_BLUE);

	public static final RegistrySupplier<Block> YELLOW_ANCIENT_FABRIC = registerAncientFabric(DyeColor.YELLOW);

	public static final RegistrySupplier<Block> LIME_ANCIENT_FABRIC = registerAncientFabric(DyeColor.LIME);

	public static final RegistrySupplier<Block> PINK_ANCIENT_FABRIC = registerAncientFabric(DyeColor.PINK);

	public static final RegistrySupplier<Block> GRAY_ANCIENT_FABRIC = registerAncientFabric(DyeColor.GRAY);

	public static final RegistrySupplier<Block> LIGHT_GRAY_ANCIENT_FABRIC = registerAncientFabric(DyeColor.LIGHT_GRAY);

	public static final RegistrySupplier<Block> CYAN_ANCIENT_FABRIC = registerAncientFabric(DyeColor.CYAN);

	public static final RegistrySupplier<Block> PURPLE_ANCIENT_FABRIC = registerAncientFabric(DyeColor.PURPLE);

	public static final RegistrySupplier<Block> BLUE_ANCIENT_FABRIC = registerAncientFabric(DyeColor.BLUE);

	public static final RegistrySupplier<Block> BROWN_ANCIENT_FABRIC = registerAncientFabric(DyeColor.BROWN);

	public static final RegistrySupplier<Block> GREEN_ANCIENT_FABRIC = registerAncientFabric(DyeColor.GREEN);

	public static final RegistrySupplier<Block> RED_ANCIENT_FABRIC = registerAncientFabric(DyeColor.RED);

	public static final RegistrySupplier<Block> BLACK_ANCIENT_FABRIC = registerAncientFabric(DyeColor.BLACK);
	private static final BlockBehaviour.Properties UNRAVELLED_FABRIC_BLOCK_SETTINGS = of(Material.STONE, MaterialColor.COLOR_BLACK).randomTicks().lightLevel(state -> 15).strength(0.3F, 0.3F);

	public static final RegistrySupplier<LiquidBlock> ETERNAL_FLUID = register("eternal_fluid", () -> new ArchitecturyLiquidBlock(ModFluids.ETERNAL_FLUID, of(Material.LAVA, MaterialColor.COLOR_RED).lightLevel(state -> 15)));

	public static final RegistrySupplier<LiquidBlock> LEAK = register("leak", () -> new ArchitecturyLiquidBlock(ModFluids.LEAK, BlockBehaviour.Properties.of(Material.WATER, MaterialColor.COLOR_GRAY)));

	public static final RegistrySupplier<Block> DECAYED_BLOCK = registerWithoutTab("decayed_block", () -> new UnravelledFabricBlock(UNRAVELLED_FABRIC_BLOCK_SETTINGS));

	public static final RegistrySupplier<Block> UNFOLDED_BLOCK = registerWithoutTab("unfolded_block", () -> new UnravelledFabricBlock(UNRAVELLED_FABRIC_BLOCK_SETTINGS));

	public static final RegistrySupplier<Block> UNWARPED_BLOCK = registerWithoutTab("unwarped_block", () -> new UnravelledFabricBlock(UNRAVELLED_FABRIC_BLOCK_SETTINGS));

	public static final RegistrySupplier<Block> UNRAVELLED_BLOCK = registerWithoutTab("unravelled_block", () -> new UnravelledFabricBlock(UNRAVELLED_FABRIC_BLOCK_SETTINGS));

	public static final RegistrySupplier<Block> UNRAVELLED_FABRIC = register("unravelled_fabric", () -> new UnravelledFabricBlock(UNRAVELLED_FABRIC_BLOCK_SETTINGS));

	public static final RegistrySupplier<Block> MARKING_PLATE = registerWithoutTab("marking_plate", () -> new MarkingPlateBlock(of(Material.METAL, DyeColor.BLACK).noOcclusion()));

	public static final RegistrySupplier<Block> SOLID_STATIC = register("solid_static", () -> new UnravelledFabricBlock(of(Material.STONE).strength(7, 25).randomTicks().requiresCorrectToolForDrops().sound(SoundType.SAND)));

	public static final RegistrySupplier<Block> TESSELATING_LOOM = register("tesselating_loom", () -> new TesselatingLoomBlock(of(LOOM)));

	public static final RegistrySupplier<Block> REALITY_SPONGE = register("reality_sponge", () -> new RealitySpongeBlock(UNRAVELLED_FABRIC_BLOCK_SETTINGS));

	//Decay graph filler.
	public static final RegistrySupplier<Block> DRIFTWOOD_WOOD = register("driftwood_wood", () -> new RotatedPillarBlock(of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistrySupplier<Block> DRIFTWOOD_LOG = register("driftwood_log", () -> new RotatedPillarBlock(of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY).strength(2.0F).sound(SoundType.WOOD)));
	public static final RegistrySupplier<Block> DRIFTWOOD_PLANKS = register("driftwood_planks", () -> new Block(of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistrySupplier<Block> DRIFTWOOD_LEAVES = register("driftwood_leaves", () -> new LeavesBlock(of(OAK_LEAVES)));
	public static final RegistrySupplier<Block> DRIFTWOOD_SAPLING = register("driftwood_sapling", () -> new Block(of(OAK_SAPLING)));
	public static final RegistrySupplier<Block> DRIFTWOOD_FENCE = registerFence("driftwood_fence", DRIFTWOOD_PLANKS);
	public static final RegistrySupplier<Block> DRIFTWOOD_GATE = registerFenceGate("driftwood_gate", DRIFTWOOD_PLANKS); // TODO: add driftwood wood type
	public static final RegistrySupplier<Block> DRIFTWOOD_BUTTON = registerButton("driftwood_button", DRIFTWOOD_PLANKS);
	public static final RegistrySupplier<Block> DRIFTWOOD_SLAB = registerSlab("driftwood_slab", DRIFTWOOD_PLANKS);
	public static final RegistrySupplier<Block> DRIFTWOOD_STAIRS = registerStairs("driftwood_stairs", DRIFTWOOD_PLANKS);
	public static final RegistrySupplier<Block> DRIFTWOOD_DOOR = register("driftwood_door", () -> new DoorBlock(of(Material.WOOD, MaterialColor.COLOR_GRAY).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), BlockSetType.OAK));
	public static final RegistrySupplier<Block> DRIFTWOOD_TRAPDOOR = register("driftwood_trapdoor", () -> new TrapDoorBlock(of(Material.WOOD, MaterialColor.COLOR_GRAY).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn((state, world, pos, type) -> false), BlockSetType.OAK));

	public static final RegistrySupplier<Block> AMALGAM_BLOCK = register("amalgam_block", () -> new Block(of(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final RegistrySupplier<Block> AMALGAM_DOOR = register("amalgam_door", () -> new DoorBlock(of(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion(), BlockSetType.IRON));
	public static final RegistrySupplier<Block> AMALGAM_TRAPDOOR = register("amalgam_trapdoor", () -> new TrapDoorBlock(of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).isValidSpawn((state, world, pos, type) -> false), BlockSetType.IRON));
	public static final RegistrySupplier<Block> RUST = register("rust", () -> new Block(of(Material.WOOD)));
	public static final RegistrySupplier<Block> AMALGAM_SLAB = registerSlab("amalgam_slab", AMALGAM_BLOCK);
	public static final RegistrySupplier<Block> AMALGAM_STAIRS = registerStairs("amalgam_stairs", AMALGAM_BLOCK);
	public static final RegistrySupplier<Block> AMALGAM_ORE = register("amalgam_ore", () -> new DropExperienceBlock(of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

	public static final RegistrySupplier<Block> CLOD_ORE = register("clod_ore", () -> new Block(of(Material.AMETHYST)));
	public static final RegistrySupplier<Block> CLOD_BLOCK = register("clod_block", () -> new Block(of(Material.AMETHYST)));

	public static final RegistrySupplier<Block> GRAVEL_FENCE = registerFence("gravel_fence", GRAVEL);
	public static final RegistrySupplier<Block> GRAVEL_BUTTON = registerButton("gravel_button", GRAVEL);
	public static final RegistrySupplier<Block> GRAVEL_SLAB = registerSlab("gravel_slab", GRAVEL);
	public static final RegistrySupplier<Block> GRAVEL_STAIRS = registerStairs("gravel_stairs", GRAVEL);
	public static final RegistrySupplier<Block> GRAVEL_WALL = registerWall("gravel_wall", GRAVEL);

	public static final RegistrySupplier<Block> DARK_SAND = register("dark_sand", () -> new Block(of(Material.SAND, MaterialColor.COLOR_BLACK).strength(0.5F).sound(SoundType.SAND)));
	public static final RegistrySupplier<Block> DARK_SAND_FENCE = registerFence("dark_sand_fence", DARK_SAND);
	public static final RegistrySupplier<Block> DARK_SAND_BUTTON = registerButton("dark_sand_button", DARK_SAND);
	public static final RegistrySupplier<Block> DARK_SAND_SLAB = registerSlab("dark_sand_slab", DARK_SAND);
	public static final RegistrySupplier<Block> DARK_SAND_STAIRS = registerStairs("dark_sand_stairs", DARK_SAND);
	public static final RegistrySupplier<Block> DARK_SAND_WALL = registerWall("dark_sand_wall", DARK_SAND);

	public static final RegistrySupplier<Block> CLAY_FENCE = registerFence("clay_fence", CLAY);
	public static final RegistrySupplier<Block> CLAY_GATE = registerFenceGate("clay_gate", CLAY);
	public static final RegistrySupplier<Block> CLAY_BUTTON = registerButton("clay_button", CLAY);
	public static final RegistrySupplier<Block> CLAY_SLAB = registerSlab("clay_slab", CLAY);
	public static final RegistrySupplier<Block> CLAY_STAIRS = registerStairs("clay_stairs", CLAY);

	public static final RegistrySupplier<Block> CLAY_WALL = registerWall("clay_wall", CLAY);

	public static final RegistrySupplier<Block> MUD_FENCE = registerFence("mud_fence", MUD);
	public static final RegistrySupplier<Block> MUD_GATE = registerFenceGate("mud_gate", MUD);
	public static final RegistrySupplier<Block> MUD_BUTTON = registerButton("mud_button", MUD);
	public static final RegistrySupplier<Block> MUD_SLAB = registerSlab("mud_slab", MUD);
	public static final RegistrySupplier<Block> MUD_STAIRS = registerStairs("mud_stairs", MUD);

	public static final RegistrySupplier<Block> UNRAVELED_FENCE = registerFence("unraveled_fence", UNRAVELLED_FABRIC);
	public static final RegistrySupplier<Block> UNRAVELED_GATE = registerFenceGate("unraveled_gate", UNRAVELLED_FABRIC);
	public static final RegistrySupplier<Block> UNRAVELED_BUTTON = registerButton("unraveled_button", UNRAVELLED_FABRIC);
	public static final RegistrySupplier<Block> UNRAVELED_SLAB = registerSlab("unraveled_slab", UNRAVELLED_FABRIC);
	public static final RegistrySupplier<Block> UNRAVELED_STAIRS = registerStairs("unraveled_stairs", UNRAVELLED_FABRIC);

	public static final RegistrySupplier<Block> DEEPSLATE_SLAB = registerSlab("deepslate_slab", Blocks.DEEPSLATE);
	public static final RegistrySupplier<Block> DEEPSLATE_STAIRS = registerStairs("deepslate_stairs", Blocks.DEEPSLATE);
	public static final RegistrySupplier<Block> DEEPSLATE_WALL = registerWall("deepslate_wall", Blocks.DEEPSLATE);

	public static final RegistrySupplier<Block> RED_SAND_SLAB = registerSlab("red_sand_slab", Blocks.RED_SAND);
	public static final RegistrySupplier<Block> RED_SAND_STAIRS = registerStairs("red_sand_stairs", Blocks.RED_SAND);
	public static final RegistrySupplier<Block> RED_SAND_WALL = registerWall("red_sand_wall", Blocks.RED_SAND);

	public static final RegistrySupplier<Block> SAND_SLAB = registerSlab("sand_slab", Blocks.SAND);
	public static final RegistrySupplier<Block> SAND_STAIRS = registerStairs("sand_stairs", Blocks.SAND);
	public static final RegistrySupplier<Block> SAND_WALL = registerWall("sand_wall", Blocks.SAND);

	public static final RegistrySupplier<Block> END_STONE_SLAB = registerSlab("end_stone_slab", Blocks.END_STONE);
	public static final RegistrySupplier<Block> END_STONE_STAIRS = registerStairs("end_stone_stairs", Blocks.END_STONE);
	public static final RegistrySupplier<Block> END_STONE_WALL = registerWall("end_stone_wall", Blocks.END_STONE);

 	public static final RegistrySupplier<Block> NETHERRACK_FENCE = registerFence("netherrack_fence", Blocks.NETHERRACK);
 	public static final RegistrySupplier<Block> NETHERRACK_SLAB = registerSlab("netherrack_slab", Blocks.NETHERRACK);
 	public static final RegistrySupplier<Block> NETHERRACK_STAIRS = registerStairs("netherrack_stairs", Blocks.NETHERRACK);
 	public static final RegistrySupplier<Block> NETHERRACK_WALL = registerWall("netherrack_wall", Blocks.NETHERRACK);

	public static final RegistrySupplier<Block> UNRAVELED_SPIKE = register("unraveled_spike", () -> new PointedDripstoneBlock(of(UNRAVELLED_FABRIC.get()).lightLevel(state -> 0))); //TODO: make this proper class later
	public static final RegistrySupplier<Block> GRITTY_STONE = register("gritty_stone", () -> new Block(of(STONE)));

	public static void init() {
		BLOCKS.register();
		BLOCK_ITEMS.register();
	}

	private static RegistrySupplier<Block> registerWithoutTab(String name, Supplier<Block> block) {
		return BLOCKS.register(name, block);
	}

	private static RegistrySupplier<Block> registerAncientFabric(DyeColor color) {
		RegistrySupplier<Block> block = register(color.getSerializedName() + "_ancient_fabric", () -> new AncientFabricBlock(color));
		ANCIENT_FABRIC_BLOCKS.put(color, block);
		return block;
	}

	private static RegistrySupplier<Block> registerFabric(DyeColor color) {
		RegistrySupplier<Block> block = register(color.getSerializedName() + "_fabric", () -> new FabricBlock(color));
		FABRIC_BLOCKS.put(color, block);
		return block;
	}

	@Environment(EnvType.CLIENT)
	public static void initClient() {
		RenderTypeRegistry.register(RenderType.cutout(), ModBlocks.QUARTZ_DOOR.get(), ModBlocks.GOLD_DOOR.get(), ModBlocks.DRIFTWOOD_LEAVES.get(), ModBlocks.UNRAVELED_SPIKE.get());
	}

	public static RegistrySupplier<Block> ancientFabricFromDye(DyeColor color) {
		return ANCIENT_FABRIC_BLOCKS.get(color);
	}

	public static RegistrySupplier<Block> fabricFromDye(DyeColor color) {
		return FABRIC_BLOCKS.get(color);
	}

	public static <T extends Block> RegistrySupplier<T> register(String name, Supplier<T> block) {
		var supplier = BLOCKS.register(name, block);
		BLOCK_ITEMS.register(name, () -> new BlockItem(supplier.get(), new Item.Properties().arch$tab(DIMENSIONAL_DOORS)));

		return supplier;
	}

	public static RegistrySupplier<Block> registerFence(String name, Block block) {
		return register(name, () -> new FenceBlock(of(block)));
	}

	public static RegistrySupplier<Block> registerFence(String name, RegistrySupplier<Block> block) {
		return register(name, () -> new FenceBlock(of(block.get())));
	}

	public static RegistrySupplier<Block> registerFenceGate(String name, Block block) {
		return register(name, () -> new FenceGateBlock(of(block), WoodType.OAK)); // TODO: parameterize WoodType and BlockSetType
	}

	public static RegistrySupplier<Block> registerFenceGate(String name, RegistrySupplier<Block> block) {
		return register(name, () -> new FenceGateBlock(of(block.get()), WoodType.OAK)); // TODO: parameterize WoodType and BlockSetType
	}

	public static RegistrySupplier<Block> registerButton(String name, Block block) {
		return register(name, () -> new ButtonBlock(of(block).noCollission().strength(0.5F), BlockSetType.STONE, 20, false));
	}

	public static RegistrySupplier<Block> registerButton(String name, RegistrySupplier<Block> block) {
		return register(name, () -> new ButtonBlock(of(block.get()).noCollission().strength(0.5F), BlockSetType.STONE, 20, false));
	}

	public static RegistrySupplier<Block> registerSlab(String name, Block block) {
		return register(name, () -> new SlabBlock(of(block)));
	}

	public static RegistrySupplier<Block> registerSlab(String name, RegistrySupplier<Block> block) {
		return register(name, () -> new SlabBlock(of(block.get())));
	}

	public static RegistrySupplier<Block> registerStairs(String name, Block block) {
		return register(name, () -> new StairBlock(block.defaultBlockState(), of(block)));
	}

	public static RegistrySupplier<Block> registerStairs(String name, RegistrySupplier<Block> block) {
		return register(name, () -> {
			var b = block.get();
			return new StairBlock(b.defaultBlockState(), of(b));
		});
	}

	public static RegistrySupplier<Block> registerWall(String name, Block block) {
		return register(name, () -> new WallBlock(of(block)));
	}

	public static RegistrySupplier<Block> registerWall(String name, RegistrySupplier<Block> block) {
		return register(name, () -> new WallBlock(of(block.get())));
	}
	
	private static BlockBehaviour.Properties of(Material material, MaterialColor color) {
		return BlockBehaviour.Properties.of(material, color);
	}

	private static BlockBehaviour.Properties of(Material material) {
		return BlockBehaviour.Properties.of(material);
	}

	private static BlockBehaviour.Properties of(Material material, DyeColor dyeColor) {
		return BlockBehaviour.Properties.of(material, dyeColor);
	}

	private static BlockBehaviour.Properties of(Block block) {
		return BlockBehaviour.Properties.copy(block);
	}
}
