package dev.clxud.navigator;

import dev.clxud.navigator.commands.AnarchyCommand;
import dev.clxud.navigator.commands.ModdedCommand;
import dev.clxud.navigator.commands.SMPCommand;
import dev.clxud.navigator.commands.ServersCommand;
import dev.clxud.navigator.events.*;
import dev.clxud.navigator.utils.InventorySerializer;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public final class Plugin extends JavaPlugin {

    public HashMap<UUID, String> inventoryMap = new HashMap<>();
    public InventorySerializer invSerializer = new InventorySerializer();

    public void serializeInv(UUID uuid, PlayerInventory inventory) {
        String serialized = invSerializer.playerInventoryToBase64(inventory);
        inventoryMap.put(uuid, serialized);
    }

    public void deserializeInv(UUID uuid, PlayerInventory inventory) throws IOException {
        String serialized = inventoryMap.get(uuid);
        inventoryMap.remove(uuid);
        inventoryMap.put(uuid, serialized);
        ItemStack[] items = invSerializer.itemStackArrayFromBase64(serialized);
        inventory.setContents(items);
    }


    @Override
    public void onEnable() {

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().severe("Could not find PlaceholderAPI! Disabling plugin...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        getServer().getPluginManager().registerEvents(new OnInventoryClickEvent(this), this);
        getServer().getPluginManager().registerEvents(new OnInventoryInteractEvent(), this);
        getServer().getPluginManager().registerEvents(new OnItemInteractEvent(this), this);
        getServer().getPluginManager().registerEvents(new OnJoinEvent(this), this);
        getServer().getPluginManager().registerEvents(new OnGamemodeChange(this), this);
        getServer().getPluginManager().registerEvents(new OnLeaveEvent(this), this);
        getCommand("servers").setExecutor(new ServersCommand(this));
        getCommand("anarchy").setExecutor(new AnarchyCommand(this));
        getCommand("smp").setExecutor(new SMPCommand(this));
        getCommand("modded").setExecutor(new ModdedCommand(this));
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getLogger().info("Navigator has been loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Navigator is now shutting down, goodbye!");
    }
}
