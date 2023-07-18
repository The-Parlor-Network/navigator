package dev.clxud.navigator.events;

import dev.clxud.navigator.Plugin;
import dev.clxud.navigator.utils.InventorySerializer;
import dev.clxud.navigator.utils.ServerSelector;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

import java.io.IOException;

public class OnGamemodeChange implements Listener {

    private final Plugin plugin;
    private final ServerSelector ss;

    public OnGamemodeChange(Plugin plugin) {
        this.plugin = plugin;
        this.ss = new ServerSelector(this.plugin);
    }

    @EventHandler
    public void onGamemodeChange(PlayerGameModeChangeEvent event) throws IOException {
        Player player = event.getPlayer();
        if (event.getNewGameMode() != GameMode.CREATIVE) {
            plugin.serializeInv(player.getUniqueId(), player.getInventory());
            player.getInventory().clear();
            ss.handleCompassInit(player);
        } else {
            player.getInventory().clear();
            if (plugin.inventoryMap.containsKey(player.getUniqueId())) {
                plugin.deserializeInv(player.getUniqueId(), player.getInventory());
            }
        }

    }
}
