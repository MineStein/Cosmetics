package com.rowlingsrealm.cosmetics.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Copyright Tyler Grissom 2018
 */
public class ItemUtility {

    public static boolean isSimilar(ItemStack comparator, Material type, String name) {
        return (comparator.getType().equals(type) && ChatColor.stripColor(comparator.getItemMeta().getDisplayName()).equalsIgnoreCase(ChatColor.stripColor(name)));
    }
}
