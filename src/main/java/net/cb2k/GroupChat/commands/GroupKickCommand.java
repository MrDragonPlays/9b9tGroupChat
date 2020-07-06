package net.cb2k.GroupChat.commands;

import net.cb2k.GroupChat.GroupChat;
import net.cb2k.GroupChat.objects.GroupRole;
import net.cb2k.GroupChat.objects.OPlayer;
import net.cb2k.GroupChat.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GroupKickCommand implements CommandExecutor {

    private static final GroupChat plugin = GroupChat.getInstance();

    @Override public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player))
            return ChatUtil.sendConfigMessageRT(commandSender, "error.not-a-player");

        Player player = (Player) commandSender;
        OPlayer oPlayer = plugin.playerManager.getPlayer(player);

        if (oPlayer.getGroup() == null)
            return ChatUtil.sendConfigMessageRT(commandSender, "error.not-in-group");

        if (oPlayer.getRole() != GroupRole.OWNER)
            return ChatUtil.sendConfigMessageRT(commandSender, "error.not-enough-permissions");

        if (args.length < 1)
            return ChatUtil.sendConfigMessageRT(commandSender, "error.not-enough-arguments"); // Checks if there are any arguments

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]); // Get target by first argument

        if (target == null)
            return ChatUtil.sendConfigMessageRT(commandSender, "error.invalid-player-argument"); // Checks if the target was found

        OPlayer OTarget = plugin.playerManager.getPlayer(target.getUniqueId());

        if (OTarget.getGroup() != oPlayer.getGroup())
            return ChatUtil.sendConfigMessageRT(commandSender, "error.player-not-in-group", OTarget.getUsername()); // Check if the target is in the senders group

        oPlayer.getGroup().removePlayer(OTarget.getUniqueId()); // Remove player object from group

        OTarget.setGroup(null); // Remove group object from player

        return true;
    }

}
