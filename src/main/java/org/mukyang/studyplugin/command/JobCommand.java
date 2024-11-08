package org.mukyang.studyplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;

public class JobCommand implements CommandExecutor {
    @EventHandler
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setjob")) {
            return handleSetJobCommand(sender, args);
        } else if (command.getName().equalsIgnoreCase("checkstatus")) {
            return handleCheckStatusCommand(sender, args);
        }
        return false;
    }

    private boolean handleSetJobCommand(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("사용법: /setjob <플레이어> <직업>");
            return false;
        }

        return true;
    }





    private boolean handleCheckStatusCommand(CommandSender sender, String[] args) {
    return false;
    }

}
