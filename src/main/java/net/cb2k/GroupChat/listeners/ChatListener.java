package net.cb2k.GroupChat.listeners;

import net.cb2k.GroupChat.GroupChat;
import net.cb2k.GroupChat.objects.Group;
import net.cb2k.GroupChat.objects.OPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Set;
import java.util.stream.Collectors;

public class ChatListener implements Listener {

    private final static GroupChat plugin = GroupChat.getInstance();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        OPlayer player = plugin.playerManager.getPlayer(event.getPlayer());
        if (!player.isTalkingInGroupChat()) return;

        event.setCancelled(true);

        Group group = player.getGroup();
        Set<Player> onlineMembers = group.getOnlineMembers();
        for (Player member : onlineMembers) {
            // TODO get message from config, then replace placeholders
            member.sendMessage(color("&1<" + player.getUsername() + ">&r " + event.getMessage()));
        }

        Set<Player> chatSpies = Bukkit.getOnlinePlayers().stream()
                .filter(p -> !onlineMembers.contains(p)) // Remove all members of the group since they already received the message
                .filter(p -> p.hasPermission("groupchat.chatspy")) // Remove all players that do not have the permission
                .map(p -> plugin.playerManager.getPlayer(p)) // Change Player into OPlayer object
                .filter(OPlayer::isSpyToggled) // Filter if chat spy is on
                .map(p -> p.getPlayer()) // Change OPlayer into Player object
                .collect(Collectors.toSet());

        for (Player spy : chatSpies) {
            // TODO get message from config, then replace placeholders
            spy.sendMessage(color("&1GROUPSPY - <" + player.getUsername() + ">&r " + event.getMessage()));
        }

    }

    private String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
