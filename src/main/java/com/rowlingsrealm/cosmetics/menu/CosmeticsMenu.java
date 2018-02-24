package com.rowlingsrealm.cosmetics.menu;

import com.rowlingsrealm.cosmetics.CosmeticsPlugin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Copyright Tyler Grissom 2018
 */
@AllArgsConstructor
public class CosmeticsMenu {

    @Getter private CosmeticsPlugin plugin;

    private String c(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    private Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, 27, "Cosmetics");

        // TODO animate these to show different items

        ItemStack wandSkins = new ItemStack(Material.BLAZE_POWDER); {
            ItemMeta m = wandSkins.getItemMeta();

            m.setDisplayName(c("&2Wand Skins"));
            m.setLore(Arrays.asList(
                    "",
                    c("&8Alternative looks for your wand!"),
                    "",
                    c("&6&lClick &7to view available wand skins")
            ));

            wandSkins.setItemMeta(m);
        }

        ItemStack hats = new ItemStack(Material.CHAINMAIL_HELMET); {
            ItemMeta m = hats.getItemMeta();

            m.setDisplayName(c("&2Hats"));
            m.setLore(Arrays.asList(
                    "",
                    c("&8Top hats for your character!"),
                    "",
                    c("&6&lClick &7to view available hats")
            ));

            hats.setItemMeta(m);
        }

        ItemStack pets = new ItemStack(Material.MOB_SPAWNER); {
            ItemMeta m = hats.getItemMeta();

            m.setDisplayName(c("&2Pets"));
            m.setLore(Arrays.asList(
                    "",
                    c("&8Companions for your character!"),
                    "",
                    c("&6&lClick &7to view available pets")
            ));

            pets.setItemMeta(m);
        }

        inventory.setItem(10, wandSkins);
        inventory.setItem(13, wandSkins);
        inventory.setItem(16, wandSkins);

        return inventory;
    }
    
    public void open(Player... players) {
        for (Player player :
                players) {
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1F, 1F);
            player.openInventory(getInventory());
        }
    }
}
