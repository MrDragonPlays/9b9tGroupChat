package net.cb2k.GroupChat.objects;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Group {

    private final UUID creatorUniqueId;
    private final String creator;
    private final Set<OfflinePlayer> members;
    private final Set<OfflinePlayer> invites;

    public Group(UUID paramCreator) {
        this.creatorUniqueId = paramCreator;
        this.creator = Bukkit.getOfflinePlayer(paramCreator).getName();
        this.members = new LinkedHashSet<>();
        this.invites = new LinkedHashSet<>();
        members.add(Bukkit.getOfflinePlayer(paramCreator));
    }

    public Group(UUID paramCreator, Set<OfflinePlayer> paramMembers, Set<OfflinePlayer> paramInvites) {
        this(paramCreator);
        members.addAll(paramMembers);
        invites.addAll(paramInvites);
    }

    public GroupRole getRole(UUID paramPlayer) {
        if (paramPlayer == creatorUniqueId) return GroupRole.OWNER;
        return GroupRole.MEMBER;
    }

    public String getCreator() {
        return creator;
    }

    public UUID getCreatorUniqueId() {
        return creatorUniqueId;
    }

    public Set<OfflinePlayer> getAllMembers() {
        return members;
    }

    public Set<Player> getOnlineMembers() {
        return members.stream()
                .filter(OfflinePlayer::isOnline) // Check if players are online
                .map(player -> Bukkit.getPlayer(player.getUniqueId())) // Change OfflinePlayer into Player object
                .collect(Collectors.toSet());
    }

    public Set<Player> getOnlineMembers(Player paramPlayer) {
        return getOnlineMembers().stream()
                .filter(paramPlayer::canSee) // Remove vanished players
                .collect(Collectors.toSet());
    }

    public Set<OfflinePlayer> getOfflineMembers(Player paramPlayer) {
        Set<OfflinePlayer> output = new LinkedHashSet<>(members);
        output.removeAll(getOnlineMembers(paramPlayer));
        return output;
    }

    public boolean isInvited(UUID uniqueId) {
        return invites.contains(Bukkit.getOfflinePlayer(uniqueId));
    }

    public boolean isInvited(Player player) {
        return isInvited(player.getUniqueId());
    }

    public void removeInvite(UUID uniqueId) {
        invites.remove(Bukkit.getOfflinePlayer(uniqueId));
    }

    public void removeInvite(Player player) {
        removeInvite(player.getUniqueId());
    }

    public void addInvite(UUID uniqueId) {
        invites.add(Bukkit.getOfflinePlayer(uniqueId));
    }

    public void addPlayer(UUID uniqueId) {
        members.add(Bukkit.getOfflinePlayer(uniqueId));
    }

    public void addPlayer(Player player) {
        addPlayer(player.getUniqueId());
    }

    public void removePlayer(UUID uniqueId) {
        members.remove(Bukkit.getOfflinePlayer(uniqueId));
    }

    public void removePlayer(Player player) {
        removePlayer(player.getUniqueId());
    }


}
