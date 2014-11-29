package com.arrayprolc.gametools;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.arrayprolc.bungeehook.BungeeHooks;


public class GameTools {

	public static void sendAllToLobbyAndReset(){
		for(Player p : Bukkit.getOnlinePlayers()){
		BungeeHooks.sendPlayerToServer("lobby", p);
		}
		for(World w : Bukkit.getWorlds()) for(Entity e : w.getEntities()) e.remove();
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getServer().getPluginManager().getPlugin("HubPlugin"), new Runnable(){
			public void run(){
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "save-off");
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "stop");
			}
		}, 5);

	}
	
}
