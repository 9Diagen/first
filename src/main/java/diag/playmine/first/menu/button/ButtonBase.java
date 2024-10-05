package diag.playmine.first.menu.button;

import diag.playmine.first.items.itemCreator.ItemCreator;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
public abstract class ButtonBase {

    private final int slot;

    private final ItemStack item;

    public abstract void onClick(InventoryClickEvent e);

    public ButtonBase(
            int slot,
            @NotNull ItemStack item,
            @NotNull String displayName
    ) {
        this.slot = slot;
        this.item = item;
        setDisplayName(displayName);
    }

    public ButtonBase(
            int slot,
            @NotNull ItemStack item,
            @NotNull String displayName,
            @NotNull List<Component> lore
    ) {
        this(slot, item, displayName);
        setLore(lore);
    }

    private void setDisplayName(@NotNull String displayName) {
        ItemCreator.updateItemMeta(meta -> meta.displayName(Component.text(displayName)), item);
    }

    private void setLore(@NotNull List<Component> lore) {
        ItemCreator.updateItemMeta(meta -> meta.lore(lore), item);
    }

}
