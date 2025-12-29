package me.tiger.antiIllegal.listener;

import me.tiger.antiIllegal.util.ItemNormalizer;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (!(event.getPlayer() instanceof Player player)) return;
        if (player.isOp()) return; // OPs excluded

        Inventory inv = event.getInventory();
        boolean changed = false;

        // 🔹 NORMAL inventories (player, chest, etc.)
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            if (item == null) continue;

            ItemStack fixed = ItemNormalizer.normalize(item);
            if (fixed != null) {
                inv.setItem(i, fixed);
                changed = true;
            }
        }

        // 🔹 SHULKER BOXES (contents only)
        if (inv.getHolder() instanceof ShulkerBox shulker) {
            Inventory shulkerInv = shulker.getInventory();

            for (int i = 0; i < shulkerInv.getSize(); i++) {
                ItemStack item = shulkerInv.getItem(i);
                if (item == null) continue;

                ItemStack fixed = ItemNormalizer.normalize(item);
                if (fixed != null) {
                    shulkerInv.setItem(i, fixed);
                    changed = true;
                }
            }

            shulker.update(); // save changes
        }

        if (changed) {
            player.updateInventory();
        }
    }
}
