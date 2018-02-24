package com.rowlingsrealm.cosmetics;

import com.rowlingsrealm.cosmetics.command.CommandBase;
import com.rowlingsrealm.cosmetics.command.CosmeticsCommand;
import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Copyright Tyler Grissom 2018
 */
public class CosmeticsPlugin extends JavaPlugin {

    @Getter
    private CosmeticsPlugin plugin;

    private void registerCommand(String cmd, CommandBase base) {
        getCommand(cmd).setExecutor(base);
    }

    @Override
    public void onEnable() {
        plugin = this;

        getConfig().options().copyDefaults(true);
        saveConfig();

        {
            registerCommand("cosmetics", new CosmeticsCommand(plugin));
        }
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }
}
