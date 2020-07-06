package net.cb2k.GroupChat.commands;

import net.cb2k.GroupChat.GroupChat;
import net.cb2k.GroupChat.objects.OPlayer;
import net.cb2k.GroupChat.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GroupSpyCommand implements CommandExecutor {

    private static final GroupChat plugin = GroupChat.getInstance();

    @Override public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player))
            return ChatUtil.sendConfigMessageRT(commandSender, "error.not-a-player");

        if (!commandSender.hasPermission("groupchats.spy"))
            return ChatUtil.sendConfigMessageRT(commandSender, "error.not-enough-permissions");

        Player player = (Player) commandSender;
        OPlayer oPlayer = plugin.playerManager.getPlayer(player);
        oPlayer.setSpyToggled(!oPlayer.isSpyToggled());

        return ChatUtil.sendConfigMessageRT(player, "success.toggled-spy", oPlayer.isSpyToggled() ? "enabled" : "disabled", oPlayer.isSpyToggled() ? "Enabled" : "Disabled");
    }

}
