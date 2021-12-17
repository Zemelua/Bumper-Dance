package io.github.zemelua.bumper_dance.listener;

import io.github.zemelua.bumper_dance.config.PluginConfig;
import io.github.zemelua.bumper_dance.util.PluginUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.Collection;
import java.util.Random;

public class SneakGrowListener implements Listener {
	public static final SneakGrowListener INSTANCE = new SneakGrowListener();

	private final Random random = new Random();

	private SneakGrowListener() {
	}

	@EventHandler
	public void onPlayerToggleSneak(final PlayerToggleSneakEvent event) {
		Player player = event.getPlayer();
		boolean enable = PluginConfig.INSTANCE.isSneakGrowEnable();
		double range = PluginConfig.INSTANCE.getSneakGrowRange();

		if (event.isSneaking() && enable) {
			this.growAroundPlants(player.getWorld(), player.getLocation(), range);
		}
	}

	private void growAroundPlants(World world, Location center, double range) {
		Collection<Location> growPoses = PluginUtils.getSpherePoses(center, range);

		growPoses.forEach(pos -> {
			double distance = Math.abs(center.distance(pos));
			Block block = world.getBlockAt(pos);
			Material type = block.getType();

			if (this.random.nextDouble(range) >= distance) {
				Location above = pos.clone().add(0, 1, 0);
				Location bottom = pos.clone().add(0, -1, 0);

				if (PluginUtils.growsBySneak(block)) {
					block.applyBoneMeal(BlockFace.EAST);
				} else if (type == Material.SUGAR_CANE && world.getBlockAt(above).isEmpty()) {
					world.setBlockData(above, block.getBlockData().clone());
				} else if (type == Material.CAVE_VINES && world.getBlockAt(bottom).isEmpty()) {
					world.setBlockData(bottom, block.getBlockData().clone());
					world.setType(pos, Material.CAVE_VINES_PLANT);
				}
			}
		});
	}
}
