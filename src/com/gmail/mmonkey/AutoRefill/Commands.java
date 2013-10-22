package com.gmail.mmonkey.AutoRefill;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{
	
	private AutoRefill plugin;
	
	Commands(AutoRefill plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = null;
		
		//Check to see if sender is a player
		if(sender instanceof Player) {
			player = (Player) sender;
		} else {
			sender.sendMessage("Only players may use this command!");
			return true;
		}

		//Add player to refillList if not already
		if(!plugin.refillList.containsKey(player)) {
			plugin.refillList.put(player, new RefillPlayer(player, plugin.enabled));
		}
		
		RefillPlayer p = plugin.refillList.get(player);
		
		//If command has no arguments, show whether AutoRefill is enabled or disabled.
		if(args.length == 0) {
					
			if(p.isEnabled()) {
				player.sendMessage(ChatColor.GREEN + "AutoRefill is enabled.");
			} else {
				player.sendMessage(ChatColor.RED + "AutoRefill is disabled.");
			}
		
		//Turn Refill on or off
		} else if(args.length == 1) {
			
			if(args[0].equalsIgnoreCase("on")) {
						
				//Check to see if player has permission
				if(!player.hasPermission("autorefill.on")) {
					player.sendMessage(ChatColor.RED + "You don't have permission.");
					return true;
				}
				
				//Turn Refill on
				if(p.isEnabled()){
					player.sendMessage(ChatColor.YELLOW + "AutoRefill is already enabled.");
				} else {
					p.setEnabled(true);
					player.sendMessage(ChatColor.GREEN + "AutoRefill is enabled.");
				}
				
				
			} else if(args[0].equalsIgnoreCase("off")) {
						
				//Check to see if player has permission
				if(!player.hasPermission("autorefill.off")) {
					player.sendMessage(ChatColor.RED + "You don't have permission.");
					return true;
				}
				
				//Turn Refill off
				if(!p.isEnabled()) {
					player.sendMessage(ChatColor.YELLOW + "AutoRefill is already disabled.");
				} else {
					p.setEnabled(false);
					player.sendMessage(ChatColor.RED + "AutoRefill is now disabled.");
				}
			
			//Invalid Command
			} else {
				player.sendMessage("Command is not understood.");
			}
					
		//Turn Refill on or off for specified player	
		} else if(args.length == 2){
					
			if(args[1].equalsIgnoreCase("on")) {
						
				//Check to see if player has permission
				if(!player.hasPermission("autorefill.player.on")) {
					player.sendMessage(ChatColor.RED + "You don't have permission.");
					return true;
				}
				
				//Get player
				Player player2 = plugin.getServer().getPlayer(args[0]);
						
				if(player2 != null){
					
					if(player2.hasPermission("autorefill.use")) {
					
						//Add player to refillList if not already
						if(!plugin.refillList.containsKey(player2)) {
							plugin.refillList.put(player2, new RefillPlayer(player2, plugin.enabled));
						}
								
						//Pull player out of refillList
						RefillPlayer p2 = plugin.refillList.get(player2);
								
						if(p2.isEnabled()){
							player.sendMessage(ChatColor.YELLOW + "AutoRefill is already enabled for " + args[0] + ".");
									
						} else {
							p2.setEnabled(true);
							player.sendMessage(ChatColor.GREEN + "AutoRefill has been enabled for " + args[0] + ".");
							player2.sendMessage(ChatColor.GREEN + "AutoRefill is enabled thanks to " + player.getDisplayName() + ".");
						}
					} else {
						player.sendMessage("Player " + args[0] + " does not have permission to use AutoRefill.");
					}
							
				} else {
					player.sendMessage("Player " + args[0] + " not found.");
				}
						
			} else if(args[1].equalsIgnoreCase("off")) {
						
				//Check to see if player has permission
				if(!p.getPlayer().hasPermission("autorefill.player.off")) {
					player.sendMessage(ChatColor.RED + "You don't have permission.");
					return true;
				}
				
				//Get player
				Player player2 = plugin.getServer().getPlayer(args[0]);
						
				if(player2 != null){
					
					if(player2.hasPermission("autorefill.use")) {
							
						//Add player to refillList if not already
						if(!plugin.refillList.containsKey(player2)) {
							plugin.refillList.put(player2, new RefillPlayer(player2, plugin.enabled));
						}
								
						//Pull player out of refillList
						RefillPlayer p2 = plugin.refillList.get(player2);
								
						if(!p2.isEnabled()){
							player.sendMessage(ChatColor.YELLOW + "AutoRefill is already disabled for " + args[0] + ".");
									
						} else {
							p2.setEnabled(false);
							player.sendMessage(ChatColor.GREEN + "AutoRefill has been disabled for " + args[0] + ".");
							player2.sendMessage(ChatColor.GREEN + "AutoRefill has been disabled by " + player.getDisplayName() + ".");
						}
					} else {
						player.sendMessage("Player " + args[0] + " does not have permission to use AutoRefill.");
					}
					
				} else {
					player.sendMessage("Player " + args[0] + " not found.");
				}
			}
		}
		return true;
	}
}