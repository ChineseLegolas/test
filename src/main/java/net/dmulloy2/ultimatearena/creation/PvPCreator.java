package net.dmulloy2.ultimatearena.creation;

import net.dmulloy2.ultimatearena.UltimateArena;
import net.dmulloy2.ultimatearena.handlers.WorldEditHandler;
import net.dmulloy2.ultimatearena.types.ArenaLocation;
import net.dmulloy2.ultimatearena.types.FieldType;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.selections.Selection;

/**
 * @author dmulloy2
 */

public class PvPCreator extends ArenaCreator
{
	public PvPCreator(Player player, String name, UltimateArena plugin)
	{
		super(player, name, plugin);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPoint(String[] args)
	{
		switch (stepNumber)
		{
			case 1: // Arena
			{
				WorldEditHandler worldEdit = plugin.getWorldEditHandler();
				if (worldEdit.useWorldEdit())
				{
					if (! worldEdit.hasSelection(player))
					{
						sendMessage("&cYou must have a WorldEdit selection to do this!");
						return;
					}

					Selection sel = worldEdit.getSelection(player);
					if (! worldEdit.isCuboidSelection(sel))
					{
						sendMessage("&cYou must have a cuboid selection to do this!");
						return;
					}

					Location arena1 = sel.getMaximumPoint();
					Location arena2 = sel.getMinimumPoint();

					// Perform some checks
					if (arena1 == null || arena2 == null)
					{
						sendMessage("&cPlease make sure you have two valid points in your selection!");
						return;
					}

					if (plugin.isInArena(arena1) || plugin.isInArena(arena2))
					{
						sendMessage("&cThese points overlap an existing arena!");
						return;
					}

					target.setArena1(new ArenaLocation(arena1));
					target.setArena2(new ArenaLocation(arena2));

					sendMessage("&3Arena points set!");
					break; // Step completed
				}
				else
				{
					if (target.getArena1() == null)
					{
						target.setArena1(new ArenaLocation(player));
						sendMessage("&3First point set.");
						sendMessage("&3Please set the &e2nd &3point.");
						return;
					}
					else
					{
						target.setArena2(new ArenaLocation(player));
						sendMessage("&3Second point set!");
						break; // Step completed
					}
				}
			}
			case 2: // Lobby
			{
				WorldEditHandler worldEdit = plugin.getWorldEditHandler();
				if (worldEdit.useWorldEdit())
				{
					if (! worldEdit.hasSelection(player))
					{
						sendMessage("&cYou must have a WorldEdit selection to do this!");
						return;
					}

					Selection sel = worldEdit.getSelection(player);
					if (! worldEdit.isCuboidSelection(sel))
					{
						sendMessage("&cYou must have a cuboid selection to do this!");
						return;
					}

					Location lobby1 = sel.getMaximumPoint();
					Location lobby2 = sel.getMinimumPoint();

					// Perform some checks
					if (lobby1 == null || lobby2 == null)
					{
						sendMessage("&cPlease make sure you have two valid points in your selection!");
						return;
					}

					if (plugin.isInArena(lobby1) || plugin.isInArena(lobby2))
					{
						sendMessage("&cThese points overlap an existing arena!");
						return;
					}

					if (lobby1.getWorld().getUID() != target.getArena1().getWorld().getUID())
					{
						sendMessage("&cYou must make your lobby in the same world as your arena!");
						return;
					}

					target.setLobby1(new ArenaLocation(lobby1));
					target.setLobby2(new ArenaLocation(lobby2));

					sendMessage("&3Lobby points set!");
					break; // Step completed
				}
				else
				{
					ArenaLocation loc = new ArenaLocation(player);
					if (plugin.isInArena(loc))
					{
						sendMessage("&cThis point overlaps an existing arena!");
						return;
					}

					if (loc.getWorld().getUID() != target.getArena1().getWorld().getUID())
					{
						sendMessage("&cYou must make your lobby in the same world as your arena!");
						return;
					}

					if (target.getLobby1() == null)
					{
						target.setLobby1(new ArenaLocation(player));
						sendMessage("&3First point set.");
						sendMessage("&3Please set the &e2nd &3point.");
						return;
					}
					else
					{
						target.setArena2(new ArenaLocation(player));
						sendMessage("&3Second point set!");
						break; // Step completed
					}
				}
			}
			case 3:
			{
				target.setLobbyREDspawn(new ArenaLocation(player));
				sendMessage("&eRed &3team lobby spawn set.");
				break; // Step completed
			}
			case 4:
			{
				target.setLobbyBLUspawn(new ArenaLocation(player));
				sendMessage("&eBlue &3team lobby spawn set.");
				break; // Step completed
			}
			case 5:
			{
				target.setTeam1spawn(new ArenaLocation(player));
				sendMessage("&eRed &3team arena spawn set.");
				break; // Step completed
			}
			case 6:
			{
				target.setTeam2spawn(new ArenaLocation(player));
				sendMessage("&eBlue &3team arena spawn set.");
				break; // Step completed
			}
		}

		stepUp(); // Next step
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stepInfo()
	{
		switch (stepNumber)
		{
			case 1:
				sendMessage("&3Please set &e2 &3points for the arena");
				break;
			case 2:
				sendMessage("&3Please set &e2 &3points for the lobby.");
				break;
			case 3:
				sendMessage("&3Please set the &eRed &3team lobby spawn.");
				break;
			case 4:
				sendMessage("&3Please set the &eBlue &3team lobby spawn.");
				break;
			case 5:
				sendMessage("&3Please set the &eRed &3team arena spawn.");
				break;
			case 6:
				sendMessage("&3Please set the &eBlue &3team arena spawn.");
				break;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FieldType getType()
	{
		return FieldType.PVP;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSteps()
	{
		this.steps = 6;
	}
}