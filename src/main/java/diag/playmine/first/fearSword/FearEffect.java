package diag.playmine.first.fearSword;

import diag.playmine.first.First;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class FearEffect {

    public static void applyFear(LivingEntity target, Location casterLocation) {
        var plugin = First.instance;

        Vector fleeDirection = target.getLocation().toVector().subtract(casterLocation.toVector()).normalize();

        // Увеличиваем скорость
        fleeDirection.multiply(0.4); // Множитель скорости, можно варьировать

        // Поворачиваем сущность в противоположную сторону
        Location targetLocation = target.getLocation();
        targetLocation.setYaw(targetLocation.getYaw() + 180); // Поворот на 180 градусов
        target.teleport(targetLocation);

        // Добавляем эффект страха - слепота (ограниченный обзор) на 3 секунды
        target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1));
        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 255));

        var listener = new MoveListener(target.getName());

        // Повторяем установку скорости каждые 5 тиков в течение 3 секунд
        new BukkitRunnable() {
            int counter = 0;

            @Override
            public void run() {
                if (counter >= 60) { // Прекращаем через 3 секунды (60 тиков)
                    this.cancel();
                    PlayerMoveEvent.getHandlerList().unregister(listener);
                    return;
                }

                // Устанавливаем направление движения

                target.setVelocity(fleeDirection);

                counter += 5; // Увеличиваем счётчик на 5 тиков (0.25 секунды)
            }
        }.runTaskTimer(plugin, 0L, 5L); // Запускаем каждые 5 тиков
    }

}
