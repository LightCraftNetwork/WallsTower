package com.arrayprolc.bungeehook;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.arrayprolc.MainPackage.Main;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class BungeeHooks {
	static Main plugin;
	public static void init(Main instance){
		Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(instance, "BungeeCord");
//		Bukkit.getServer().getMessenger().registerIncomingPluginChannel(instance, "BungeeCord", this);
		plugin = instance;
	}

	public static void sendPlayerToServer(String name, Player p){
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(name);
		p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
	}
}
