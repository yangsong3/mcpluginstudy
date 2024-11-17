package org.mukyang.studyplugin;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.mukyang.studyplugin.EnhanceSystem.EnhanceSystem;
import org.mukyang.studyplugin.PlayerJobSystem.PlayerJobEvent;
import org.mukyang.studyplugin.ScoreBoard.PlayerScoreboardManager;
import org.mukyang.studyplugin.ScoreBoard.ScoreboardUpdater;
import org.mukyang.studyplugin.command.JobCommand;

public final class Studyplugin extends JavaPlugin {

    private ScoreboardUpdater updater;

    @Override
    public void onEnable() {
        NamespacedKey damageKey = new NamespacedKey(this, "enhance_damage");
        EnhanceSystem enhanceSystem = new EnhanceSystem(damageKey);

        // Plugin startup logic
        getLogger().info("플러그인 시작");

        getServer().getPluginManager().registerEvents(new PlayerJobEvent(), this);
        getServer().getConsoleSender().sendMessage("ScoreBoard Enabled");

        updater = new ScoreboardUpdater(this);

        getCommand("setjob").setExecutor((new JobCommand()));
        getCommand("checkstatus").setExecutor(new JobCommand());

        getCommand("scoreboard").setExecutor(new PlayerScoreboardManager(updater));

        getCommand("enhance").setExecutor(enhanceSystem);
        getServer().getPluginManager().registerEvents(enhanceSystem, this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("플러그인 종료");

        getServer().getConsoleSender().sendMessage("ScoreBoard Disnabled");
    }
}
