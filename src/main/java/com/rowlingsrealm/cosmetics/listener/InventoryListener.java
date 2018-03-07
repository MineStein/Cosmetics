package com.rowlingsrealm.cosmetics.listener;

import com.elmakers.mine.bukkit.magic.MagicPlugin;
import com.rowlingsrealm.cosmetics.CosmeticsPlugin;
import com.rowlingsrealm.cosmetics.item.ItemUtility;
import com.rowlingsrealm.cosmetics.menu.WandSkinsMenu;
import com.rowlingsrealm.cosmetics.sound.QuickSound;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.server.v1_12_R1.PacketPlayOutSetSlot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright Tyler Grissom 2018
 */
@AllArgsConstructor
public class InventoryListener implements Listener {

    @Getter private CosmeticsPlugin plugin;

    private String c(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    private List<WandSkinsMenu.WandSkin> getWandSkins() {
        List<WandSkinsMenu.WandSkin> wandSkins = new ArrayList<>();
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection section = config.getConfigurationSection("wands");

        for (String key :
                section.getKeys(false)) {
            section = section.getConfigurationSection(key);

            WandSkinsMenu.WandSkin skin = new WandSkinsMenu.WandSkin();

            skin.setMaterial(Material.valueOf(section.getString("material").toUpperCase()));
            skin.setData(section.getInt("data"));
            skin.setLore(section.getStringList("lore"));
            skin.setName(section.getString("name"));
            skin.setPermission(new Permission(section.getString("permission")));

            wandSkins.add(skin);
        }

        return wandSkins;
    }

    @EventHandler
    public void onInventoryOpen(final InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();

        ItemStack itemStack = new ItemStack(Material.BLAZE_POWDER); {
            ItemMeta meta = itemStack.getItemMeta();

            meta.setDisplayName("§6§lYour Cosmetics");

            meta.setLore(Arrays.asList("", "§eClick §7to view"));

            itemStack.setItemMeta(meta);
        }

        for (int i = 1; i < 5; i++) {
            PacketPlayOutSetSlot packet = new PacketPlayOutSetSlot(0, i, CraftItemStack.asNMSCopy(itemStack));

            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        Player p = ((Player) event.getWhoClicked());
        ItemStack i = event.getCurrentItem();
        Inventory inv = event.getClickedInventory();

        if (event.getSlotType().equals(InventoryType.SlotType.CRAFTING) && event.getSlot() >= 1 && event.getSlot() <= 4) {
            if (p.getTargetBlock(null, 4).getType().equals(Material.WORKBENCH)) return;

            p.performCommand("cosmetics");

            return;
        }

        if (inv.getName().equals("Cosmetics")) {
            event.setCancelled(true);

            if (i == null) return;

            if (ItemUtility.isSimilar(i, Material.BLAZE_POWDER, "Wand Skins")) {
                new WandSkinsMenu(plugin).open(p);
            } else if (ItemUtility.isSimilar(i, Material.CHAINMAIL_HELMET, "Hats")) {
                p.performCommand("hats");
            } else if (ItemUtility.isSimilar(i, Material.MOB_SPAWNER, "Pets")) {
                p.performCommand("pets");
            }
        } else if (inv.getName().equals("Wand Skins")) {
            event.setCancelled(true);

            if (i == null) return;

            if (!MagicPlugin.getAPI().isWand(p.getItemInHand())) {
                p.sendMessage(c("&4&lX &cYou must be holding a wand!"));

                return;
            }

            List<WandSkinsMenu.WandSkin> wandSkins = getWandSkins();

            for (WandSkinsMenu.WandSkin skin :
                    wandSkins) {
                if (ChatColor.stripColor(c(skin.getName())).equals(ChatColor.stripColor(i.getItemMeta().getDisplayName()))) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wandp " + p.getName() + " configure icon " + skin.getMaterial().toString() + ":" + String.valueOf(skin.getData()));

                    QuickSound.click(p);

                    p.sendMessage(c("&8(&dCosmetics&8) &dYour &5" + ChatColor.stripColor(c(skin.getName())) + " &dhas been equipped."));
                }
            }
        }
    }
}
