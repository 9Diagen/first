package diag.playmine.first.events.joinEvent;

import diag.playmine.first.First;
import diag.playmine.first.economy.EconomyManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;

public class OnPlayerJoin implements Listener {

    private final EconomyManager economyManager;

    public OnPlayerJoin() {
        this.economyManager = First.instance.getEconomyManager();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        economyManager.loadBalance(event.getPlayer().getName());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        economyManager.unloadBalance(event.getPlayer().getName());
    }

}
