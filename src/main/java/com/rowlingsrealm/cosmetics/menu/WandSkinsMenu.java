package com.rowlingsrealm.cosmetics.menu;

import com.rowlingsrealm.cosmetics.CosmeticsPlugin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Copyright Tyler Grissom 2018
 */
@AllArgsConstructor
public class WandSkinsMenu {

    public class WandSkin {

        @Getter @Setter private Material material;
        @Getter @Setter private int data;
        @Getter @Setter private String name;
        @Getter @Setter private List<String> lore;
    }

    @Getter private CosmeticsPlugin plugin;

    private int resize(Collection<?> collection) {
        int resize = 9;
        int size = collection.size();

        if (size > 9) resize = 18;
        if (size > 18) resize = 27;
        if (size > 27) resize = 36;
        if (size > 36) resize = 45;
        if (size > 45) resize = 54;

        return resize;
    }

    private List<WandSkin> getWandSkins() {
        List<WandSkin> wandSkins = new ArrayList<>();
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection section = config.getConfigurationSection("wands");

        for (String key :
                section.getKeys(false)) {
            section = section.getConfigurationSection(key);

            WandSkin skin = new WandSkin();

            skin.setMaterial(Material.valueOf(section.getString("material").toUpperCase()));
            skin.setData(section.getInt("data"));
            skin.setLore(section.getStringList("lore"));
            skin.setName(section.getString("name"));

            wandSkins.add(skin);
        }

        return wandSkins;
    }

    private Inventory getInventory() {
        List<WandSkin> skins = getWandSkins();
        Inventory inventory = Bukkit.createInventory(null, resize(skins), "Wand Skins");

        for (WandSkin skin :
                skins) {
            ItemStack item = new ItemStack(skin.getMaterial(), 1, (short) skin.getData()); {
                ItemMeta m = item.getItemMeta();

                m.setDisplayName(ChatColor.translateAlternateColorCodes('&', skin.getName()));

                List<String> newLore = new ArrayList<>();

                for (String str :
                        skin.getLore()) {
                    newLore.add(ChatColor.translateAlternateColorCodes('&', str));
                }

                m.setLore(newLore);
                m.setUnbreakable(true);
                m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);

                item.setItemMeta(m);
            }

            inventory.addItem(item);
        }

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
