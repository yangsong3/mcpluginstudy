package org.mukyang.studyplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class Studyplugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("플러그인 시작");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("플러그인 종료");
    }
}
