package me.tiger.antiIllegal;

import me.tiger.antiIllegal.listener.InventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiIllegal extends JavaPlugin {

    private static boolean enabled = true;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(
                new InventoryListener(this), this
        );
        getLogger().info("AntiIllegal enabled");
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean state) {
        enabled = state;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("§cNo permission.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("§7Usage: /antiillegal <enable|disable>");
            return true;
        }

        if (args[0].equalsIgnoreCase("enable")) {
            enabled = true;
            sender.sendMessage("§aAntiIllegal enabled.");
        } else if (args[0].equalsIgnoreCase("disable")) {
            enabled = false;
            sender.sendMessage("§cAntiIllegal disabled.");
        } else {
            sender.sendMessage("§7Usage: /antiillegal <enable|disable>");
        }

        return true;
    }
}
