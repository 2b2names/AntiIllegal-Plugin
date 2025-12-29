package me.tiger.antiIllegal;

import me.tiger.antiIllegal.listener.InventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiIllegal extends JavaPlugin {

    // Flag to track if AntiIllegal is active
    private static boolean antiIllegalEnabled = true;

    @Override
    public void onEnable() {
        // Register listener with reference to this plugin
        Bukkit.getPluginManager().registerEvents(
                new InventoryListener(this), this
        );
        getLogger().info("AntiIllegal enabled");
    }

    // Accessor for listener / other classes
    public static boolean isAntiIllegalEnabled() {
        return antiIllegalEnabled;
    }

    public static void setAntiIllegalEnabled(boolean state) {
        antiIllegalEnabled = state;
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
            AntiIllegal.setAntiIllegalEnabled(true);
            sender.sendMessage("§aAntiIllegal enabled.");
        } else if (args[0].equalsIgnoreCase("disable")) {
            AntiIllegal.setAntiIllegalEnabled(false);
            sender.sendMessage("§cAntiIllegal disabled.");
        } else {
            sender.sendMessage("§7Usage: /antiillegal <enable|disable>");
        }

        return true;
    }
}
