package net.cpprograms.minecraft.ChangeSilkTouch;

import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.enchantments.Enchantment;

/**
 * ChangeSilkTouch block listener
 * @author cppchriscpp
 */
public class ChangeSilkTouchBlockListener extends BlockListener {
    private final ChangeSilkTouch plugin;

    public ChangeSilkTouchBlockListener(final ChangeSilkTouch plugin) {
        this.plugin = plugin;
    }


    /**
     * Called when a block is broken; let us know if a portal is being destroyed.
     */
    public void onBlockBreak(BlockBreakEvent event)
    {
    	if (!event.isCancelled() && plugin.blockList.containsKey(event.getBlock().getTypeId()))
    	{
    		if (event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH))
    		{
    			event.setCancelled(true);
    			plugin.blockList.get(event.getBlock().getTypeId()).drop(event.getPlayer().getWorld(), event.getBlock().getLocation());
    			event.getBlock().setType(Material.AIR);
    		}
    	}
    }
}
