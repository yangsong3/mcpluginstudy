package org.mukyang.studyplugin.event;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class invClickEvent implements Listener {
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null)
            return;
        if (ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("TestMenu")) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            player.sendMessage("inv click was canceled");
        }
    }
}
