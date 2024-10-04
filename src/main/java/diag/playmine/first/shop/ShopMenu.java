package diag.playmine.first.shop;

import diag.playmine.first.menu.MenuBase;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import java.util.ArrayList;

public class ShopMenu extends MenuBase {

    public ShopMenu() {
        super(54, Component.text("Магазин"), getButtons());
    }

    private static ArrayList<ShopItemButton> getButtons() {
        var lore = new ArrayList<Component>();
        lore.add(Component.text("Курвасанчик с телотинкой"));
        lore.add(Component.text("На заварварку"));
        lore.add(Component.text("Цена: 100"));

        var buttons = new ArrayList<ShopItemButton>();
        buttons.add(new ShopItemButton(0, Material.BREAD, "Курвасан", lore, 100));

        return buttons;
    }

}
