package net.cpprograms.minecraft.ChangeSilkTouch;

import net.cpprograms.minecraft.General.PluginBase;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import org.bukkit.plugin.PluginManager;

/**
 * ChangeSilkTouch -- Make SilkTouch do what you want it to.
 *
 * @author cppchriscpp
 */
public class ChangeSilkTouch extends PluginBase {

    /**
     * A Block listener.
     */
    private final ChangeSilkTouchBlockListener blockListener = new ChangeSilkTouchBlockListener(this);
	
	/**
	 * The blocks being used.
	 */
	public Map<Integer, BlockDetail> blockList;

	/**
	 * Called upon enabling the plugin
	 */
	@SuppressWarnings("unchecked")
	public void onEnable() {

    	blockList = new HashMap<Integer, BlockDetail>();

		// Read in the YAML config stuff
		try
		{
			ArrayList<Map<String, Object>> blocks;
			if (getConfig().contains("blocks"))
				blocks = (ArrayList<Map<String, Object>>)getConfig().get("blocks");
			else {
				logSevere("No blocks in configuration file! Are you sure your configuration file is correct?");
				return;
			}
			for (Map<String, Object> obj : blocks) {
				int block, newblock, count=1;
				if (!obj.containsKey("blockType")) 
				{
					logWarning("One of the rows in config.yml had a blank block type. It has been skipped.");
					continue;
				}
				block = Integer.parseInt(obj.get("blockType").toString());
				if (obj.containsKey("dropType"))
					newblock = Integer.parseInt(obj.get("dropType").toString());
				else
					newblock = block;
				if (obj.containsKey("count"))
					count = Integer.parseInt(obj.get("count").toString());
					
				blockList.put(block, new BlockDetail(block, count, newblock));
				
			}


		}
		catch (ClassCastException i)
		{
			logSevere("Could not load the configuration file! Please put a config file in the plugin's folder.");
			return;
		}
		catch (java.lang.NumberFormatException i)
		{
			logSevere("An exception occurred when trying to read your config file.");
			logSevere("Check your config.yml!");
			return;
		}

		if (!this.getDataFolder().exists())
		{
			logSevere("Could not read plugin's data folder! Please put the ChangeSilkTouch folder in the plugins folder with the plugin!");
			logSevere("Aborting plugin load");
			return;
		}

        // Register our events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(blockListener, this);
		
        super.onEnable();
    }

    /*
     * Called upon disabling the plugin.
     */
    public void onDisable() {
    	super.onDisable();
    }
}

