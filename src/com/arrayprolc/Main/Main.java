package com.arrayprolc.Main;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import net.minecraft.util.org.apache.commons.io.FileUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import code.husky.mysql.MySQL;

import com.arrayprolc.event.ArrayEventsSetup;
import com.arrayprolc.event.PlayerJoin;
import com.arrayprolc.rank.RankManager;
import com.arrayprolc.team.Team;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.entity.EntityManager;
import de.slikey.effectlib.listener.ItemListener;

public class Main extends JavaPlugin implements Listener {

	public static boolean acceptingPlayers = false;
	public static boolean ready = false;
	public static boolean inLobby = true;
	public static MySQL MySQL;
	public static java.sql.Connection c = null;
	private EntityManager entityManager;
	public static Team red, blue, green, yellow;
	public static Team[] teams = { red, blue, green, yellow };
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
		saveFile();
		this.reloadConfig();
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "save-off");
		ready = true;
		acceptingPlayers = true;
		RankManager.init(this);
		ArrayEventsSetup.setupEvents(this);
		Bukkit.getPluginManager().registerEvents(this, this);
		red = new Team("Red", (byte)14, ChatColor.RED);
		blue = new Team("Blue", (byte)11, ChatColor.BLUE);
		green = new Team("Green", (byte)13, ChatColor.GREEN);
		yellow = new Team("Yellow", (byte)4, ChatColor.YELLOW);
	}


	@EventHandler
	public void join(PlayerJoinEvent e){
		if(!ready){
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
				public void run(){
					e.getPlayer().kickPlayer("�cThat game is still rebooting!");
				}
			}, 2);
			return;
		}
		if(!acceptingPlayers){
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
				public void run(){
					e.getPlayer().kickPlayer("�cThat game is full! Try another server!");
				}
			}, 2);
			return;
		}
		//Using this method instead of putting content into the event so if we need to call it later it's no problem.
		PlayerJoin.joinServer(e.getPlayer());
		
		
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
