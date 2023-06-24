package br.com.caio.dungeonplugin.listeners;

import br.com.caio.dungeonplugin.utils.BlockManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlock implements Listener {
    private final BlockManager blockManager;

    public BreakBlock(BlockManager blockManager){
        this.blockManager = blockManager;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!blockManager.removePlacedBlock(player, event.getBlock().getLocation())) {
            event.setCancelled(true);
        }
    }
}
