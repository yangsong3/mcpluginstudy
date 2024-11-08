package org.mukyang.studyplugin.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.mukyang.studyplugin.ItemManager;

public class BreakEvent implements Listener {


    @EventHandler
    public void breakDirt(BlockBreakEvent e) {
        Material brBlock = e.getBlock().getBlockData().getMaterial();
        if (brBlock == Material.DIRT || brBlock == Material.GRASS_BLOCK) {
            Player p = e.getPlayer();
            p.sendMessage("test success");
            if (Math.floor(Math.random() * 10) < 5) {
                p.getInventory().addItem(ItemManager.enchantdia);
            } else {
                p.getInventory().addItem(ItemManager.dia);
            }
        }
    }
}
