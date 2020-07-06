package net.cb2k.GroupChat.commands;

import net.cb2k.GroupChat.GroupChat;
import net.cb2k.GroupChat.objects.Group;
import net.cb2k.GroupChat.objects.OPlayer;
import net.cb2k.GroupChat.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GroupAcceptInviteCommand implements CommandExecutor {

    private static GroupChat plugin = GroupChat.getInstance();

    @Override public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player))
            return ChatUtil.sendConfigMessageRT(commandSender, "error.not-a-player");

        Player player = (Player) commandSender;
        OPlayer oPlayer = plugin.playerManager.getPlayer(player);

        if (oPlayer.getGroup() != null)
            return ChatUtil.sendConfigMessageRT(commandSender, "error.already-in-group");

        if (args.length < 1)
            return ChatUtil.sendConfigMessageRT(commandSender, "error.not-enough-arguments");

        Group targetGroup = plugin.groupManager.getGroup(Bukkit.getPlayer(args[0]));

        if (!targetGroup.isInvited(player))
            return ChatUtil.sendConfigMessageRT(commandSender, "error.not-invited-to-group", targetGroup.getCreator());

        targetGroup.removeInvite(player); // Revoke invite
        targetGroup.addPlayer(player); // Add player object to group
        oPlayer.setGroup(targetGroup); // Add group object to player

        return true;
    }

}
