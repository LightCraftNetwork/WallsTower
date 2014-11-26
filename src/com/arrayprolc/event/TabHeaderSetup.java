package com.arrayprolc.event;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.arrayprolc.Main.Main;
import com.arrayprolc.bountifulupdate.BUtils;

public class TabHeaderSetup implements Listener {

	public Main plugin;

	public TabHeaderSetup(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		BUtils.sendHeaderAndFooter(e.getPlayer(), "", "§7Walls Tower");
	}

}
