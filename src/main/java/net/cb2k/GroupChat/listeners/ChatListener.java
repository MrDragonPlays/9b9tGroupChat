package net.cb2k.GroupChat.listeners;

import net.cb2k.GroupChat.GroupChat;
import net.cb2k.GroupChat.objects.Group;
import net.cb2k.GroupChat.objects.OPlayer;
import net.cb2k.GroupChat.util.ChatUtil;
import org.bukkit.Bukkit;
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
            member.sendMessage(ChatUtil.color(ChatUtil.replacePlaceHolders(plugin.getConfig().getString("chat-formatting.standard"), player.getUsername(), event.getMessage(), group.getCreator())));
        }

        Set<Player> chatSpies = Bukkit.getOnlinePlayers().stream()
                .filter(p -> !onlineMembers.contains(p)) // Remove all members of the group since they already received the message
                .filter(p -> p.hasPermission("groupchat.chatspy")) // Remove all players that do not have the permission
                .map(p -> plugin.playerManager.getPlayer(p)) // Change Player into OPlayer object
                .filter(OPlayer::isSpyToggled) // Filter if chat spy is on
                .map(OPlayer::getPlayer) // Change OPlayer into Player object
                .collect(Collectors.toSet());

        for (Player spy : chatSpies) {
            spy.sendMessage(ChatUtil.color(ChatUtil.replacePlaceHolders(plugin.getConfig().getString("chat-formatting.spy"), player.getUsername(), event.getMessage(), group.getCreator())));
        }

    }

}
