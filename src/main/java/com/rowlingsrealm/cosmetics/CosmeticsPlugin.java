package com.rowlingsrealm.cosmetics;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Copyright Tyler Grissom 2018
 */
public class CosmeticsPlugin extends JavaPlugin {

    @Getter
    private CosmeticsPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }
}
