package org.mukyang.studyplugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomMenuGui implements Listener {
    public static void openCustomMenu(org.bukkit.entity.Player player) {
        Inventory gui = Bukkit.createInventory(null, 9, "MENU");

        ItemStack button = new ItemStack(Material.DIAMOND);
        ItemMeta meta = button.getItemMeta();
        meta.setDisplayName("DIA");
        button.setItemMeta(meta);

        gui.setItem(4, button);

        player.openInventory(gui);
    }
}
