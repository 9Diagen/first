package diag.playmine.first.items.armor.boots;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import diag.playmine.first.items.Boostable;
import diag.playmine.first.items.armor.Armor;
import diag.playmine.first.menu.keys.Keys;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BootsOfSpeed extends Armor implements Boostable {

    public BootsOfSpeed(@NotNull NamespacedKey key) {
        super(key);
        setMissChance(20);
        Keys.boosts.add(this);
    }

    @Override
    public void applyBoost(PlayerArmorChangeEvent e) {
        setSpeed(e.getPlayer(), 0.15);
    }

    @Override
    public void clearBoost(PlayerArmorChangeEvent e) {
        setSpeed(e.getPlayer(), 0.1);
    }

    private void setSpeed(@NotNull Player player, double value) {
        var speedAttribute = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        if (speedAttribute != null) {
            speedAttribute.setBaseValue(value);
        }
    }

}
