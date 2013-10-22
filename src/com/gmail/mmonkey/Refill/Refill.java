package com.gmail.mmonkey.Refill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Refill extends JavaPlugin {
	
	public Logger log = Logger.getLogger("Minecraft");
	public HashMap<Player, RefillPlayer> refillList = new HashMap<Player, RefillPlayer>();
	public boolean enabled = false;
	
	public ArrayList<Material> configBlocks = new ArrayList<Material>();

	public void onEnable() {
		
		long start = System.currentTimeMillis();
		
		load();
		
		getLogger().info("Refill has been Enabled!");
        getServer().getPluginManager().registerEvents(new PlaceBlock(this), this);
        getCommand("refill").setExecutor(new Commands(this));
        
        log.info("[Refill] By mmonkey loaded in " + (System.currentTimeMillis() - start) / 1000.0D + " seconds.");
    }
	public void load(){
		
		this.saveDefaultConfig();
		
		this.enabled = this.getConfig().getBoolean("general.auto-enabled");
		List<String> loadBlocks = Refill.this.getConfig().getStringList("blocks");
		
		for(String s: loadBlocks) {
			if(Material.getMaterial(s) != null){
				configBlocks.add(Material.getMaterial(s));
			}
		}
		
		log.info("[Refill] Config Loaded.");
	}
	public void onDisable() {
    	getLogger().info("Refill has been Disabled.");
    }
}
