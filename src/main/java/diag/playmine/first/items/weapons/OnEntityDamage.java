package diag.playmine.first.items.weapons;

import diag.playmine.first.items.itemGetter.ItemGetter;
import diag.playmine.first.menu.keys.Keys;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnEntityDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player player)) return;

        var item = player.getInventory().getItemInMainHand();
        var weapon = ItemGetter.getValueFromKeySet(item, Keys.weapons);

        if (weapon == null) return;

        double randomValue = Math.random() * 100;

        if (randomValue <= weapon.getCritChance()) {
            double newDamage = e.getDamage() * weapon.getCritMultiplier();
            e.setDamage(newDamage);
        }

        weapon.onDamage(e);
    }

}
