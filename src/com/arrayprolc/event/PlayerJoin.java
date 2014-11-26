package com.arrayprolc.event;


import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class PlayerJoin {

	public static void joinServer(Player p){
		p.getInventory().clear();
		p.setHealth(20.0D);
		p.setFoodLevel(20);
		p.setFireTicks(0);
		p.setVelocity(new Vector(0, 0, 0));
		p.teleport(p.getWorld().getSpawnLocation());
		p.setGameMode(GameMode.ADVENTURE);
	}
	
}
