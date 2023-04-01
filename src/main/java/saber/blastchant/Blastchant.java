package saber.blastchant;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import saber.blastchant.commands.BlastchantCommand;
import saber.blastchant.listeners.BlastchantBlockBreakEvent;

public final class Blastchant extends JavaPlugin {

    public String name;
    @Override
    public void onEnable() {
        // Plugin startup logic
        //Load Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        name = ChatColor.translateAlternateColorCodes('&', getConfig().getString("blastchantName"));

        getCommand("blastchant").setExecutor(new BlastchantCommand(this));
        getServer().getPluginManager().registerEvents(new BlastchantBlockBreakEvent(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
