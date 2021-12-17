package io.github.zemelua.bumper_dance.listener;

import io.github.zemelua.bumper_dance.config.PluginConfig;
import io.github.zemelua.bumper_dance.util.PluginUtils;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class HarvestExpListener implements Listener {
	public static final HarvestExpListener INSTANCE = new HarvestExpListener();

	private final Random random = new Random();

	private HarvestExpListener() {
	}

	@EventHandler
	public void onBlockBreak(final BlockBreakEvent event) {
		boolean enable = PluginConfig.INSTANCE.isHarvestExpEnable();
		int min = PluginConfig.INSTANCE.getHarvestExpMin();
		int max = PluginConfig.INSTANCE.getHarvestExpMax();

		if (enable) {
			event.setExpToDrop(this.getDropExp(event.getBlock(), min, max));
		}
	}

	private int getDropExp(Block block, int min, int max) {
		if (PluginUtils.dropsExp(block)) {
			return this.random.nextInt(max - min) + min;
		}

		return 0;
	}
}
