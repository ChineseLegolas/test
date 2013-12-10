package net.dmulloy2.ultimatearena.creation;

import net.dmulloy2.ultimatearena.UltimateArena;
import net.dmulloy2.ultimatearena.types.FieldType;

import org.bukkit.entity.Player;

/**
 * @author dmulloy2
 */

public class InfectCreator extends PvPCreator
{
	public InfectCreator(Player player, String name, UltimateArena plugin)
	{
		super(player, name, plugin);
	}

	@Override
	public FieldType getType()
	{
		return FieldType.INFECT;
	}
}