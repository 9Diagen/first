package diag.playmine.first.items.armor;

import diag.playmine.first.items.KeyItem;
import diag.playmine.first.menu.keys.Keys;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public abstract class Armor implements Listener, KeyItem {

    private final NamespacedKey key;

    @Getter
    @Setter
    private double missChance = 0;

    public Armor(@NotNull NamespacedKey key) {
        this.key = key;
        Keys.armors.add(this);
    }

    @Override
    public NamespacedKey getKey() {
        return key;
    }

}
