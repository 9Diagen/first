package diag.playmine.first.events.itemInteract;

import diag.playmine.first.items.KeyItem;
import org.bukkit.event.player.PlayerInteractEvent;

public interface Interactable extends KeyItem {

    void onRightClick(PlayerInteractEvent e);

}
