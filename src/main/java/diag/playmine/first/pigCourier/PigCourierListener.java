package diag.playmine.first.pigCourier;

import diag.playmine.first.fearSword.FearEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PigCourierListener implements Listener {

    // Храним время последнего клика
    private long lastClickTime = 0;

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        // Проверяем, что в руке у игрока золотой топор
        if (player.getInventory().getItemInMainHand().getType() != Material.GOLDEN_AXE) return;

        // Проверяем, что это правый клик в воздухе или по блоку
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {

            // Проверяем, прошло ли достаточно времени с последнего вызова
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastClickTime < 200) {
                // Игнорируем дублирующий вызов
                return;
            }

            // Обновляем время последнего клика
            lastClickTime = currentTime;

            // Ваш код для действия при правом клике
            new PigCourier(player);
        }
    }

}
