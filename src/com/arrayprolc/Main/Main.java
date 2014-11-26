package com.arrayprolc.Main;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static boolean acceptingPlayers = true;
	
	public void onEnable(){
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "save-off");
		acceptingPlayers = true;
		
	}
	
	public void onDisable(){
		//test
	}
	
	@EventHandler
	public void join(PlayerJoinEvent e){
		if(!acceptingPlayers){
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
				public void run(){
					e.getPlayer().kickPlayer("§cThat game is full! Try another server!");
				}
			}, 2);
		}
	}

}
