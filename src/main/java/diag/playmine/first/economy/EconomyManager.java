package diag.playmine.first.economy;

import diag.playmine.first.First;
import diag.playmine.first.configuration.Configuration;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

public class EconomyManager {

    private static final long SAVE_INTERVAL = 30;

    private final Map<String, Double> player2Balance = new Object2ObjectOpenHashMap<>();

    private final Configuration configuration;

    private final JavaPlugin plugin;

    public EconomyManager(Configuration economyData) {
        configuration = economyData;
        plugin = First.instance;
        initialize();
    }

    public double getBalance(@NotNull String player) {
        return player2Balance.getOrDefault(player, 0.0);
    }

    public void setBalance(@NotNull String player, double amount) {
        player2Balance.put(player, amount);
    }

    public void addBalance(@NotNull String player, double amount) {
        removeBalance(player, -amount);
    }

    public void removeBalance(@NotNull String player, double amount) {
        setBalance(player, Math.max(getBalance(player) - amount, 0));
    }

    public boolean hasBalance(@NotNull String player, double amount) {
        return getBalance(player) >= amount;
    }

    public boolean hasPlayer(@NotNull String player) {
        return configuration.yamlConfig.getString("players." + player) != null;
    }

    public void loadBalance(@NotNull String player) throws IOException {
        if (!hasPlayer(player)) {
            configuration.set("players." + player, 0);
            configuration.yamlConfig.save(configuration.configurationFile);
        }
        player2Balance.put(player, configuration.yamlConfig.getDouble("players." + player, 0));
    }

    public void unloadBalance(@NotNull String player) {
        setPlayerBalance(player);
        player2Balance.remove(player);
    }

    public void saveAll() {
        var players = new Object2ObjectOpenHashMap<>(player2Balance);
        players.forEach((player, balance) -> configuration.set("players." + player, balance));
        configuration.saveConfig();
    }

    public void setPlayerBalance(@NotNull String player) {
        configuration.set("players." + player, player2Balance.get(player));
    }

    public void initialize() {
        plugin.getServer()
                .getScheduler()
                .runTaskTimer(plugin, this::saveAll, SAVE_INTERVAL * 20, SAVE_INTERVAL * 20);
    }

}
