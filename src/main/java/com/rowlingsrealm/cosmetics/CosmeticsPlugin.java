package com.rowlingsrealm.cosmetics;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.rowlingsrealm.cosmetics.command.CommandBase;
import com.rowlingsrealm.cosmetics.command.CosmeticsCommand;
import com.rowlingsrealm.cosmetics.listener.InventoryListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Copyright Tyler Grissom 2018
 */
public class CosmeticsPlugin extends JavaPlugin {

    @Getter
    private CosmeticsPlugin plugin;

    @Getter
    private ProtocolManager protocolManager;

    private void registerCommand(String cmd, CommandBase base) {
        getCommand(cmd).setExecutor(base);
    }
    
    private void registerListeners(Listener... listeners) {
        for (Listener l :
                listeners) {
            Bukkit.getPluginManager().registerEvents(l, this);
        }
    }

    @Override
    public void onEnable() {
        plugin = this;
        protocolManager = ProtocolLibrary.getProtocolManager();

        getConfig().options().copyDefaults(true);
        saveConfig();

        {
            registerCommand("cosmetics", new CosmeticsCommand(plugin));
        }

        {
            registerListeners(
                    new InventoryListener(this)
            );
        }
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }
}
