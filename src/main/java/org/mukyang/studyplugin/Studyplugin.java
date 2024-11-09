package org.mukyang.studyplugin;

import PlayerJobSystem.PlayerJobEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mukyang.studyplugin.command.JobCommand;

public final class Studyplugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("플러그인 시작");

        getServer().getPluginManager().registerEvents(new PlayerJobEvent(), this);

        getCommand("setjob").setExecutor((new JobCommand()));
        getCommand("checkstatus").setExecutor(new JobCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("플러그인 종료");
    }
}
