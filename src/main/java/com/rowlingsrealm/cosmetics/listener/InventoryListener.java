package com.rowlingsrealm.cosmetics.listener;

import com.google.common.base.Preconditions;
import com.rowlingsrealm.cosmetics.CosmeticsPlugin;
import com.rowlingsrealm.cosmetics.item.ItemUtility;
import com.rowlingsrealm.cosmetics.menu.WandSkinsMenu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Copyright Tyler Grissom 2018
 */
@AllArgsConstructor
public class InventoryListener implements Listener {

    @Getter private CosmeticsPlugin plugin;

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

            Preconditions.checkNotNull(i);

            if (ItemUtility.isSimilar(i, Material.BLAZE_POWDER, "Wand Skins")) {
                new WandSkinsMenu(plugin).open(p);
            } else if (ItemUtility.isSimilar(i, Material.CHAINMAIL_HELMET, "Hats")) {
                p.performCommand("hats");
            } else if (ItemUtility.isSimilar(i, Material.MOB_SPAWNER, "Pets")) {
                p.performCommand("pets");
            }

            return;
        }
    }
}
