package diag.playmine.first.economy.command;

import diag.playmine.first.economy.EconomyManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

public class GetBalance implements CommandExecutor {

    private final EconomyManager manager;

    public GetBalance(
            @NotNull EconomyManager manager
    ) {
        this.manager = manager;
    }

    private double getBalance(@NotNull String player) {
        return manager.getBalance(player);
    }

    private String getOwnBalanceMessage(@NotNull String playerName) {
        return MessageFormat.format("Ваш баланс {0}", getBalance(playerName));
    }

    private String getPlayerBalanceMessage(@NotNull String playerName) {
        return MessageFormat.format("Баланс игрока {0}: {1}", playerName, getBalance(playerName));
    }

    private String getPlayerNotFoundMessage(@NotNull String playerName) {
        return MessageFormat.format("Игрок с ником {0} не найден", playerName);
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {
        if (!(sender instanceof Player player))
            return true;

        int length = args.length;

        if (length == 0) player.sendMessage(getOwnBalanceMessage(player.getName()));

        else if (length == 1) {
            String receiverName = args[0];
            Player receiver = Bukkit.getPlayer(receiverName);

            if (receiver == null) {
                player.sendMessage(getPlayerNotFoundMessage(receiverName));
                return true;
            }

            player.sendMessage(getPlayerBalanceMessage(receiverName));
        }

        else player.sendMessage("Команда введена неверно");

        return true;
    }
}
