package com.gmail.mmonkey.AutoRefill;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlaceBlock implements Listener {

	private AutoRefill plugin;

	public PlaceBlock(AutoRefill plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		
		final Player player = event.getPlayer();
		
		if(player.hasPermission("autorefill.use")) {
			
			if(plugin.refillList.containsKey(player)) {
				
				RefillPlayer p = plugin.refillList.get(player);
				
				if(p.isEnabled()){
					
					final Material blockType = event.getBlockPlaced().getType();
					final ItemStack inHand = player.getItemInHand();
					final Inventory playerInventory = player.getInventory();
					final short durability = inHand.getDurability();
			
					if(inHand.getAmount() == 1) {
				
						if(isRefillable(blockType) && playerInventory.contains(blockType)) {
							
							//Delays refill by 1 tick
							this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
								public void run() {
									int index = -1;
									for(ItemStack i: playerInventory) {
										index += 1;
										if(i != null) {
											if(i.getDurability() == durability) {
												refill(player, index);
												return;
											}
										}
									}
								}
							}, 1L);
							return;
						}
					}
				}
			} else {
				plugin.refillList.put(player, new RefillPlayer(player, plugin.enabled));
			}
		}
	}
	
	public void refill(Player player, int index){
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
	
	private boolean isRefillable(Material block) {
		return plugin.configBlocks.contains(block);
	}
}
