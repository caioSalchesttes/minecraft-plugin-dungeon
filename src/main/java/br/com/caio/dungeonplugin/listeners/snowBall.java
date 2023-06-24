package br.com.caio.dungeonplugin.listeners;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class snowBall implements Listener {

    @EventHandler
    public void shotSnow(ProjectileHitEvent event){
        if (event.getEntity() instanceof Snowball) {

            Location hitLocation = event.getEntity().getLocation();
            World world = hitLocation.getWorld();
            if (world != null) {
                world.createExplosion(hitLocation, 4.0F);
            }
        }
    }
}
