package org.mukyang.studyplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mukyang.studyplugin.inventory.openmenu;

public class menu implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            openmenu inv = new openmenu();
            inv.open(player);
        }
        return false;
    }
}
