package org.mukyang.studyplugin.ScoreBoard;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.mukyang.studyplugin.PlayerJobSystem.PlayerData;
import org.mukyang.studyplugin.PlayerJobSystem.PlayerJob;

public class ScoreboardUpdater {

    private final Plugin plugin;

    public ScoreboardUpdater(Plugin plugin) {
        this.plugin = plugin;
    }

    public void startUpdateingScoreboard(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline()) {
                    this.cancel();
                    return;
                }

                updateScoreboard(player);
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    private void updateScoreboard(Player player){
        org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
        if (manager == null){
            return;
        }

        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("PlayerInfo", "dummy", "Player Stats");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score nameScore = objective.getScore("Name: "+ player.getName());
        nameScore.setScore(4);

        PlayerData data = PlayerData.get(player);

        Score jobScore = objective.getScore("Job: " + data.getJob().name());
        jobScore.setScore(3);

        Location loc = player.getLocation();
        String location = String.format("X: %.1f, Y: %.1f, Z: %.1f", loc.getX(), loc.getY(), loc.getZ());
        Score locationScore = objective.getScore("Loc: " + location);
        locationScore.setScore(2);

        String biome = player.getLocation().getBlock().getBiome().toString().toLowerCase().replace("_", " ");
        Score biomeScore = objective.getScore("Biome: " + biome);
        biomeScore.setScore(1);

        player.setScoreboard(scoreboard);
    }
}
