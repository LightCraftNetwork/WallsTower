package com.arrayprolc.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.arrayprolc.MainPackage.Main;
import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;
import com.arrayprolc.team.TeamUtils;

public class ClickWool implements Listener
{

	Main plugin;

	public ClickWool(Main instance)
	{
		plugin = instance;
	}

	@EventHandler
	public void interact(PlayerInteractEvent e)
	{
		if (!Main.inLobby)
			return;
		if (!e.getAction().toString().contains("CLICK"))
			return;
		if (!e.getPlayer().getItemInHand().hasItemMeta())
			return;
		if (!e.getPlayer().getItemInHand().getItemMeta().hasDisplayName())
			return;
		e.setCancelled(true);
		if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("BLUE"))
		{
			// IntroAnimation.playIntroAnimation();
			int max = TeamUtils.getMaxPerTeam();
			if (Main.blue.getTeamSize() >= max)
			{
				e.getPlayer().sendMessage(StringManager.getPrefix(MessageType.ERROR) + "That team is already full! (" + max + "/" + max + ")");
				return;
			}
			Main.blue.addMember(e.getPlayer());
			e.setCancelled(true);
			return;
		} else if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("RED"))
		{
			int max = TeamUtils.getMaxPerTeam();
			if (Main.red.getTeamSize() >= max)
			{
				e.getPlayer().sendMessage(StringManager.getPrefix(MessageType.ERROR) + "That team is already full! (" + max + "/" + max + ")");
				return;
			}
			Main.red.addMember(e.getPlayer());
			e.setCancelled(true);
			return;
		} else
		{
			e.getPlayer().sendMessage(StringManager.getPrefix(MessageType.ERROR) + "Whatever you just clicked broke something.");
			return;
		}
	}
}
