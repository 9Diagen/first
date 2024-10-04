package diag.playmine.first.events.itemInteract;

import diag.playmine.first.fearSword.FearEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class ItemInteractEvent implements Listener {

    private final Interactable interactable;

    public ItemInteractEvent(@NotNull Interactable interactable) {
        this.interactable = interactable;
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!interactable.getSuccess(e)) return;

        interactable.execute(player);
    }

}
