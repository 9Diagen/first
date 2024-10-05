package diag.playmine.first.events.armorChangeEvent;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import diag.playmine.first.items.Boostable;
import diag.playmine.first.items.itemGetter.ItemGetter;
import diag.playmine.first.menu.keys.Keys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class OnPlayerArmorChange implements Listener {

    @EventHandler
    public void onPlayerArmorChange(PlayerArmorChangeEvent e) {
        var newItem = e.getNewItem();
        var oldItem = e.getOldItem();

        toggleBoost(newItem, true, e);
        toggleBoost(oldItem, false, e);
    }

    private void toggleBoost(
            @NotNull ItemStack item,
            boolean apply,
            @NotNull PlayerArmorChangeEvent e
    ) {
        Boostable boost = ItemGetter.getValueFromKeySet(item, Keys.boosts);

        if (boost == null) return;

        if (apply) {
            boost.applyBoost(e);
        } else {
            boost.clearBoost(e);
        }
    }
}