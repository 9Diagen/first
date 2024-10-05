package diag.playmine.first.command;

import diag.playmine.first.First;
import diag.playmine.first.menu.MenuBase;
import diag.playmine.first.menu.button.ButtonBase;
import diag.playmine.first.shop.ShopMenu;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OpenShopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("The command is available only for players");
            return true;
        }

        new ShopMenu().open(player);

        return true;
    }

}
