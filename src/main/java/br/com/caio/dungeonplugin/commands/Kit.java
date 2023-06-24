package br.com.caio.dungeonplugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
public class Kit implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(!(commandSender instanceof Player)) return false;

        Player player = (Player) commandSender;

        if(commandSender.getName().equalsIgnoreCase("kit")){

            if(!(strings.length == 0)){
                player.sendMessage(ChatColor.RED+"Ai não né meu");
            }

            ItemStack newItem = new ItemStack(Material.DIAMOND_BLOCK, 1);
            player.getInventory().setItem(36, newItem);

        }

        return false;
    }
}
