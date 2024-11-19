package org.mukyang.studyplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.testpl.testpl.gui.CustomMenuGui;

public class menuCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (label.equalsIgnoreCase("MENU") && sender instanceof Player) {
            Player player = (Player) sender;
            CustomMenuGui.openCustomMenu(player);
            return true;
        }

        return false;
    }
}
