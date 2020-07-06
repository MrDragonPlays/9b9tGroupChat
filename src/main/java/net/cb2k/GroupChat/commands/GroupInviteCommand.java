package net.cb2k.GroupChat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GroupInviteCommand implements CommandExecutor {

    @Override public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        // TODO Check if player is in group

        // TODO Check if player has permission to invite (if this is needed idk)

        // TODO Add target player to list of invited players

        // TODO Send target player a message that they were invited

        return true;
    }

}
