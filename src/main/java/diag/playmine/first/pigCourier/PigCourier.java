package diag.playmine.first.pigCourier;

import net.minecraft.world.entity.animal.EntityPig;
import org.bukkit.Location;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PigCourier {

    public PigCourier(@NotNull Player player) {
        spawnPigAndDeliverItem(player);
    }

    public void spawnPigAndDeliverItem(@NotNull Player targetPlayer) {
        Location spawnLocation = targetPlayer.getLocation().clone().add(5, 0, 5); // Создаем свинью на небольшом расстоянии от игрока
//        CraftPig pig = spawnLocation.getWorld().spawn(spawnLocation, CraftPig.class); // Спавним свинью

        //Получаем сущность NMS для свиньи
        //EntityPig nmsPig = ((CraftPig)pig).getHandle();
        //nmsPig.bO.a(0, new PigFollowPlayer(nmsPig, 2));
    }
}