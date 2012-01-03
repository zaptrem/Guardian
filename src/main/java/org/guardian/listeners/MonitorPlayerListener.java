package org.guardian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.guardian.Guardian;

public class MonitorPlayerListener extends PlayerListener {

    private final Guardian plugin = Guardian.getInstance();

    public MonitorPlayerListener() {
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_CHAT, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_COMMAND_PREPROCESS, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_BED_ENTER, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_BED_LEAVE, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_BUCKET_EMPTY, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_BUCKET_FILL, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_DROP_ITEM, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_INTERACT, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_JOIN, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_KICK, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_PICKUP_ITEM, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_QUIT, this, Priority.Monitor, plugin);
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_TELEPORT, this, Priority.Monitor, plugin);
    }

    @Override
    public void onPlayerChat(PlayerChatEvent event) {
        super.onPlayerChat(event);
    }

    @Override
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        super.onPlayerCommandPreprocess(event);
    }

    @Override
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        super.onPlayerBedEnter(event);
    }

    @Override
    public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
        super.onPlayerBedLeave(event);
    }

    @Override
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
        super.onPlayerBucketEmpty(event);
    }

    @Override
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {
        super.onPlayerBucketFill(event);
    }

    @Override
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        super.onPlayerDropItem(event);
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        super.onPlayerInteract(event);
    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        super.onPlayerJoin(event);
    }

    @Override
    public void onPlayerKick(PlayerKickEvent event) {
        super.onPlayerKick(event);
    }

    @Override
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        super.onPlayerPickupItem(event);
    }

    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {
        super.onPlayerQuit(event);
    }

    @Override
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        super.onPlayerTeleport(event);
    }
}
