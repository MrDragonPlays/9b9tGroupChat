package net.cb2k.GroupChat.commands;

import net.cb2k.GroupChat.GroupChat;
import net.cb2k.GroupChat.objects.Group;
import net.cb2k.GroupChat.objects.OPlayer;
import net.cb2k.GroupChat.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GroupCreateCommand implements CommandExecutor {

    private static final GroupChat plugin = GroupChat.getInstance();

    @Override public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player))
            return ChatUtil.sendConfigMessageRT(commandSender, "error.not-a-player");

        Player player = (Player) commandSender;
        OPlayer oPlayer = plugin.playerManager.getPlayer(player);

        if (oPlayer.getGroup() != null)
            return ChatUtil.sendConfigMessageRT(commandSender, "error.already-in-group");

        Group newGroup = new Group(player.getUniqueId());

        plugin.groupManager.addGroup(newGroup);

        oPlayer.setGroup(newGroup);

        return ChatUtil.sendConfigMessageRT(commandSender, "success.created-group");
    }

}
