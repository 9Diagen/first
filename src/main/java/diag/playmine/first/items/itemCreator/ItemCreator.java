package diag.playmine.first.items.itemCreator;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ItemCreator {


    public static ItemStack updateItemMeta
            (
                    Consumer<ItemMeta> metaUpdater,
                    @NotNull ItemStack item
            ) {

        var meta = item.getItemMeta();
        if (metaUpdater != null) {
            metaUpdater.accept(meta);
        }
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack updateItemMeta
            (
                    Consumer<ItemMeta> metaUpdater,
                    @NotNull ItemStack item,
                    @NotNull NamespacedKey key
            ) {

        return updateItemMeta(meta -> {
            meta.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, true);
            if (metaUpdater != null) {
                metaUpdater.accept(meta);
            }
        }, item);
    }

}
