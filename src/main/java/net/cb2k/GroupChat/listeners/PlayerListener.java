package net.cb2k.GroupChat.listeners;

import net.cb2k.GroupChat.GroupChat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private static final GroupChat plugin = GroupChat.getInstance();

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        plugin.playerManager.unloadPlayer(event.getPlayer());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        plugin.playerManager.loadPlayer(event.getPlayer());
    }

}
