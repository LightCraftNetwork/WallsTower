package com.arrayprolc.introanimation;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.arrayprolc.MainPackage.Main;
import com.arrayprolc.bountifulupdate.BUtils;


public class IntroAnimation {
	
	public static Location teamRedSpawn = new Location(Bukkit.getWorld("world2"), -23, 48, -81);
	public static Location teamBlueSpawn  = new Location(Bukkit.getWorld("world2"), 27, 48, 91);
	
	static boolean active = true;
	public static void playIntroAnimation(){
		if(!active) return;
		for(final Player p : Bukkit.getOnlinePlayers()){
			p.setGameMode(GameMode.SPECTATOR);
			Location starting = new Location(p.getWorld(), 5, 95, 151, -181, 35);
			final Location end = new Location(p.getWorld(), 5, 95, -111, -181, 35);
			BUtils.sendTitle(p, "§9§lWalls Tower", "§7§lMap: Fantasy", 5, 555, 0);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Bukkit.getServer().getPluginManager().getPlugin("WallsTower")), new Runnable(){
				public void run(){
					BUtils.sendTitle(p, "§cBeta Release", "§7§lPlease report any bugs!", 0, 5, 5);
				}
			}, 20*5);
			p.teleport(starting);
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Bukkit.getServer().getPluginManager().getPlugin("WallsTower"), new Runnable(){
				public void run() {
					if(active){
					Location n = p.getLocation();
					n.setZ(n.getZ() - 1);
					p.setVelocity(new Vector(0, 0, 0));
					p.teleport(n);
					if(end.distance(n) < 3){

						active = false;
						for(Player p2 : Bukkit.getOnlinePlayers()){
							Location r = new Location(p2.getWorld(), -23, 48, -81);
							Location b  = new Location(p2.getWorld(), 27, 48, 91);
							//p2.setVelocity(new Vector(0, 0.3, 0));
							boolean blue = false;
							blue = Main.blue.players.contains(p2.getUniqueId());
							if(blue){
								p2.teleport(b);
								p.setVelocity(new Vector(0, 0, 0));
							}else{
								p2.teleport(r);
								p.setVelocity(new Vector(0, 0, 0));
							}
							p2.setGameMode(GameMode.SURVIVAL);
						}
						
					}
					}
					
					
				}
				
			}, 0, 1);
		}
	}
	
}
