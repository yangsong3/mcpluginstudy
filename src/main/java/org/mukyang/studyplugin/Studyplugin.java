package org.mukyang.studyplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.mukyang.studyplugin.commands.menu;
import org.mukyang.studyplugin.event.BreakEvent;
import org.mukyang.studyplugin.event.invClickEvent;

public final class Studyplugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("플러그인 시작");
        getServer().getPluginManager().registerEvents(new BreakEvent(), this);
        getServer().getPluginManager().registerEvents(new invClickEvent(), this);

        getServer().getPluginCommand("menu").setExecutor(new menu());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("플러그인 종료");
    }
}
