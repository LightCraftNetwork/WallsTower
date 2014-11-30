package com.arrayprolc.team;

import java.util.HashMap;
import java.util.UUID;

import com.arrayprolc.MainPackage.Main;

public class TeamUtils
{

	public static HashMap<UUID, String> teams = new HashMap<UUID, String>();

	public static int getMaxPerTeam()
	{
		return 2;
		/*
		 * int total = Bukkit.getOnlinePlayers().length; if(total < 2){ return
		 * 1; } return Math.round(total/Main.teams.length);
		 */
	}

	public static Team getFirstOpenTeam(Team[] ts)
	{
		int smallestAmount = 10000;
		Team smallest = ts[0];
		for (Team team : ts)
		{
			if (team.getTeamSize() < getMaxPerTeam())
			{
				if (team.getTeamSize() < smallestAmount)
				{
					smallestAmount = team.getTeamSize();
					smallest = team;
				}
			}
		}
		return smallest;
	}

	public static Team getTeam(String s)
	{
		switch (s.toLowerCase())
		{
		case "red":
			return Main.red;
		case "blue":
			return Main.blue;
		default:
			return Main.red;
		}
	}

	public static String getString(Team s)
	{
		if (s.equals(Main.red))
			return "red";
		if (s.equals(Main.blue))
			return "blue";
		return "red";
	}

	public static String getColor(Team s)
	{
		if (s.equals(Main.red))
			return "�c";
		if (s.equals(Main.blue))
			return "�9";
		return "red";
	}
}
