package net.cb2k.GroupChat.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class OPlayer {


    private final String username;
    private final UUID uniqueId;
    private boolean talkingInGroupChat;
    private boolean spyToggled;
    private GroupRole role;
    private Group group;

    public OPlayer(UUID paramUniqueId) {
        this.uniqueId = paramUniqueId;
        this.username = Bukkit.getOfflinePlayer(paramUniqueId).getName();
        this.talkingInGroupChat = false;
        this.spyToggled = false;
    }

    public OPlayer(UUID paramUniqueId, Group paramGroup) {
        this(paramUniqueId);
        this.group = paramGroup;
        if (group != null) this.role = group.getRole(paramUniqueId);
    }

    public boolean isTalkingInGroupChat() {
        return talkingInGroupChat;
    }

    public void setTalkingInGroupChat(boolean talkingInGroupChat) {
        this.talkingInGroupChat = talkingInGroupChat;
    }

    public boolean isSpyToggled() {
        return spyToggled;
    }

    public void setSpyToggled(boolean spyToggled) {
        this.spyToggled = spyToggled;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
        this.role = group.getRole(this.uniqueId);
    }

    public GroupRole getRole() {
        return role;
    }

    public void setRole(GroupRole role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uniqueId);
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OPlayer oPlayer = (OPlayer) o;
        return uniqueId.equals(oPlayer.uniqueId);
    }

    @Override public int hashCode() {
        return Objects.hash(uniqueId);
    }
}
