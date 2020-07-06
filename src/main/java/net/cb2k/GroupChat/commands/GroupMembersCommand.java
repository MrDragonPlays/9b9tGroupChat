package net.cb2k.GroupChat.commands;

import net.cb2k.GroupChat.GroupChat;
import net.cb2k.GroupChat.objects.OPlayer;
import net.cb2k.GroupChat.util.ChatUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.stream.Collectors;

public class GroupMembersCommand implements CommandExecutor {

    private static final GroupChat plugin = GroupChat.getInstance();

    @Override public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player))
            return ChatUtil.sendConfigMessageRT(commandSender, "error.not-a-player");

        Player player = (Player) commandSender;
        OPlayer oPlayer = plugin.playerManager.getPlayer(player);
        if (oPlayer.getGroup() == null)
            return ChatUtil.sendConfigMessageRT(commandSender, "error.not-in-group");


        String onlineMembers = oPlayer.getGroup().getOnlineMembers(player).stream().map(Player::getName).collect(Collectors.joining(", "));
        String offlineMembers = oPlayer.getGroup().getOfflineMembers(player).stream().map(OfflinePlayer::getName).collect(Collectors.joining(", "));

        player.sendMessage(ChatUtil.color("&b&lMembers for group &a" + oPlayer.getGroup().getCreator()));
        player.sendMessage(ChatUtil.color("&bOnline members: &6" + onlineMembers));
        player.sendMessage(ChatUtil.color("&bOffline members: &6" + offlineMembers));

        return true;
    }

}
