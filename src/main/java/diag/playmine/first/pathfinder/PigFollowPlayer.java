package diag.playmine.first.pathfinder;

import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.animal.EntityPig;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;

//public class PigFollowPlayer extends PathfinderGoal {
//    private final EntityPig pig;
//    private Player targetPlayer;
//    private final double speed;
//
//    public PigFollowPlayer(EntityPig pig, double speed) {
//        this.pig = pig;
//        this.speed = speed;
//    }
//
//    @Override
//    public boolean a() {
//        // Найти ближайшего игрока
//        var bukkitPig = pig.getBukkitEntity();
//        var players = bukkitPig.getWorld().getNearbyPlayers(bukkitPig.getLocation(), 10.0);
//        var player = players.stream().findFirst();
//        player.ifPresent(value -> targetPlayer = value);
//        return targetPlayer != null;
//    }
//
//    @Override
//    public void e() {
//        // Переместить свинью к игроку
//        if (targetPlayer == null) return;
//
//        // Получаем координаты игрока
//        double targetX = targetPlayer.getLocation().getX();
//        double targetY = targetPlayer.getLocation().getY();
//        double targetZ = targetPlayer.getLocation().getZ();
//
//        // Устанавливаем движение свиньи к игроку
//        pig.L().a(targetX, targetY, targetZ, speed); // Используем метод L() для навигации
//
//    }
//
//    @Override
//    public boolean b() {
//        // Продолжать следовать за игроком, пока он близко
//        return targetPlayer != null && !targetPlayer.isDead();
//    }
//
//}