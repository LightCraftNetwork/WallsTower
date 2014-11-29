package com.arrayprolc.team;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.arrayprolc.MainPackage.Main;
import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;

public class Team {
	
	String color;
	
	byte woolData;
	
	ChatColor code;
	
	public ArrayList<UUID> players = new ArrayList<UUID>();
	public Team(String color, byte woolData, ChatColor code) {
		this.color = color; this.woolData = woolData; this.code = code;
	}
	
	public void addMember(Player p){
		players.remove(p.getUniqueId());
		for(Team t : Main.teams) try{ t.removeMember(p); }catch(Exception ex){}
		players.add(p.getUniqueId());
		p.setPlayerListName(TeamUtils.getColor(this) + p.getName());
		TeamUtils.teams.remove(p.getUniqueId());
		TeamUtils.teams.put(p.getUniqueId(), TeamUtils.getString(this));
		p.sendMessage(StringManager.getPrefix(MessageType.SUCCESS) + "You are now on the " + TeamUtils.getColor(this) + TeamUtils.getString(this) + "�7 team.");
	}
	
	public void removeMember(Player p){
		players.remove(p.getUniqueId());
		p.setPlayerListName(p.getName());
		TeamUtils.teams.remove(p.getUniqueId());
	}
	
	public Player[] getMembers(){
		return getPlayers().toArray(new Player[getPlayers().size()]);
	}
	
	public ArrayList<Player> getPlayers(){
		ArrayList<Player> ps = new ArrayList<Player>();
		for(UUID u : players.toArray(new UUID[players.size()])){
			ps.add(getPlayer(u));
		}
		return ps;
	}
	
	public ArrayList<UUID> getPlayerUUIDs(){
		return players;
	}
	
	private Player getPlayer(UUID u){
		for(Player p : Bukkit.getOnlinePlayers()){
			if(p.getUniqueId().toString().equalsIgnoreCase(u.toString())){
				return p;
			}
		}
		return null;
	}
	
	public int getTeamSize(){
		return players.size();
	}
	
	
	
}
