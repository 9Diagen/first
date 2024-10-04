package diag.playmine.first.events.itemInteract;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public interface Interactable {

    boolean getSuccess(@NotNull PlayerInteractEvent e);

    void execute(@NotNull Player player);

}
