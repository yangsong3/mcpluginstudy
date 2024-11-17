package org.mukyang.studyplugin.ScoreBoard;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class PlayerScoreboardManager implements CommandExecutor {

    private final ScoreboardUpdater updater;

    public PlayerScoreboardManager(ScoreboardUpdater updater){
        this.updater = updater;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return false;
        }

        Player player = (Player) sender;
        updater.startUpdateingScoreboard(player);
        player.sendMessage("Scoreboard updates started");
        return true;
    }

}
