package br.com.caio.dungeonplugin.listeners;

import br.com.caio.dungeonplugin.utils.BlockManager;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class TntBreak implements Listener {
    private final BlockManager blockManager;

    public TntBreak(BlockManager blockManager) {
        this.blockManager = blockManager;
    }

    @EventHandler
    public void onTNTExplosion(EntityExplodeEvent event) {
        if (event.getEntity() instanceof TNTPrimed) {
            event.blockList().removeIf(block -> blockManager.wasBlockPlacedByPlayer(block.getLocation()));
        }
    }
}