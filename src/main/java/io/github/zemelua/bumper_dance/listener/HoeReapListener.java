package io.github.zemelua.bumper_dance.listener;

import io.github.zemelua.bumper_dance.config.PluginConfig;
import io.github.zemelua.bumper_dance.util.PluginUtils;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class HoeReapListener implements Listener {
	public static final HoeReapListener INSTANCE = new HoeReapListener();

	private HoeReapListener() {
	}

	@EventHandler
	public void onPlayerInteract(final PlayerInteractEvent event) {
		boolean enable = PluginConfig.INSTANCE.isHoeReapEnable();

		if (event.getAction() == Action.RIGHT_CLICK_BLOCK && enable) {
			this.tryReap(event.getPlayer(), event.getClickedBlock());
		}
	}

	private void tryReap(Player player, Block block) {
		ItemStack item = player.getInventory().getItemInMainHand();

		if (PluginUtils.isHoe(item)) {
			BlockData data = block.getBlockData();

			if (data instanceof Ageable dataAgeable) {
				int maximumAge = dataAgeable.getMaximumAge();

				if (maximumAge > 0 && dataAgeable.getAge() == maximumAge) {
					Location location = block.getLocation();

					for (ItemStack itemStack : block.getDrops(item, player)) {
						block.getWorld().dropItem(location, itemStack);
					}

					dataAgeable.setAge(0);
					block.getWorld().getBlockAt(location).setBlockData(data);
				}
			}
		}
	}
}
