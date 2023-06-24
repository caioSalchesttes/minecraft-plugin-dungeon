package br.com.caio.dungeonplugin.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class killMonster implements Listener {

    @EventHandler
    public void dropItemMoster(EntityDeathEvent event){
        event.getDrops().clear();
        ItemStack item = new ItemStack(Material.EMERALD, random());
        event.getDrops().add(item);
    }

    @EventHandler
    public void getDrop(PlayerPickupItemEvent event){
        //Get player
        Player player = event.getPlayer();
        Item item = event.getItem();

        //Send message to player
        player.sendMessage("Você pegou um total de " + item.getItemStack().getAmount() + " esmeraldas");

    }

    public static int random() {
        int maxNumber = 100;
        double p = new Random().nextDouble();
        int generatedNumber = (int)(maxNumber / Math.pow(p, 1.0 / maxNumber));
        return generatedNumber;
    }

}
