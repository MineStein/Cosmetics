package com.rowlingsrealm.cosmetics.menu;

import com.rowlingsrealm.cosmetics.CosmeticsPlugin;
import com.rowlingsrealm.cosmetics.item.ItemBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

        ItemStack wandSkins = new ItemBuilder()
                .type(Material.BLAZE_POWDER)
                .name(c("&2Wand Skins"))
                .lore(
                        "",
                        c("&8Alternative looks for your wand!"),
                        "",
                        c("&6&lClick &7to view available wand skins")
                )
                .build();

        ItemStack hats = new ItemBuilder()
                .type(Material.CHAINMAIL_HELMET)
                .name(c("&2Hats"))
                .lore(
                        "",
                        c("&8Top hats for your character!"),
                        "",
                        c("&6&lClick &7to view available hats")
                )
                .build();

        {
            ItemMeta m = hats.getItemMeta();

            m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

            hats.setItemMeta(m);
        }

        ItemStack pets = new ItemBuilder()
                .type(Material.MOB_SPAWNER)
                .name(c("&2Pets"))
                .lore(
                        "",
                        c("&8Companions for your character!"),
                        "",
                        c("&6&lClick &7to view available pets")
                )
                .build();

        inventory.setItem(10, wandSkins);
        inventory.setItem(13, hats);
        inventory.setItem(16, pets);

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
