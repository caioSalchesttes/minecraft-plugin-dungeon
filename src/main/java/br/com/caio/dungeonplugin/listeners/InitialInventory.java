package br.com.caio.dungeonplugin.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.NamespacedKey;

import java.util.List;

public class InitialInventory implements Listener {
    private NamespacedKey key;
    public InitialInventory(NamespacedKey key){
        this.key = key;
    }

    @EventHandler
    public void onPlayer(PlayerJoinEvent event){

        ItemStack item = new ItemStack(Material.DIAMOND_AXE, 1);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName("Espada do caralho");
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "unique_id_value");

        item.setItemMeta(itemMeta);

        Player player = event.getPlayer();

        player.getInventory().setItem(27, item);

    }

    @EventHandler
    public void playerDrop(PlayerDropItemEvent event){
        ItemStack item = event.getItemDrop().getItemStack();
        if (item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void respawnPlayer(PlayerRespawnEvent event){
        ItemStack item = new ItemStack(Material.DIAMOND_AXE, 1);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName("Espada do caralho");
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "unique_id_value");

        item.setItemMeta(itemMeta);

        Player player = event.getPlayer();

        player.getInventory().setItem(27, item);
    }
}
