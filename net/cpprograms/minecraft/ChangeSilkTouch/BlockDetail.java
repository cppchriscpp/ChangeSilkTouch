package net.cpprograms.minecraft.ChangeSilkTouch;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

class BlockDetail {
	private int blockId;
	private int dropCount;
	private int dropType;
	
	/**
	 * Defines a blockdetail using only the block's id
	 * @param _blockId The id of the block
	 */
	public BlockDetail(int _blockId) 
	{
		blockId = _blockId;
		dropType = _blockId;
		dropCount = 1;
	}
	
	/**
	 * Defines a blockdetail using the block id and the drop id.
	 * @param _blockId Id of the block.
	 * @param _dropType Id of the block to drop.
	 */
	public BlockDetail(int _blockId, int _dropType) 
	{
		blockId = _blockId;
		dropType = _dropType;
		dropCount = 1;
	}
	
	/**
	 * Full constructor for BlockDetail. Use this.
	 * @param _blockId Id of the block.
	 * @param _dropType Id of the block to drop.
	 * @param _dropCount Number of blocks to drop.
	 */
	public BlockDetail(int _blockId, int _dropType, int _dropCount) 
	{
		blockId = _blockId;
		dropType = _dropType;
		dropCount = _dropCount;
	}
	
	/**
	 * Get the ID of the block that must be broken.
	 * @return ID of the block that must be broken.
	 */
	public int getBlockId() 
	{
		return blockId;
	}
	
	/**
	 * Get the ID of the block to be dropped.
	 * @return Id of the block to be dropped.
	 */
	public int getDropId()
	{
		return dropType;
	}
	
	/**
	 * Get the number of drops to release.
	 * @return The number of drops to release.
	 */
	public int getCount()
	{
		return dropCount;
	}
	
	/**
	 * Drop the item represented by this into the world at that location.
	 * @param location The location to drop the item into.
	 */
	public void drop(Location location) 
	{
		ItemStack ourstuff = new ItemStack(dropCount, dropType);
		location.getWorld().dropItemNaturally(location, ourstuff);
	}
}