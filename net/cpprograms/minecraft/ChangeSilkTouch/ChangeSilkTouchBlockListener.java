package net.cpprograms.minecraft.ChangeSilkTouch;

import org.bukkit.Material;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.enchantments.Enchantment;

/**
 * ChangeSilkTouch block listener
 * @author cppchriscpp
 */
public class ChangeSilkTouchBlockListener extends BlockListener {
    private final ChangeSilkTouch plugin;

    /**
     * Constructor
     * @param plugin The plugin to attach this to.
     */
    public ChangeSilkTouchBlockListener(final ChangeSilkTouch plugin) {
        this.plugin = plugin;
    }


    /**
     * Called when a block is broken; let us know that we need to pay attention now.
     * @param event The block breaking event.
     */
    public void onBlockBreak(BlockBreakEvent event)
    {
    	if (!event.isCancelled() && plugin.blockList.containsKey(event.getBlock().getTypeId()))
    	{
    		if (event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH))
    		{
    			event.setCancelled(true);
    			plugin.blockList.get(event.getBlock().getTypeId()).drop(event.getBlock().getLocation());
    			event.getPlayer().getItemInHand().setDurability((short)(event.getPlayer().getItemInHand().getDurability()-1));
    			event.getBlock().setType(Material.AIR);
    		}
    	}
    }
}
