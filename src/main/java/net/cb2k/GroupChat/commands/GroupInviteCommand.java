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

public class GroupInviteCommand implements CommandExecutor {

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
            return ChatUtil.sendConfigMessageRT(commandSender, "error.not-enough-arguments");

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

        if (target == null)
            return ChatUtil.sendConfigMessageRT(commandSender, "error.invalid-player-argument");

        oPlayer.getGroup().addInvite(target.getUniqueId());

        if (target.isOnline())
            return ChatUtil.sendConfigMessageRT(Bukkit.getPlayer(target.getUniqueId()), "other.invited-to-group", oPlayer.getGroup().getCreator());

        return true;
    }

}
