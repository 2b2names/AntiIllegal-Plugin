package me.tiger.antiIllegal.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class ItemNormalizer {

    private static final Material[] ILLEGAL_BLOCKS = {
            Material.BEDROCK,
            Material.END_PORTAL,
            Material.END_PORTAL_FRAME,
            Material.COMMAND_BLOCK,
            Material.REPEATING_COMMAND_BLOCK,
            Material.CHAIN_COMMAND_BLOCK,
            Material.BARRIER,
            Material.STRUCTURE_BLOCK,
            Material.STRUCTURE_VOID,
            Material.JIGSAW
    };

    public static ItemStack normalize(ItemStack item) {
        ItemStack copy = item.clone();
        boolean changed = false;

        // Unobtainable blocks → dirt
        for (Material mat : ILLEGAL_BLOCKS) {
            if (copy.getType() == mat) {
                copy.setType(Material.DIRT);
                changed = true;
                break;
            }
        }

        // Clamp enchantments to vanilla max
        if (copy.hasItemMeta()) {
            ItemMeta meta = copy.getItemMeta();
            Map<Enchantment, Integer> enchants = new HashMap<>(meta.getEnchants());

            for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) {
                Enchantment ench = entry.getKey();
                int level = entry.getValue();

                if (level > ench.getMaxLevel()) {
                    meta.removeEnchant(ench);
                    meta.addEnchant(ench, ench.getMaxLevel(), true);
                    changed = true;
                }
            }

            copy.setItemMeta(meta);
        }

        return changed ? copy : null;
    }
}
