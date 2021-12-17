package io.github.zemelua.bumper_dance;

import io.github.zemelua.bumper_dance.config.PluginConfig;
import io.github.zemelua.bumper_dance.listener.HarvestExpListener;
import io.github.zemelua.bumper_dance.listener.HoeReapListener;
import io.github.zemelua.bumper_dance.listener.SneakGrowListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BumperDance extends JavaPlugin {
	@Override
	public void onEnable() {
		PluginManager pluginManager = this.getServer().getPluginManager();

		pluginManager.registerEvents(SneakGrowListener.INSTANCE, this);
		pluginManager.registerEvents(HarvestExpListener.INSTANCE, this);
		pluginManager.registerEvents(HoeReapListener.INSTANCE, this);

		this.saveDefaultConfig();
		PluginConfig.INSTANCE.setConfig(this.getConfig());
	}
}
