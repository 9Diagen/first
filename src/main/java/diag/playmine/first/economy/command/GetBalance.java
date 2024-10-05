package diag.playmine.first.economy.command;

import diag.playmine.first.First;
import diag.playmine.first.configuration.Configuration;
import diag.playmine.first.configuration.ConfigurationHelper;
import diag.playmine.first.economy.EconomyManager;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetBalance implements CommandExecutor {

    private final FileConfiguration config = First.getInstance().getConfig();

    private TextComponent getPlayerNotFoundMessage(@NotNull String playerName) {
        return ConfigurationHelper.getPlayerNotFoundMessage(playerName);
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

        if (length == 0) player.sendMessage(ConfigurationHelper.getOwnBalanceMessage(player.getName()));

        else if (length == 1) {
            String receiverName = args[0];
            Player receiver = Bukkit.getPlayer(receiverName);

            if (receiver == null) {
                player.sendMessage(getPlayerNotFoundMessage(receiverName));
                return true;
            }

            player.sendMessage(ConfigurationHelper.getPlayerBalanceMessage(receiverName));
        }

        else player.sendMessage(ConfigurationHelper.getWrongInputMessage());

        return true;
    }
}
