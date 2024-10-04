package diag.playmine.first;

import diag.playmine.first.command.OpenShopCommand;
import diag.playmine.first.configuration.Configuration;
import diag.playmine.first.economy.EconomyManager;
import diag.playmine.first.economy.command.GetBalance;
import diag.playmine.first.economy.command.SetBalance;
import diag.playmine.first.events.itemInteract.Interactable;
import diag.playmine.first.events.itemInteract.ItemInteractEvent;
import diag.playmine.first.events.joinEvent.OnPlayerJoin;
import diag.playmine.first.fearSword.FearEffect;
import diag.playmine.first.menu.MenuBase;
import diag.playmine.first.menu.button.ButtonBase;
import diag.playmine.first.menu.keys.Keys;
import diag.playmine.first.shop.ShopMenu;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class First extends JavaPlugin {

    @Getter
    public static First instance;

    private EconomyManager economyManager;

    public EconomyManager getEconomyManager() {
        return economyManager;
    }

    @Override
    public void onEnable() {
        instance = this;
        Configuration configuration = new Configuration("economy.yml", this);
        economyManager = new EconomyManager(configuration);
        registerCommands();
        registerEvents();
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("shop")).setExecutor(new OpenShopCommand());
        Objects.requireNonNull(getCommand("getbalance")).setExecutor(new GetBalance(economyManager));
        Objects.requireNonNull(getCommand("setbalance")).setExecutor(new SetBalance(economyManager));
    }

    private void registerEvents() {
        var fear = new Interactable() {

            @Override
            public boolean getSuccess(@NotNull PlayerInteractEvent e) {
                if (e.getPlayer().getInventory().getItemInMainHand().getType() != Material.DIAMOND_SWORD) return false;
                return e.getAction().toString().contains("RIGHT_CLICK");
            }

            @Override
            public void execute(@NotNull Player player) {
                FearEffect.applyFear(player, player.getLocation());
            }

        };

        var openMenu = new Interactable() {
            @Override
            public boolean getSuccess(@NotNull PlayerInteractEvent e) {
                if (!e.getAction().toString().contains("RIGHT_CLICK")) return false;

                var meta = e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
                if (meta == null) return false;

                var keys = meta.getPersistentDataContainer().getKeys();

                return keys.stream().anyMatch(key -> key.equals(Keys.MENU_KEY));
            }

            @Override
            public void execute(@NotNull Player player) {
                new ShopMenu().open(player);
            }
        };

        var pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new OnPlayerJoin(), this);
        pluginManager.registerEvents(new ItemInteractEvent(fear), this);
        pluginManager.registerEvents(new ItemInteractEvent(openMenu), this);
//        pluginManager.registerEvents(new PigCourierListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
