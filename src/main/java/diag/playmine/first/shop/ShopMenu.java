package diag.playmine.first.shop;

import diag.playmine.first.events.itemInteract.Interactable;
import diag.playmine.first.items.itemCreator.ItemCreator;
import diag.playmine.first.menu.MenuBase;
import diag.playmine.first.menu.keys.Keys;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ShopMenu extends MenuBase {

    public ShopMenu() {
        super(54, Component.text("Магазин"), getButtons());
    }

    private static ArrayList<ShopItemButton> getButtons() {
        var breadLore = getLoreList("Курвасанчик с телотинкой", "На заварварку", "Цена: 100");

        var buttons = new ArrayList<ShopItemButton>();
        buttons.add(new ShopItemButton(0, new ItemStack(Material.BREAD), "Курвасан", breadLore, 100));

        var bootsOfSpeedLore = getLoreList("Тапки спида", "Цена: 500");

        var bootsOfSpeed = ItemCreator.updateItemMeta(meta -> {
            meta.setUnbreakable(true);
            meta.addEnchant(Enchantment.RIPTIDE, 1, true);
        }, new ItemStack(Material.LEATHER_BOOTS), Keys.BOOTS_OF_SPEED_KEY);

        buttons.add(new ShopItemButton(1, bootsOfSpeed, "Boots of Speed", bootsOfSpeedLore, 500));

        var fearSwordLore = getLoreList("Цена: 1000");
        var fearSword = ItemCreator.updateItemMeta(null, new ItemStack(Material.DIAMOND_SWORD), Keys.FEAR_SWORD_KEY);

        buttons.add(new ShopItemButton(2, fearSword, "FearSword", fearSwordLore, 1000));

        return buttons;
    }

    private static ArrayList<Component> getLoreList(String... lines) {
        var lore = new ArrayList<Component>();

        for (var line: lines) {
            lore.add(Component.text(line));
        }

        return lore;
    }

    @Override
    public void onRightClick(PlayerInteractEvent e) {
        open(e.getPlayer());
    }

    @Override
    public NamespacedKey getKey() {
        return Keys.MENU_KEY;
    }
}
