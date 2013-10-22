package com.gmail.mmonkey.Refill;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlaceBlock implements Listener {

	private Refill plugin;

	public PlaceBlock(Refill plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		
		final Player player = event.getPlayer();
		
		if(player.hasPermission("refill.use")) {
			
			if(plugin.refillList.containsKey(player)) {
				
				RefillPlayer p = plugin.refillList.get(player);
				
				if(p.isEnabled()){
					
					final Material blockType = event.getBlockPlaced().getType();
					ItemStack inHand = player.getItemInHand();
					final Inventory playerInventory = player.getInventory();
			
					if(inHand.getAmount() == 1) {
				
						if(isReplaceable(blockType) && playerInventory.contains(blockType)) {
							this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
								public void run() {
									fillup(player, playerInventory.first(blockType));
								}
							}, 1L); //delays fill-up by 1 tick
							return;
						}
					}
				}
			} else {
				plugin.refillList.put(player, new RefillPlayer(player, plugin.enabled));
			}
		}
	}
	
	public void fillup(Player player, int index){
		Inventory playerInventory = player.getInventory();
		
		if(index != -1 && index <= 8){
			player.getInventory().setHeldItemSlot(index);
			return;
		} else {
			if (index != -1 && player.getItemInHand().getType() == Material.AIR) {
				player.setItemInHand(playerInventory.getItem(index));
				playerInventory.setItem(index, null);
				return;
			}
		} 
	}
	
	private boolean isReplaceable(Material block) {
		return plugin.configBlocks.contains(block);
	}
}
