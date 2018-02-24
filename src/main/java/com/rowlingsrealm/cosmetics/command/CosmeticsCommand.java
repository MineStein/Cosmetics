package com.rowlingsrealm.cosmetics.command;

import com.rowlingsrealm.cosmetics.CosmeticsPlugin;
import com.rowlingsrealm.cosmetics.menu.CosmeticsMenu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Copyright Tyler Grissom 2018
 */
@AllArgsConstructor
public class CosmeticsCommand extends CommandBase {

    @Getter private CosmeticsPlugin plugin;

    @Override
    void execute(CommandSender sender, Command command, String[] args) {
        if (sender instanceof Player) {
            new CosmeticsMenu(getPlugin()).open(((Player) sender));
        }
    }
}
