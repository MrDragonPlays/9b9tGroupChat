package net.cb2k.GroupChat.managers;

import net.cb2k.GroupChat.GroupChat;
import net.cb2k.GroupChat.objects.Group;
import net.cb2k.GroupChat.objects.OPlayer;
import org.bukkit.OfflinePlayer;

import java.util.Set;
import java.util.UUID;

public class DatabaseManager {

    private static final GroupChat plugin = GroupChat.getInstance();

    public DatabaseManager() {

    }

    public OPlayer getPlayer(UUID uniqueId) {
        // TODO Get players group from database
        // Group group = plugin.groupManager.getGroup(groupId);
        Group group = null;
        return new OPlayer(uniqueId, group);
    }

    public Group getGroup(UUID uniqueId) {
        // TODO Get set of members from database
        Set<OfflinePlayer> members = null;
        Set<OfflinePlayer> invites = null;
        return new Group(uniqueId, members, invites);
    }

    public void saveGroup(Group group) {

    }

    public void savePlayer(OPlayer player) {

    }

}
