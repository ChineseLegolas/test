package com.orange451.UltimateArena.commands;

import org.bukkit.ChatColor;

import com.orange451.UltimateArena.UltimateArena;
import com.orange451.UltimateArena.Arenas.Arena;
import com.orange451.UltimateArena.permissions.PermissionType;

public class PCommandPause extends UltimateArenaCommand
{
	public PCommandPause(UltimateArena plugin) 
	{
		super(plugin);
		this.name = "pause";
		this.requiredArgs.add("arena");
		this.mode = "admin";
		this.description = "pause the start timer on an arena";
		this.permission = PermissionType.CMD_PAUSE.permission;
	}
	
	@Override
	public void perform() 
	{
		String name = args[0];
		Arena arena = plugin.getArena(name);
		if (arena == null)
		{
			player.sendMessage(ChatColor.GOLD + "No arena with that name...");
			return;
		}		
		
		arena.pauseStartTimer = !arena.pauseStartTimer;
		player.sendMessage(ChatColor.GOLD + "Start timer for arena " + ChatColor.AQUA + arena.name + ChatColor.GOLD + "is now " + (arena.pauseStartTimer ? "paused" : "unpaused"));
	}	
}