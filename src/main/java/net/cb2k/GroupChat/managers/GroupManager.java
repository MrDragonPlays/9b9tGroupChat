package net.cb2k.GroupChat.managers;

import net.cb2k.GroupChat.GroupChat;
import net.cb2k.GroupChat.objects.Group;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GroupManager {

    private final static GroupChat plugin = GroupChat.getInstance();

    private final ConcurrentHashMap<UUID, Group> groups;

    public GroupManager() {
        groups = new ConcurrentHashMap<>();
    }

    public Group getGroup(UUID uniqueId) {
        if (!groups.containsKey(uniqueId)) {
            Group group = plugin.databaseManager.getGroup(uniqueId);
            if (group != null) groups.put(uniqueId, group);
        }
        return groups.getOrDefault(uniqueId, null);
    }

    public Group getGroup(Player owner) {
        return getGroup(owner.getUniqueId());
    }

    public void addGroup(Group group) {
        groups.put(group.getCreatorUniqueId(), group);
        // TODO Add to database
    }

    public void removeGroup(UUID uniqueId) {
        groups.remove(uniqueId);
        // TODO Remove from database
    }

    public void removeGroup(Group group) {
        removeGroup(group.getCreatorUniqueId());
    }

}
