package br.com.caio.dungeonplugin;

import br.com.caio.dungeonplugin.commands.Kit;
import br.com.caio.dungeonplugin.listeners.*;
import org.bukkit.NamespacedKey;
import br.com.caio.dungeonplugin.utils.BlockManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DungeonRealms extends JavaPlugin {

    private NamespacedKey key;
    @Override
    public void onEnable() {
        getCommand("kit").setExecutor(new Kit());
        key = new NamespacedKey(this, "unique_id");
        BlockManager blockManager = new BlockManager();
        this.getServer().getPluginManager().registerEvents(new BreakBlock(blockManager), this);
        this.getServer().getPluginManager().registerEvents(new PlaceBlock(blockManager), this);
        this.getServer().getPluginManager().registerEvents(new TntBreak(blockManager), this);
        this.getServer().getPluginManager().registerEvents(new InitialInventory(key), this);
        this.getServer().getPluginManager().registerEvents(new snowBall(), this);
        this.getServer().getPluginManager().registerEvents(new killMonster(), this);
    }

    @Override
    public void onDisable() {
    }
}
