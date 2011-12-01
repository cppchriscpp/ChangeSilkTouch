package net.cpprograms.minecraft.ChangeSilkTouch;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.reader.UnicodeReader;

/**
 * ChangeSilkTouch -- Make SilkTouch do what you want it to.
 *
 * @author cppchriscpp
 */
public class ChangeSilkTouch extends JavaPlugin {

    /**
     * A Block listener.
     */
    private final ChangeSilkTouchBlockListener blockListener = new ChangeSilkTouchBlockListener(this);

	/**
	 * YAML config reader
	 */
	private final Yaml yaml = new Yaml(new SafeConstructor());
	
	public Map<Integer, BlockDetail> blockList;

	/**
	 * Called upon enabling the plugin
	 */
    @SuppressWarnings({ "unchecked" })
	public void onEnable() {

    	blockList = new HashMap<Integer, BlockDetail>();
    	
		// Read in the YAML config stuff
		try
		{
			FileInputStream fIn = new FileInputStream(new File(this.getDataFolder(), "config.yml"));
			Map<String, Object> data = (Map<String, Object>)yaml.load(new UnicodeReader(fIn));
			ArrayList<Map<String, Object>> blocks;
			// Yeah, this is kind of bad practice. Might have settings one day or something.
			if (data.containsKey("blocks"))
				blocks = (ArrayList<Map<String, Object>>)data.get("blocks");
			else {
				System.out.println("ChangeSilkTouch: No blocks in configuration file! Are you sure you configured right?");
				return;
			}
			for (Map<String, Object> obj : blocks) {
				int block, newblock, count=1;
				if (!obj.containsKey("blockType")) 
				{
					System.out.println("ChangeSilkTouch: One of the rows in config.yml had a blank block type. It has been skipped.");
					continue;
				}
				block = Integer.parseInt(obj.get("blockType").toString());
				if (obj.containsKey("dropType"))
					newblock = Integer.parseInt(obj.get("dropType").toString());
				else
					newblock = block;
				if (obj.containsKey("dropCount"))
					count = Integer.parseInt(obj.get("dropCount").toString());
					
				blockList.put(block, new BlockDetail(block, count, newblock));
				
			}


		}
		catch (IOException i)
		{
			System.out.println("ChangeSilkTouch: Could not load the configuration file! Please put a config file in the plugin's folder.");
			return;
		}
		catch (java.lang.NumberFormatException i)
		{
			System.out.println("ChangeSilkTouch: An exception occurred when trying to read your config file.");
			System.out.println("ChangeSilkTouch: Check your config.yml!");
			return;
		}

		if (!this.getDataFolder().exists())
		{
			System.out.println("ChangeSilkTouch: Could not read plugin's data folder! Please put the TravelPortals folder in the plugins folder with the plugin!");
			System.out.println("ChangeSilkTouch: Aborting plugin load");
			return;
		}

        // Register our events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Priority.High, this);
		
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    }

    /*
     * Called upon disabling the plugin.
     */
    public void onDisable() {
    }
}

