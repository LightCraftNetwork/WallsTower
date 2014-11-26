package com.arrayprolc.team;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;

public class Team {
	
	String color;
	
	byte woolData;
	
	ChatColor code;
	
	ArrayList<UUID> players = new ArrayList<UUID>();
	public Team(String color, byte woolData, ChatColor code) {
		this.color = color; this.woolData = woolData; this.code = code;
	}
	
	
	
}
