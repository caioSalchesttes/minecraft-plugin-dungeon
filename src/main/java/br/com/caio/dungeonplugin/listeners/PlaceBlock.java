package br.com.caio.dungeonplugin.listeners;

import br.com.caio.dungeonplugin.utils.BlockManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceBlock implements Listener {

    private final BlockManager blockManager;

    public PlaceBlock(BlockManager blockManager){
        this.blockManager = blockManager;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        BlockList blockList = new BlockList();

        Player player = event.getPlayer();
        blockManager.addPlacedBlock(player, event.getBlock().getLocation());

        if(!blockList.isAllowed(event.getBlock().getType())){
            event.setBuild(false);
        }
    }
}
