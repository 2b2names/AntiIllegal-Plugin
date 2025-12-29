package me.tiger.antiIllegal;

import me.tiger.antiIllegal.listener.InventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiIllegal extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(
                new InventoryListener(), this
        );
        getLogger().info("AntiIllegal enabled (2b2t-style)");
    }
}
