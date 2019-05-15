package mykyta.Anchor;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import mykyta.Anchor.Listeners.BlockEvent;

public class Anchor extends JavaPlugin {
    Util util = new Util();

    public void onEnable() {
        util.setInstance(this);
        util.setNMS();
        util.createDatabase();

        saveDefaultConfig();
        Bukkit.getServer().getPluginManager().registerEvents(new BlockEvent(), this);
    }

    public void onDisable() {
        
    }
}
