package org.mukyang.studyplugin.event;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuGuiClickEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("MENU")) {
            event.setCancelled(true);

            if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.DIAMOND) {
                event.getWhoClicked().sendMessage("DIADIADIA closing");
                event.getWhoClicked().closeInventory();
            }
        }
    }
}
