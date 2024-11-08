package org.mukyang.studyplugin.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.mukyang.studyplugin.ItemManager;

public class openmenu {
    private final Inventory inv;

    private void initItemSetting() {
        inv.setItem(0, ItemManager.dia);
    }

    public openmenu() {
        this.inv = Bukkit.createInventory(null, 9, "TestMenu");
        initItemSetting();
    }

    public void open(Player player) {
        player.openInventory(inv);
    }

}
