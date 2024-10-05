package diag.playmine.first.shop;

import diag.playmine.first.First;
import diag.playmine.first.menu.button.ButtonBase;
import net.kyori.adventure.text.Component;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ShopItemButton extends ButtonBase {

    private final double price;

    public ShopItemButton(
            int slot,
            @NotNull ItemStack item,
            @NotNull String displayName,
            List<Component> lore,
            double price
    ) {
        super(slot, item, displayName, lore);
        this.price = price;
    }

    @Override
    public void onClick(InventoryClickEvent e) {
        var economyManager = First.instance.getEconomyManager();
        var player = e.getWhoClicked();

        if (!economyManager.hasBalance(player.getName(), price)) {
            player.sendMessage("У вас недостаточно средств для покупки предмета");
            return;
        }

        var inventory = player.getInventory();
        var item = e.getCurrentItem();
        assert item != null;
        var hashMap = inventory.addItem(item);

        if (!hashMap.isEmpty()) {
            player.sendMessage("Недостаточно места в инвентаре");
            return;
        }

        economyManager.removeBalance(player.getName(), price);
    }

}
