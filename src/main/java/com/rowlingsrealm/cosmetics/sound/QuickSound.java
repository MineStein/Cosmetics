package com.rowlingsrealm.cosmetics.sound;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Copyright Tyler Grissom 2018
 */
public class QuickSound {

    public static void play(Player p, Sound sound) {
        p.playSound(p.getLocation(), sound, 1F, 1F);
    }

    public static void levelUp(Player p) {
        play(p, Sound.ENTITY_PLAYER_LEVELUP);
    }

    public static void click(Player p) {
        play(p, Sound.UI_BUTTON_CLICK);
    }
}
