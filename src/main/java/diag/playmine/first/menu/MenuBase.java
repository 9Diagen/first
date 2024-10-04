package diag.playmine.first.menu;

import diag.playmine.first.First;
import diag.playmine.first.menu.button.ButtonBase;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MenuBase implements Listener {

    private final Inventory inventory;

    private List<? extends ButtonBase> buttons;

    public void open(@NotNull Player player) {
        player.openInventory(inventory);
    }

    public MenuBase(int size, @NotNull Component title) {
        inventory = Bukkit.createInventory(null, size, title);
        First.instance.getServer().getPluginManager().registerEvents(this, First.instance);
    }

    public MenuBase(int size, @NotNull Component title, @NotNull List<? extends ButtonBase> buttons) {
        this(size, title);
        this.buttons = buttons;

        for (ButtonBase button: buttons) {
            inventory.setItem(button.getSlot(), button.getItem());
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!e.getInventory().equals(inventory)) return;

        e.setCancelled(true);
        var clickedButton = buttons.stream().filter(b -> b.getSlot() == e.getSlot()).findFirst();
        clickedButton.ifPresent(button -> button.onClick(e));
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (!e.getInventory().equals(inventory)) return;

        InventoryClickEvent.getHandlerList().unregister(this);
        InventoryCloseEvent.getHandlerList().unregister(this);
    }

}
