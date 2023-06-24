package br.com.caio.dungeonplugin.listeners;

import org.bukkit.Material;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BlockList {
    private final Set<Material> allowedBlocks;

    public BlockList() {
        this.allowedBlocks = new HashSet<>(Arrays.asList(
                Material.STONE,
                Material.DIRT,
                Material.TNT
        ));
    }

    public boolean isAllowed(Material blockType) {
        return allowedBlocks.contains(blockType);
    }
}
