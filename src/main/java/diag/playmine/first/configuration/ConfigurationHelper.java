package diag.playmine.first.configuration;

import diag.playmine.first.First;
import diag.playmine.first.economy.EconomyManager;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class ConfigurationHelper {

    private static final FileConfiguration config = First.instance.getConfig();

    private static final EconomyManager economyManager = First.getInstance().getEconomyManager();

    private static TextComponent getConfigString(@NotNull String path, Object... args) {
        return Configuration.getFormattedConfigString(path, config, args);
    }

    public static TextComponent getPlayerNotFoundMessage(@NotNull String playerName) {
        return getConfigString("messages.errors.player-not-found", playerName);
    }

    public static TextComponent getWrongInputMessage() {
        return getConfigString("messages.errors.wrong-input");
    }

    public static TextComponent getOwnBalanceMessage(@NotNull String playerName) {
        return getConfigString("messages.balance.get-own-balance", economyManager.getBalance(playerName));
    }

    public static TextComponent getPlayerBalanceMessage(@NotNull String playerName) {
        return getConfigString("messages.balance.get-player-balance", playerName, economyManager.getBalance(playerName));
    }

    public static TextComponent setPlayerBalanceMessageSender(@NotNull String playerName, double amount) {
        return getConfigString("messages.balance.set-player-balance-sender", playerName, amount);
    }

    public static TextComponent setPlayerBalanceMessageReceiver(@NotNull String playerName, double amount) {
        return getConfigString("messages.balance.set-player-balance-sender", playerName, amount);
    }

}
