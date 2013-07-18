package StevenDimDoors.mod_pocketDim;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

import StevenDimDoors.mod_pocketDim.helpers.dimHelper;
import StevenDimDoors.mod_pocketDim.helpers.yCoordHelper;
import StevenDimDoors.mod_pocketDim.ticking.MobObelisk;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;

public class CommonTickHandler implements ITickHandler
{
	private Random rand = new Random();
	public int tickCount=0;
	public int tickCount2=0;
	private static DDProperties properties = null;
	public static ArrayList<int[]> chunksToPopulate= new ArrayList<int[]>();
	public static final int MAX_MONOLITH_SPAWNING_CHANCE = 100;
	private static final int MAX_MONOLITH_SPAWN_Y = 245;
	private static final int CHUNK_SIZE = 16;

	
	public CommonTickHandler()
	{
		if (properties == null)
			properties = DDProperties.instance();
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) 
	{
		if (type.equals(EnumSet.of(TickType.SERVER)))
		{
			onTickInGame();
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		if (type.equals(EnumSet.of(TickType.SERVER)))
		{
			if(!CommonTickHandler.chunksToPopulate.isEmpty())
			{
				for(int[] chunkData : CommonTickHandler.chunksToPopulate)
				{
					if(chunkData[0]==properties.LimboDimensionID)
					{
						this.placeMonolithsInLimbo(chunkData[0], chunkData[1], chunkData[2]);
					}
					else
					{
						this.placeMonolithsInPockets(chunkData[0], chunkData[1], chunkData[2]);
					}
					
				}
			}
			CommonTickHandler.chunksToPopulate.clear();
		}
	}

	public EnumSet ticks()
	{
		return EnumSet.of(TickType.SERVER);
	}

	public String getLabel()
	{
		return null;
	}

	private void placeMonolithsInPockets(int worldID, int chunkX, int chunkZ)
	{
		World worldObj = dimHelper.getWorld(worldID);
		DimData dimData = dimHelper.dimList.get(worldObj.provider.dimensionId);
        int sanity = 0;
        int blockID = 0;
        boolean didSpawn=false;
        
        if (dimData == null ||
        	dimData.dungeonGenerator == null ||
        	dimData.dungeonGenerator.isOpen)
        {
        	return;
        }
        
		//The following initialization code is based on code from ChunkProviderGenerate.
		//It makes our generation depend on the world seed.
		Random random = new Random(worldObj.getSeed());
        long factorA = random.nextLong() / 2L * 2L + 1L;
        long factorB = random.nextLong() / 2L * 2L + 1L;
        random.setSeed((long)chunkX * factorA + (long)chunkZ * factorB ^ worldObj.getSeed());
	    
		int x, y, z;
		do
		{
			//Select a random column within the chunk
			x = chunkX * CHUNK_SIZE + random.nextInt(CHUNK_SIZE);
			z = chunkZ * CHUNK_SIZE + random.nextInt(CHUNK_SIZE);
			y = MAX_MONOLITH_SPAWN_Y;
			blockID = worldObj.getBlockId(x, y, z);
			
			while (blockID == 0 &&y>0)
			{
				y--;
				blockID = worldObj.getBlockId(x, y, z);
 
			}
			while((blockID == mod_pocketDim.blockDimWall.blockID||blockID == mod_pocketDim.blockDimWallPerm.blockID)&&y>0)
			{
				y--;
				blockID = worldObj.getBlockId(x, y, z);
			}
			while (blockID == 0 &&y>0)
			{
				y--;
				blockID = worldObj.getBlockId(x, y, z);
 
			}
			if(y > 0)
			{
				
				

				int jumpSanity=0;
				int jumpHeight=0;
				do
				{
				
					jumpHeight = y+random.nextInt(10);
					
					jumpSanity++;
				}
				while(!worldObj.isAirBlock(x,jumpHeight+6 , z)&&jumpSanity<20);
				
				
				

				Entity mob = new MobObelisk(worldObj);
				mob.setLocationAndAngles(x, jumpHeight, z, 1, 1);
				worldObj.spawnEntityInWorld(mob);
				didSpawn=true;
			}

			sanity++;
			
		}
		while (sanity<5&&!didSpawn);
	}
	
	private void placeMonolithsInLimbo(int worldID, int var2, int var3)
	{
		World world = dimHelper.getWorld(worldID);
		
		if (rand.nextInt(MAX_MONOLITH_SPAWNING_CHANCE) < properties.MonolithSpawningChance)
		{
			int y =0;
			int x = var2*16 + rand.nextInt(16);
			int z = var3*16 + rand.nextInt(16);
			int yTest;
			do
			{
	
				x = var2*16 + rand.nextInt(16);
				z = var3*16 + rand.nextInt(16);
	
				while(world.getBlockId(x, y, z)==0&&y<255)
				{
					y++;
				}
				y = yCoordHelper.getFirstUncovered(world,x , y+2, z);
	
				yTest=yCoordHelper.getFirstUncovered(world,x , y+5, z);
				if(yTest>245)
				{
					return;
				}
				
				int jumpSanity=0;
				int jumpHeight=0;
				do
				{
					jumpHeight = y+rand.nextInt(25);
					
					jumpSanity++;
				}
				while(!world.isAirBlock(x,jumpHeight+6 , z)&&jumpSanity<20);
				
				
				Entity mob = new MobObelisk(world);
				mob.setLocationAndAngles(x, jumpHeight, z, 1, 1);
	
				
				world.spawnEntityInWorld(mob);
	
			}
			while (yTest > y);
		}
	}
	//replaces rifts in game that have been destroyed/have blocks placed over them. 
	private void onTickInGame()
	{

		try
		{

			if(tickCount>100)
			{
				tickCount=0;
				int i=0;


				while (i<15&&FMLCommonHandler.instance().getEffectiveSide()==Side.SERVER)
				{
					i++;
					LinkData link;

					//actually gets the random rift based on the size of the list
					link = (LinkData) dimHelper.instance.getRandomLinkData(true);



					if(link!=null)
					{

						if(dimHelper.getWorld(link.locDimID)!=null)
						{
							World world=dimHelper.getWorld(link.locDimID);

							int blocktoReplace = world.getBlockId(link.locXCoord, link.locYCoord, link.locZCoord);

							if(!mod_pocketDim.blocksImmuneToRift.contains(blocktoReplace))//makes sure the rift doesn't replace a door or something
							{
								if(dimHelper.instance.getLinkDataFromCoords(link.locXCoord, link.locYCoord, link.locZCoord, link.locDimID)==null)
								{
								}
								else
								{
									dimHelper.getWorld(link.locDimID).setBlock(link.locXCoord, link.locYCoord, link.locZCoord, properties.RiftBlockID);
									TileEntityRift.class.cast(dimHelper.getWorld(link.locDimID).getBlockTileEntity(link.locXCoord, link.locYCoord, link.locZCoord)).hasGrownRifts=true;
								}
							}
						}
					}
				}
			}


		}
		catch (Exception e)
		{
			tickCount++;
			System.out.println("something on tick went wrong: " + e);
			e.printStackTrace();
		}
		tickCount++;

		//this section regulates decay in Limbo- it records any blocks placed by the player and later progresss them through the decay cycle
		if(tickCount2>10&&dimHelper.blocksToDecay!=null)
		{
			tickCount2=0;
			if(!dimHelper.blocksToDecay.isEmpty()&&dimHelper.getWorld(properties.LimboDimensionID)!=null)
			{


				if(dimHelper.blocksToDecay.size()>rand.nextInt(400))
				{
					int index = rand.nextInt(dimHelper.blocksToDecay.size());
					Point3D point = (Point3D) dimHelper.blocksToDecay.get(index);

					int blockID = dimHelper.getWorld(properties.LimboDimensionID).getBlockId(point.getX(), point.getY(), point.getZ());
					int idToSet=Block.stone.blockID;

					if(blockID==0||blockID==properties.LimboBlockID)
					{
						dimHelper.blocksToDecay.remove(index);
					}
					else
					{
						if(Block.blocksList[idToSet] instanceof BlockContainer)
						{
							idToSet=-1;
							dimHelper.blocksToDecay.remove(index); 
						}



						if(blockID==Block.cobblestone.blockID)
						{
							idToSet=Block.gravel.blockID;
						}
						if(blockID==Block.stone.blockID)
						{
							idToSet=Block.cobblestone.blockID;

						}
						if(blockID==Block.gravel.blockID&&!dimHelper.getWorld(properties.LimboDimensionID).isAirBlock(point.getX(), point.getY()-1, point.getZ()))
						{
							idToSet=properties.LimboBlockID;
							dimHelper.getWorld(properties.LimboDimensionID).scheduleBlockUpdate(point.getX(), point.getY(), point.getZ(),10, idToSet);

						}
						else if(blockID==Block.gravel.blockID)
						{
							dimHelper.blocksToDecay.remove(index);
							idToSet=-1;

						}

						if(idToSet!=-1)
						{

							dimHelper.getWorld(properties.LimboDimensionID).setBlock(point.getX(), point.getY(), point.getZ(), idToSet);

						}    		
					}   		    		
				}   		
			}
		}

		tickCount2++;

		if(mod_pocketDim.teleTimer>0)
		{
			mod_pocketDim.teleTimer--;
		}






	}

}
