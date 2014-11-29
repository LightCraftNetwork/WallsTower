package com.arrayprolc.event;


import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.arrayprolc.MainPackage.Main;
import com.arrayprolc.item.ItemTools;
import com.arrayprolc.team.Team;
import com.arrayprolc.team.TeamUtils;

public class PlayerJoin {

	public static void joinServer(Player p){
		p.getInventory().clear();
		p.setHealth(20.0D);
		p.setFoodLevel(20);
		p.setFireTicks(0);
		p.setVelocity(new Vector(0, 0, 0));
		p.teleport(p.getWorld().getSpawnLocation());
		p.setGameMode(GameMode.ADVENTURE);
		Team t = TeamUtils.getFirstOpenTeam(new Team[] { Main.red, Main.blue} );
		t.addMember(p);
		p.getInventory().setItem(0, ItemTools.setName(new ItemStack(Material.WOOL, 1, (byte)11), "§9§lBLUE TEAM §7- §dCLICK TO JOIN"));
		p.getInventory().setItem(8, ItemTools.setName(new ItemStack(Material.WOOL, 1, (byte)14), "§c§lRED TEAM §7- §dCLICK TO JOIN"));
	}
	
}
	