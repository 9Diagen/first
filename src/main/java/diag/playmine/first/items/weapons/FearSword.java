package diag.playmine.first.items.weapons;

import diag.playmine.first.events.itemInteract.Interactable;
import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class FearSword extends Weapon implements Interactable {

    public FearSword(@NotNull NamespacedKey key) {
        super(key);
        setCritChance(15);
        setCritMultiplier(2);
    }

    @Override
    public void onRightClick(PlayerInteractEvent e) {
        var player = e.getPlayer();
//        FearEffect.applyFear(player, player.getLocation());
    }
}
