package mykyta.Anchor.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEvent implements Listener {
    @EventHandler
	public void onPlace(BlockPlaceEvent event) {
		/*if (event.getBlock().getTypeId() == Util.instance.getConfig().getInt("block")) {
			try {
				if (event.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', Util.instance.getConfig().getString("name")))) {
					if (util.hasAnchor(event.getBlock().getLocation().getChunk())) {
						event.getPlayer().sendTitle(
							ChatColor.translateAlternateColorCodes('&', "&6Already Anchored!"), 
							ChatColor.translateAlternateColorCodes('&', "&7The chunk already has an anchor.")
						);
						event.setCancelled(true);
						return;
					}
					
					event.getPlayer().sendTitle(
						ChatColor.translateAlternateColorCodes('&', "&aPlaced an Anchor"), 
						ChatColor.translateAlternateColorCodes('&', "&7The chunk won't unload when you leave.")
					);
					util.addAnchor(event.getBlock());
					((Player) event.getPlayer()).playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
					event.getPlayer().getWorld().playEffect(event.getBlock().getLocation(), Effect.MOBSPAWNER_FLAMES, 19); 
				}
			}
			catch (Exception e) {
				return;
			}
		}*/
	}
}