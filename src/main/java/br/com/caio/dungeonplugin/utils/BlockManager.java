package br.com.caio.dungeonplugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BlockManager {
    private static Map<UUID, List<String>> placedBlocks = new HashMap<>();
    private static File dataFile;
    private static YamlConfiguration dataConfig;

    static {
        loadData();
    }

    public static void addPlacedBlock(Player player, Location blockLocation) {
        UUID playerId = player.getUniqueId();
        placedBlocks.computeIfAbsent(playerId, k -> new ArrayList<>());
        placedBlocks.get(playerId).add(serializeLocation(blockLocation));
        saveData();
    }

    public static boolean removePlacedBlock(Player player, Location blockLocation) {
        UUID playerId = player.getUniqueId();
        List<String> playerBlocks = placedBlocks.get(playerId);
        if (playerBlocks != null) {
            String serializedLocation = serializeLocation(blockLocation);
            if (playerBlocks.remove(serializedLocation)) {
                saveData();
                return true;
            }
        }
        return false;
    }

    public boolean wasBlockPlacedByPlayer(Location blockLocation) {
        for (List<String> playerBlocks : placedBlocks.values()) {
            for (String serializedLocation : playerBlocks) {
                Location placedBlockLocation = deserializeLocation(serializedLocation);
                if (placedBlockLocation.equals(blockLocation)) {
                    return false;
                }
            }
        }
        return true;
    }

    private Location deserializeLocation(String serializedLocation) {
        String[] parts = serializedLocation.split(",");
        String worldName = parts[0];
        int x = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);
        int z = Integer.parseInt(parts[3]);
        return new Location(Bukkit.getWorld(worldName), x, y, z);
    }

    private static void saveData() {
        dataConfig = new YamlConfiguration();
        for (UUID playerId : placedBlocks.keySet()) {
            List<String> playerBlocks = placedBlocks.get(playerId);
            dataConfig.set(playerId.toString(), playerBlocks);
        }

        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadData() {
        dataFile = new File("placedBlocks.yml");
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);

        placedBlocks.clear();
        for (String playerId : dataConfig.getKeys(false)) {
            UUID uuid = UUID.fromString(playerId);
            List<String> playerBlocks = dataConfig.getStringList(playerId);
            placedBlocks.put(uuid, playerBlocks);
        }
    }

    private static String serializeLocation(Location location) {
        return location.getWorld().getName() + "," + location.getBlockX() + "," +
                location.getBlockY() + "," + location.getBlockZ();
    }
}
