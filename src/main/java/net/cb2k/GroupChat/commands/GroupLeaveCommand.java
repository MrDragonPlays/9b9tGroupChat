package net.cb2k.GroupChat.commands;

import net.cb2k.GroupChat.GroupChat;
import net.cb2k.GroupChat.objects.GroupRole;
import net.cb2k.GroupChat.objects.OPlayer;
import net.cb2k.GroupChat.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GroupLeaveCommand implements CommandExecutor {

    private static final GroupChat plugin = GroupChat.getInstance();

    @Override public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player))
            return ChatUtil.sendConfigMessageRT(commandSender, "error.not-a-player");

        Player player = (Player) commandSender;
        OPlayer oPlayer = plugin.playerManager.getPlayer(player);

        if (oPlayer.getGroup() == null)
            return ChatUtil.sendConfigMessageRT(commandSender, "error.not-in-group");

        if (oPlayer.getRole() == GroupRole.OWNER)
            return ChatUtil.sendConfigMessageRT(commandSender, "error.cannot-leave-own-group");

        String ownerName = oPlayer.getGroup().getCreator();
        oPlayer.getGroup().removePlayer(player); // Remove player object from group
        oPlayer.setGroup(null); // Remove group object from player

        return ChatUtil.sendConfigMessageRT(commandSender, "success.left-group", ownerName);
    }

}
