package diag.playmine.first.economy.command;

import diag.playmine.first.First;
import diag.playmine.first.configuration.ConfigurationHelper;
import diag.playmine.first.economy.EconomyManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

public class SetBalance implements CommandExecutor {

    private final EconomyManager manager = First.getInstance().getEconomyManager();

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {
        if (!(sender instanceof Player player))
            return true;

        String senderName = player.getName();

        int length = args.length;

        if (length == 0 || length > 2) {
            player.sendMessage(ConfigurationHelper.getWrongInputMessage());
            return true;
        }

        double amount;

        try {
            amount = Double.parseDouble(args[length - 1]);
        }
        catch (Exception ex) {
            player.sendMessage(ConfigurationHelper.getWrongInputMessage());
            return true;
        }

        String receiverName = length == 1 ? senderName : args[0];
        manager.setBalance(receiverName, amount);

        if (!receiverName.equals(senderName)) {
            var receiver = Bukkit.getPlayer(receiverName);
            if (receiver == null) {
                player.sendMessage(ConfigurationHelper.getPlayerNotFoundMessage(receiverName));
                return true;
            }
            player.sendMessage(ConfigurationHelper.setPlayerBalanceMessageSender(receiverName, amount));
            player.sendMessage(ConfigurationHelper.setPlayerBalanceMessageReceiver(senderName, amount));
        }
        else {
            player.sendMessage(ConfigurationHelper.getOwnBalanceMessage(player.getName()));
        }

        return true;
    }
}