package diag.playmine.first.items.itemGetter;

import diag.playmine.first.items.KeyItem;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.function.Function;

public class ItemGetter {

    public static <T> T handleKeys(
            @NotNull ItemStack item,
            @NotNull Function<Set<NamespacedKey>, T> func
    ) {
        var meta = item.getItemMeta();
        if (meta == null) return null;
        var keys = meta.getPersistentDataContainer().getKeys();

        return func.apply(keys);
    }

    public static <T extends KeyItem> T getValueFromKeySet(@NotNull ItemStack item, @NotNull Set<T> set) {
        return handleKeys(item, (keys) -> set.stream()
                .filter(i -> keys.contains(i.getKey()))
                .findFirst()
                .orElse(null));
    }

    public static boolean notContainsKey(
            @NotNull ItemStack item,
            @NotNull NamespacedKey key
    ) {
        return Boolean.FALSE.equals(handleKeys(item, (keys) -> keys.contains(key)));
    }

}
