package net.cb2k.GroupChat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GroupMembersCommand implements CommandExecutor {

    @Override public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        // TODO Check if player is in group

        // TODO Get list of players that this player is allowed to see online

        // TODO Send list of players formatted (pages maybe?)

        return true;
    }

}
