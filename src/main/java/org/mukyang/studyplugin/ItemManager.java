package org.mukyang.studyplugin;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemManager {

    private static ItemStack buildItem(Material type, int amount, String displayName, String... lore) {
        ItemStack stack = new ItemStack(type, amount);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(Arrays.asList(lore));
        stack.setItemMeta(meta);
        return stack;
    }

    private static ItemStack buildItem(Material type, int amount, String displayName, Enchantment enchant, int enchantlvl, boolean enchantRestrict, String... lore) {
        ItemStack stack = new ItemStack(type, amount);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(Arrays.asList(lore));
        meta.addEnchant(enchant, enchantlvl, enchantRestrict);
        stack.setItemMeta(meta);
        return stack;
    }

    public static final ItemStack dia = buildItem(Material.DIAMOND, 1, "다이아", "흙에서 나온 다이아");

    public static final ItemStack enchantdia = buildItem(Material.DIAMOND, 1, "Heroic Dia", Enchantment.EFFICIENCY, 4, false, "HEROIC");
}