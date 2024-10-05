package diag.playmine.first.items.armor;

import diag.playmine.first.items.itemGetter.ItemGetter;
import diag.playmine.first.menu.keys.Keys;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnEntityDamageReceive implements Listener {

    @EventHandler
    public void onDamageReceive(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player player)) return;

        double missChance = 0;
        for (var item : player.getInventory().getArmorContents()) {
            if (item == null) continue;
            var armor = ItemGetter.getValueFromKeySet(item, Keys.armors);
            if (armor == null) continue;
            missChance += armor.getMissChance();
        }

        double randomValue = Math.random() * 100;

        if (randomValue <= missChance) {
            e.setCancelled(true);

            player.sendMessage("Промах");
        }

    }

}
