package com.rowlingsrealm.cosmetics.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Copyright Tyler Grissom 2018
 */
public abstract class CommandBase implements CommandExecutor {

    abstract void execute(CommandSender sender, Command command, String[] args);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        execute(commandSender, command, strings);

        return true;
    }
}
