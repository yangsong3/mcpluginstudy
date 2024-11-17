package org.mukyang.studyplugin.EnhanceSystem;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.mukyang.studyplugin.PlayerJobSystem.PlayerJob;

import java.util.Random;

public class EnhanceSystem implements CommandExecutor, Listener {
    private final Random random = new Random();
    private final NamespacedKey damageKey;

    public EnhanceSystem(NamespacedKey key) {
        this.damageKey = key;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage("Usage: /enchance <재료> ex)구리, 금, 철, 다이아, 에메랄드");
            return true;
        }

        String materialName = args[0].toUpperCase();
        Material material;
        int successChance;
        int damageIncrease;

        switch (materialName) {
            case "COOPPER":
                material = Material.COPPER_INGOT;
                successChance = 1;
                damageIncrease = 1;
                break;

            case "GOLD":
                material = Material.GOLD_INGOT;
                successChance = 3;
                damageIncrease = 3;
                break;

            case "IRON":
                material = Material.IRON_INGOT;
                successChance = 5;
                damageIncrease = 5;
                break;

            case "DIAMOND":
                material = Material.DIAMOND;
                successChance = 15;
                damageIncrease = 8;
                break;

            case "EMERALD":
                material = Material.EMERALD;
                successChance = 20;
                damageIncrease = 10;
                break;

            default:
                player.sendMessage("유효하지 않은 재료입니다.");
                return true;
        }

        ItemStack handItem = player.getInventory().getItemInMainHand();
        if (handItem.getType() == Material.AIR) {
            player.sendMessage("강화할 아이템을 손에 들고 있어야 합니다.");
            return true;
        }

        ItemStack materialItem = new ItemStack(material);
        if (!player.getInventory().containsAtLeast(materialItem, 1)) {
            player.sendMessage("강화에 필요한 재료가 부족합니다.");
            return true;
        }

        player.getInventory().removeItem(materialItem);

        int roll = random.nextInt(100) + 1;
        if (roll <= successChance) {
            ItemMeta meta = handItem.getItemMeta();
            assert meta != null;

            PersistentDataContainer data = meta.getPersistentDataContainer();
            int currentDamage = data.getOrDefault(damageKey, PersistentDataType.INTEGER, 0);

            currentDamage += damageIncrease;
            data.set(damageKey, PersistentDataType.INTEGER, currentDamage);

            String originalName = meta.hasDisplayName() ? meta.getDisplayName() : handItem.getType().name();
            meta.setDisplayName(originalName + " +" + currentDamage + "강화");

            handItem.setItemMeta(meta);

            player.sendMessage("강화 성공!! 무기의 데미지가 " + damageIncrease + " 만큼 증가했습니다.");
        } else {
            player.sendMessage("강화 실패 ㅋㅋ");
        }

        return true;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();
        ItemStack handItem = player.getInventory().getItemInMainHand();
        if (handItem.getType() == Material.AIR) return;

        ItemMeta meta = handItem.getItemMeta();
        if (meta ==null) return;

        PersistentDataContainer data = meta.getPersistentDataContainer();
        int additionalDamage = data.getOrDefault(damageKey, PersistentDataType.INTEGER, 0);

        event.setDamage(event.getDamage() + additionalDamage);
    }
}
