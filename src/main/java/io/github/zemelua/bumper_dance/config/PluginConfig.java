package io.github.zemelua.bumper_dance.config;

import org.bukkit.configuration.Configuration;

import javax.annotation.Nullable;

public class PluginConfig {
	public static final PluginConfig INSTANCE = new PluginConfig();

	@Nullable private Configuration config;

	public boolean isSneakGrowEnable() {
		if (this.config != null) {
			return this.config.getBoolean("sneakGrow.enable");
		}

		return true;
	}

	public double getSneakGrowRange() {
		if (this.config != null) {
			return this.config.getDouble("sneakGrow.range");
		}

		return 2;
	}

	public boolean isHarvestExpEnable() {
		if (this.config != null) {
			return this.config.getBoolean("harvestExp.enable");
		}

		return true;
	}

	public int getHarvestExpMin() {
		if (this.config != null) {
			return this.config.getInt("harvestExp.value.min");
		}

		return 0;
	}

	public int getHarvestExpMax() {
		if (this.config != null) {
			return this.config.getInt("harvestExp.value.max");
		}

		return 4;
	}

	public boolean isHoeReapEnable() {
		if (this.config != null) {
			return this.config.getBoolean("hoeReap.enable");
		}

		return true;
	}

	public void setConfig(@Nullable Configuration config) {
		this.config = config;
	}
}
