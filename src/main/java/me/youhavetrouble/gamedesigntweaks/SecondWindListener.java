package me.youhavetrouble.gamedesigntweaks;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashSet;

public class SecondWindListener implements Listener {

    HashSet<EntityDamageEvent.DamageCause> dots = new HashSet<>();

    public SecondWindListener() {
        dots.add(EntityDamageEvent.DamageCause.POISON);
        dots.add(EntityDamageEvent.DamageCause.WITHER);
        dots.add(EntityDamageEvent.DamageCause.DROWNING);
        dots.add(EntityDamageEvent.DamageCause.SUFFOCATION);
        dots.add(EntityDamageEvent.DamageCause.VOID);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.isDead()) return;
        EntityDamageEvent.DamageCause cause = event.getCause();
        if (dots.contains(cause)) return;
        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        double currentHealth = player.getHealth();
        double hpPercentage = (currentHealth * 100.0) / maxHealth;
        double damage = event.getFinalDamage();
        if (hpPercentage >= 75) {
            // Prevent oneshots from 75%+ hp
            if (currentHealth - damage <= 0 && !event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                event.setDamage(0.0000001);
                player.setHealth(1);
            }
            return;
        }
        if (hpPercentage >= 50) {
            event.setDamage(damage * 0.8);
            return;
        }
        if (hpPercentage >= 25) {
            event.setDamage(damage * 0.65);
            return;
        }
        event.setDamage(damage * 0.5);
    }

}
