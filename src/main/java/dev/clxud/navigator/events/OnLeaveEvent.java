package dev.clxud.navigator.events;

import dev.clxud.navigator.Plugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnLeaveEvent implements Listener {

    private final Plugin plugin;

    public OnLeaveEvent(Plugin plugin) {
        this.plugin = plugin;

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.inventoryMap.remove(player.getUniqueId());
    }


}
