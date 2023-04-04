package saber.blastchant.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import saber.blastchant.Blastchant;

import java.util.ArrayList;
import java.util.List;

public class BlastchantCommand implements CommandExecutor {

    private Blastchant plugin;
    private NamespacedKey key;

    public BlastchantCommand(Blastchant p1) {
        plugin = p1;
        key = new NamespacedKey(plugin, "Blastin");
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!commandSender.hasPermission("blastchant.add")){
            commandSender.sendMessage("No Perms");
            return true;
        }

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Must be player");
            return true;
        }
        Player play = (Player) commandSender;

        if (play.getInventory().getItemInMainHand().getAmount() == 0){
            play.sendMessage("Must be holding enchantable item");
            return true;
        }

        if (strings.length == 0) {
            commandSender.sendMessage("must have number argument");
            return true;
        }
        Integer num = 0;
        try{
            num = Integer.parseInt(strings[0]);
        } catch (Exception e){
            commandSender.sendMessage("argument must be number");
            return true;
        }

        if (num < 0){
            commandSender.sendMessage("Number must be positive");
            return true;
        }



        ItemStack item = play.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        List<String> temp = new ArrayList<>();
        if (meta.getLore() != null) for (String x : meta.getLore())if (!x.contains(plugin.name)) temp.add(x);

        if (num > 0) temp.add(plugin.name + num);
        meta.setLore(temp);
        if (num > 0) meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, num);
        else meta.getPersistentDataContainer().remove(key);
        item.setItemMeta(meta);
        play.getInventory().setItemInMainHand(item);


        return true;
    }
}
