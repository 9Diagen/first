package diag.playmine.first.menu.button;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public abstract class ButtonBase {

    private final int slot;

    private final ItemStack item;

    public int getSlot() {
        return slot;
    }

    public ItemStack getItem() {
        return item;
    }

    public abstract void onClick(InventoryClickEvent e);

    public ButtonBase(
            int slot,
            @NotNull Material material,
            @NotNull String displayName
    ) {
        this.slot = slot;
        this.item = new ItemStack(material);
        setDisplayName(displayName);
    }

    public ButtonBase(
            int slot,
            @NotNull Material material,
            @NotNull String displayName,
            @NotNull List<Component> lore
    ) {
        this(slot, material, displayName);
        setLore(lore);
    }

    private void updateItemMeta(Consumer<ItemMeta> metaUpdater) {
        var meta = item.getItemMeta();
        metaUpdater.accept(meta);
        item.setItemMeta(meta);
    }

    private void setDisplayName(@NotNull String displayName) {
        updateItemMeta(meta -> meta.displayName(Component.text(displayName)));
    }

    private void setLore(@NotNull List<Component> lore) {
        updateItemMeta(meta -> meta.lore(lore));
    }

}
