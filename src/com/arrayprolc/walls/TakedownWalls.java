package com.arrayprolc.walls;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.arrayprolc.cuboid.Cuboid;

public class TakedownWalls {
	Location entireWorld1 = new Location(Bukkit.getWorld("world2"), 55, 117, -127);
	Location entireWorld2 = new Location(Bukkit.getWorld("world2"), 55, 117, -12);
	Cuboid entireWorld = new Cuboid(entireWorld1, entireWorld2);
	
	Location red1 = new Location(Bukkit.getWorld("world2"), -51, 80, -4);
	Location red2 = new Location(Bukkit.getWorld("world2"), 51, 1, -4);
	Cuboid red = new Cuboid(red1, red2);
}
