package saber.blastchant.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import saber.blastchant.Blastchant;

public class BlastchantBlockBreakEvent implements Listener {

    private Blastchant plugin;
    private NamespacedKey key;
    public BlastchantBlockBreakEvent(Blastchant p1){
        plugin = p1;
        key = new NamespacedKey(plugin, "Blastin");
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player play = event.getPlayer();
        if (event.getPlayer().getInventory().getItemInMainHand().getAmount() == 0) return;
        PersistentDataContainer container = play.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer();
        if (!container.has(key, PersistentDataType.INTEGER)) return;
        Integer blastnum = container.get(key,PersistentDataType.INTEGER);

        // Get the direction the player is looking and simplify to +X, -X, +Z, or -Z
        int xoff = 0;
        int zoff = 0;
        int yoff = 0;
        double x = play.getPlayer().getLocation().getDirection().getX();
        double z = play.getPlayer().getLocation().getDirection().getZ();
        double y = play.getPlayer().getLocation().getDirection().getY();
        if (Math.abs(x) > Math.abs(z) && Math.abs(x) > Math.abs(y)){
            if (x>0) xoff=1;
            else xoff=-1;
        }else if (Math.abs(z) > Math.abs(x) && Math.abs(z) > Math.abs(y)){
            if (z>0) zoff=1;
            else zoff=-1;
        } else {
            if (y>0) yoff=1;
            else yoff = -1;
        }

        Block blox = event.getBlock();
        for (int i = 0; i < blastnum; i++){
            if (yoff != 0)destroyxz(blox);

            if (xoff != 0) destroyyz(blox);

            if (zoff != 0) destroyxy(blox);

            blox = blox.getRelative(xoff,yoff,zoff);
        }
    }

    public void destroyxz(Block bloc){
        if (!bloc.getRelative(1,0,1).getType().equals(Material.BEDROCK)) bloc.getRelative(1,0,1).breakNaturally();
        if (!bloc.getRelative(0,0,1).getType().equals(Material.BEDROCK)) bloc.getRelative(0,0,1).breakNaturally();
        if (!bloc.getRelative(1,0,0).getType().equals(Material.BEDROCK)) bloc.getRelative(1,0,0).breakNaturally();
        if (!bloc.getRelative(1,0,-1).getType().equals(Material.BEDROCK)) bloc.getRelative(1,0,-1).breakNaturally();
        if (!bloc.getRelative(-1,0,1).getType().equals(Material.BEDROCK)) bloc.getRelative(-1,0,1).breakNaturally();
        if (!bloc.getRelative(-1,0,-1).getType().equals(Material.BEDROCK)) bloc.getRelative(-1,0,-1).breakNaturally();
        if (!bloc.getRelative(-1,0,0).getType().equals(Material.BEDROCK)) bloc.getRelative(-1,0,0).breakNaturally();
        if (!bloc.getRelative(0,0,-1).getType().equals(Material.BEDROCK)) bloc.getRelative(0,0,-1).breakNaturally();
        if (!bloc.getRelative(0,0,0).getType().equals(Material.BEDROCK)) bloc.getRelative(0,0,0).breakNaturally();
    }

    public void destroyxy(Block bloc){
        if (!bloc.getRelative(1,1,0).getType().equals(Material.BEDROCK)) bloc.getRelative(1,1,0).breakNaturally();
        if (!bloc.getRelative(0,1,0).getType().equals(Material.BEDROCK)) bloc.getRelative(0,1,0).breakNaturally();
        if (!bloc.getRelative(1,0,0).getType().equals(Material.BEDROCK)) bloc.getRelative(1,0,0).breakNaturally();
        if (!bloc.getRelative(1,-1,0).getType().equals(Material.BEDROCK)) bloc.getRelative(1,-1,0).breakNaturally();
        if (!bloc.getRelative(-1,1,0).getType().equals(Material.BEDROCK)) bloc.getRelative(-1,1,0).breakNaturally();
        if (!bloc.getRelative(-1,-1,0).getType().equals(Material.BEDROCK)) bloc.getRelative(-1,-1,0).breakNaturally();
        if (!bloc.getRelative(-1,0,0).getType().equals(Material.BEDROCK)) bloc.getRelative(-1,0,0).breakNaturally();
        if (!bloc.getRelative(0,-1,0).getType().equals(Material.BEDROCK)) bloc.getRelative(0,-1,0).breakNaturally();
        if (!bloc.getRelative(0,0,0).getType().equals(Material.BEDROCK)) bloc.getRelative(0,0,0).breakNaturally();
    }

    public void destroyyz(Block bloc){
        if (!bloc.getRelative(0,1,1).getType().equals(Material.BEDROCK)) bloc.getRelative(0,1,1).breakNaturally();
        if (!bloc.getRelative(0,0,1).getType().equals(Material.BEDROCK)) bloc.getRelative(0,0,1).breakNaturally();
        if (!bloc.getRelative(0,1,0).getType().equals(Material.BEDROCK)) bloc.getRelative(0,1,0).breakNaturally();
        if (!bloc.getRelative(0,1,-1).getType().equals(Material.BEDROCK)) bloc.getRelative(0,1,-1).breakNaturally();
        if (!bloc.getRelative(0,-1,1).getType().equals(Material.BEDROCK)) bloc.getRelative(0,-1,1).breakNaturally();
        if (!bloc.getRelative(0,-1,-1).getType().equals(Material.BEDROCK)) bloc.getRelative(0,-1,-1).breakNaturally();
        if (!bloc.getRelative(0,-1,0).getType().equals(Material.BEDROCK)) bloc.getRelative(0,-1,0).breakNaturally();
        if (!bloc.getRelative(0,0,-1).getType().equals(Material.BEDROCK)) bloc.getRelative(0,0,-1).breakNaturally();
        if (!bloc.getRelative(0,0,0).getType().equals(Material.BEDROCK)) bloc.getRelative(0,0,0).breakNaturally();
    }
}
