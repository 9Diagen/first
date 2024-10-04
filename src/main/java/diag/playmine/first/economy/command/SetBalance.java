package diag.playmine.first.economy.command;

import diag.playmine.first.economy.EconomyManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

public class SetBalance implements CommandExecutor {

    private final EconomyManager manager;

    public SetBalance(
            @NotNull EconomyManager manager
    ) {
        this.manager = manager;
    }

    private void setBalance(@NotNull String playerName, double amount) {
        manager.setBalance(playerName, amount);
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

        String senderName = player.getName();

        int length = args.length;

        if (length == 0 || length > 2) player.sendMessage("Команда введена неверно");

        double amount;

        try {
            amount = Double.parseDouble(args[length - 1]);
        }
        catch (Exception ex) {
            player.sendMessage("Команда введена неверно");
            return true;
        }

        String receiverName = length == 1 ? senderName : args[0];
        setBalance(receiverName, amount);

        if (!receiverName.equals(senderName)) {
            var receiver = Bukkit.getPlayer(receiverName);
            if (receiver == null) {
                player.sendMessage(MessageFormat.format("Игрок с ником {0} не найден", receiverName));
                return true;
            }
            player.sendMessage(MessageFormat.format("Вы установили баланс игрока {0} {1}", receiverName, amount));
            receiver.sendMessage(MessageFormat.format("Игрок {0} установил вам баланс {1}", senderName, amount));
        }
        else {
            player.sendMessage("Ваш баланс: " + amount);
        }

        return true;
    }
}