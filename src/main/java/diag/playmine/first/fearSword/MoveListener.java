package diag.playmine.first.fearSword;

import diag.playmine.first.First;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

public class MoveListener implements Listener {

    private final String entityName;

    public MoveListener(@NotNull String entityName) {
        this.entityName = entityName;
        First.instance.getServer().getPluginManager().registerEvents(this, First.instance);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (!e.getPlayer().getName().equals(entityName)) return;
        // Проверяем, если у сущности есть метаданные, связанные с плагином


    }

}
