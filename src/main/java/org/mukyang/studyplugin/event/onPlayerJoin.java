package org.mukyang.studyplugin.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerJoin {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();

        String customJoinMessage = "Welcome, " + playerName + " !";
        Bukkit.broadcastMessage(customJoinMessage);
    }

    @EventHandler
    public void onPlayerLeave() {


    }
}
