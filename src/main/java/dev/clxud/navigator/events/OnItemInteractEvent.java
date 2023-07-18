package dev.clxud.navigator.events;

import dev.clxud.navigator.utils.ServerSelector;
import dev.clxud.navigator.Plugin;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class OnItemInteractEvent implements Listener {

    private Plugin plugin;
    private final ServerSelector ss;

    public OnItemInteractEvent(Plugin plugin) {

        this.plugin = plugin;
        this.ss = new ServerSelector(this.plugin);
    }


    @EventHandler
    public void onItemInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (!player.getGameMode().equals(GameMode.CREATIVE)) {
            if (item != null && item.getType() == Material.COMPASS) {
                event.setCancelled(true);
                ss.openGUI(player);
            }
        }


    }
}
