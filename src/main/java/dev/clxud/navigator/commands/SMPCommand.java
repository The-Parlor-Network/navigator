package dev.clxud.navigator.commands;

import dev.clxud.navigator.Plugin;
import dev.clxud.navigator.utils.ServerSelector;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SMPCommand implements CommandExecutor {


    private final Plugin plugin;
    private final ServerSelector ss;

    public SMPCommand(Plugin plugin) {
        this.plugin = plugin;
        this.ss = new ServerSelector(this.plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player  = (Player) sender;
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Connecting to &bSMP&7..."));
            ss.sendToServer(player, "smp");
            return true;
        }
        return false;
    }
}