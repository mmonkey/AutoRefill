package com.gmail.mmonkey.AutoRefill;

import org.bukkit.entity.Player;

public class RefillPlayer {

	Player player;
	boolean enabled;
	
	//Constructor
	public RefillPlayer(Player player, boolean enabled) {
		this.player = player;
		this.enabled = enabled;
	}
	
	//Getters and Setters
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isEnabled() {
		return this.enabled;
	}
	public Player getPlayer() {
		return this.player;
	}
}
