package diag.playmine.first.fearSword;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class FearSwordListener implements Listener {

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().getType() != Material.DIAMOND_SWORD) return;
        if (!e.getAction().toString().contains("RIGHT_CLICK")) return;

        FearEffect.applyFear(player, player.getLocation());

    }

}
