package com.arrayprolc.team;

import org.bukkit.Bukkit;

import com.arrayprolc.Main.Main;

public class TeamUtils {

	public static int getMaxPerTeam(){
		int total = Bukkit.getOnlinePlayers().length;
		if(total < 4){
			return 1;
		}
			return Math.round(total/Main.teams.length);
	}
	
	
	public static Team getFirstOpenTeam(Team[] ts){
		int smallestAmount = 10000;
		Team smallest = ts[0];
		for(Team team : ts){
			if(team.getTeamSize() < getMaxPerTeam()){
			if(team.getTeamSize() < smallestAmount){
				smallestAmount = team.getTeamSize();
				smallest = team;
			}
			}
		}
		return smallest;
	}
}
