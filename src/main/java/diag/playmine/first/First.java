package diag.playmine.first;

import diag.playmine.first.command.OpenShopCommand;
import diag.playmine.first.configuration.Configuration;
import diag.playmine.first.economy.EconomyManager;
import diag.playmine.first.economy.command.GetBalance;
import diag.playmine.first.economy.command.SetBalance;
import diag.playmine.first.events.joinEvent.OnPlayerJoin;
import diag.playmine.first.events.armorChangeEvent.OnPlayerArmorChange;
import diag.playmine.first.items.armor.OnEntityDamageReceive;
import diag.playmine.first.items.armor.boots.BootsOfSpeed;
import diag.playmine.first.items.weapons.OnEntityDamage;
import diag.playmine.first.menu.keys.Keys;
import diag.playmine.first.shop.ShopMenu;
import diag.playmine.first.items.weapons.FearSword;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class First extends JavaPlugin {

    @Getter
    public static First instance;

    @Getter
    private EconomyManager economyManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        Configuration economyConfig = new Configuration("economy.yml", this);
        economyManager = new EconomyManager(economyConfig);

        registerCommands();
        registerEvents();
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("shop")).setExecutor(new OpenShopCommand());
        Objects.requireNonNull(getCommand("getbalance")).setExecutor(new GetBalance());
        Objects.requireNonNull(getCommand("setbalance")).setExecutor(new SetBalance());
    }

    private void registerEvents() {
        new FearSword(Keys.FEAR_SWORD_KEY);
        new ShopMenu();
        new BootsOfSpeed(Keys.BOOTS_OF_SPEED_KEY);

        var pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new OnPlayerJoin(), this);
        pluginManager.registerEvents(new OnPlayerArmorChange(), this);
        pluginManager.registerEvents(new OnEntityDamage(), this);
        pluginManager.registerEvents(new OnEntityDamageReceive(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
