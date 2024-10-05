package diag.playmine.first.items.weapons;

import diag.playmine.first.First;
import diag.playmine.first.events.itemInteract.Interactable;
import diag.playmine.first.items.itemGetter.ItemGetter;
import diag.playmine.first.menu.keys.Keys;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public abstract class Weapon implements Listener, Interactable {

    private final NamespacedKey key;

    @Setter
    @Getter
    private double critChance = 0;

    @Setter
    @Getter
    private double critMultiplier = 1;

    public Weapon(@NotNull NamespacedKey key) {
        this.key = key;
        Keys.weapons.add(this);
        First.instance.getServer().getPluginManager().registerEvents(this, First.instance);
    }

    public void onDamage(EntityDamageByEntityEvent e) {}

    @Override
    public void onRightClick(PlayerInteractEvent e) {}

    @Override
    public NamespacedKey getKey() {
        return key;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player player)) return;
        if (ItemGetter.notContainsKey(player.getInventory().getItemInMainHand(), key)) return;

        double randomValue = Math.random() * 100;

        if (randomValue <= critChance) {
            double newDamage = e.getDamage() * critMultiplier;
            e.setDamage(newDamage);
        }

        onDamage(e);
    }
}
