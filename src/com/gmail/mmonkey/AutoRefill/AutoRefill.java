package com.gmail.mmonkey.AutoRefill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoRefill extends JavaPlugin {
	
	public Logger log = Logger.getLogger("Minecraft");
	public HashMap<Player, RefillPlayer> refillList = new HashMap<Player, RefillPlayer>();
	public boolean enabled = false;
	
	public ArrayList<Material> configBlocks = new ArrayList<Material>();

	public void onEnable() {
		
		long start = System.currentTimeMillis();
		
		load();
		
		getLogger().info("AutoRefill has been Enabled!");
        getServer().getPluginManager().registerEvents(new PlaceBlock(this), this);
        getCommand("refill").setExecutor(new Commands(this));
        
        log.info("[AutoRefill] By mmonkey loaded in " + (System.currentTimeMillis() - start) / 1000.0D + " seconds.");
    }
	public void load(){
		
		this.saveDefaultConfig();
		
		this.enabled = this.getConfig().getBoolean("general.auto-enabled");
		List<String> loadBlocks = AutoRefill.this.getConfig().getStringList("blocks");
		
		for(String s: loadBlocks) {
			if(Material.getMaterial(s) != null){
				configBlocks.add(Material.getMaterial(s));
			}
		}
		
		//Start metrics
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		} catch (IOException e) {
		    // Failed to submit the stats :-(
		}
	}
	public void onDisable() {
    	getLogger().info("AutoRefill has been Disabled.");
    }
}
