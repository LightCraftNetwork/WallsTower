package com.arrayprolc.Main;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import net.minecraft.util.org.apache.commons.io.FileUtils;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import code.husky.mysql.MySQL;

import com.arrayprolc.event.ArrayEventsSetup;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.entity.EntityManager;
import de.slikey.effectlib.listener.ItemListener;

public class Main extends JavaPlugin {

	public static boolean acceptingPlayers = false;
	public static boolean ready = false;
	public static MySQL MySQL;
	public static java.sql.Connection c = null;
	private EntityManager entityManager;
	//Will be editable soon
	File otherConfigFile = new File("C:/Users/Justin/Desktop/SpeedBuild Local Server/plugins/HubPlugin/config.yml".replace("/", File.separator));
	File move = new File("plugins/WallsTower/config.yml".replace("/", File.separator));

	public void onEnable(){
		try {
			Files.delete(move.toPath());
			FileUtils.copyFile(otherConfigFile, move);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		this.reloadConfig();
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "save-off");
		ready = true;
		acceptingPlayers = true;
		loadListeners();
		ArrayEventsSetup.setupEvents(this);
	}


	@EventHandler
	public void join(PlayerJoinEvent e){
		if(!ready){
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
				public void run(){
					e.getPlayer().kickPlayer("§cThat game is still rebooting!");
				}
			}, 2);
		}
		if(!acceptingPlayers){
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
				public void run(){
					e.getPlayer().kickPlayer("§cThat game is full! Try another server!");
				}
			}, 2);
		}
	}

	@Override
	public void onDisable() {
		entityManager.dispose();
		EffectManager.disposeAll();
		HandlerList.unregisterAll((Plugin) this);
	}
	private void loadListeners() {
		getServer().getPluginManager().registerEvents(new ItemListener(), this);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public List<EffectManager> getEffectManagers() {
		return EffectManager.getManagers();
	}
	public void saveFile(){
		this.saveConfig();
	}

}
