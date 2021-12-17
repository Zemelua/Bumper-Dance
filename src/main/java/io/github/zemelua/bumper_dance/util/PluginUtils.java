package io.github.zemelua.bumper_dance.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public final class PluginUtils {
	private static final Set<Material> SNEAK_GROW_PLANTS = Set.of(
			Material.WHEAT,
			Material.CARROTS,
			Material.POTATOES,
			Material.BEETROOTS,
			Material.OAK_SAPLING,
			Material.SPRUCE_SAPLING,
			Material.BIRCH_SAPLING,
			Material.JUNGLE_SAPLING,
			Material.ACACIA_SAPLING,
			Material.DARK_OAK_SAPLING,
			Material.PUMPKIN_STEM,
			Material.MELON_STEM,
			Material.SWEET_BERRY_BUSH
	);
	private static final Set<Material> DROP_EXP_PLANTS = Set.of(
			Material.WHEAT,
			Material.CARROTS,
			Material.POTATOES,
			Material.BEETROOTS
	);
	private static final Set<Material> HOES = Set.of(
			Material.WOODEN_HOE,
			Material.STONE_HOE,
			Material.IRON_HOE,
			Material.GOLD_ORE,
			Material.DIAMOND_HOE,
			Material.NETHERITE_HOE
	);

	private PluginUtils() {
	}

	public static Collection<Location> getSpherePoses(Location center, double range) {
		double x = center.getX();
		double y = center.getY();
		double z = center.getZ();

		List<Location> growPoses = new ArrayList<>();

		for (int i = (int) Math.ceil(x - range); i <= (int) Math.floor(x + range); i++) {
			double s = Math.sqrt(Math.pow(range, 2) - Math.pow(x - (double) i, 2));

			for (int j = (int) Math.ceil(y - s); j <= (int) Math.floor(y + s); j++) {
				double t = Math.sqrt(Math.pow(s, 2) - Math.pow(y - (double) j, 2));

				for (int k = (int) Math.ceil(z - t); k <= (int) Math.floor(z + t); k++) {
					growPoses.add(center.clone().set(i, j, k));
				}
			}
		}

		return growPoses;
	}

	public static boolean growsBySneak(Block block) {
		Material type = block.getType();

		return PluginUtils.DROP_EXP_PLANTS.contains(type);
	}

	public static boolean dropsExp(Block block) {
		Material type = block.getType();

		if (PluginUtils.DROP_EXP_PLANTS.contains(type)) {
			BlockData data = block.getBlockData();

			if (data instanceof Ageable dataAgeable) {
				return dataAgeable.getAge() >= dataAgeable.getMaximumAge();
			}

			return true;
		}

		return false;
	}

	public static boolean isHoe(ItemStack item) {
		Material type = item.getType();

		return PluginUtils.HOES.contains(type);
	}
}
