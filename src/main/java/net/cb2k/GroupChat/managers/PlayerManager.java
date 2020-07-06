package net.cb2k.GroupChat.managers;

import net.cb2k.GroupChat.GroupChat;
import net.cb2k.GroupChat.objects.OPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {

    private static final GroupChat plugin = GroupChat.getInstance();

    private ConcurrentHashMap<UUID, OPlayer> players;

    // TODO Add task to loop through players hashmap and remove offline players

    public PlayerManager() {
        players = new ConcurrentHashMap<>();
    }

    public OPlayer getPlayer(UUID uniqueId) {
        if (!players.containsKey(uniqueId)) loadPlayer(uniqueId);
        return players.getOrDefault(uniqueId, null);
    }

    public OPlayer getPlayer(Player player) {
        return getPlayer(player.getUniqueId());
    }

    public OPlayer loadPlayer(UUID uniqueId) {
        return players.put(uniqueId, plugin.databaseManager.getPlayer(uniqueId));
    }

    public void loadPlayer(Player player) {
        loadPlayer(player.getUniqueId());
    }

    public void unloadPlayer(Player player) {
        plugin.databaseManager.savePlayer(players.getOrDefault(player.getUniqueId(), null));
        players.remove(player.getUniqueId());
    }

}

