package net.cb2k.GroupChat;

import net.cb2k.GroupChat.commands.*;
import net.cb2k.GroupChat.listeners.ChatListener;
import net.cb2k.GroupChat.managers.DatabaseManager;
import net.cb2k.GroupChat.managers.GroupManager;
import net.cb2k.GroupChat.managers.PlayerManager;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class GroupChat extends JavaPlugin {

    private static GroupChat instance;

    public GroupManager groupManager;
    public PlayerManager playerManager;
    public DatabaseManager databaseManager;

    private FileConfiguration messageConfig;

    public static GroupChat getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        loadConfig();
        instance = this;
        groupManager = new GroupManager();
        playerManager = new PlayerManager();
        databaseManager = new DatabaseManager();

        // Register commands
        getCommand("gaccept").setExecutor(new GroupAcceptInviteCommand());
        getCommand("gchat").setExecutor(new GroupChatCommand());
        getCommand("gcreate").setExecutor(new GroupCreateCommand());
        getCommand("gdisband").setExecutor(new GroupDisbandCommand());
        getCommand("ginvite").setExecutor(new GroupInviteCommand());
        getCommand("gkick").setExecutor(new GroupKickCommand());
        getCommand("gleave").setExecutor(new GroupLeaveCommand());
        getCommand("gmembers").setExecutor(new GroupMembersCommand());
        getCommand("gspy").setExecutor(new GroupSpyCommand());


        // Register events
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }

    @Override
    public void onDisable() {

    }

    public void loadConfig() {
        saveDefaultConfig();
        reloadConfig();

        File messageFile = new File(getDataFolder(), "messages.yml");
        if (!messageFile.exists()) {
            messageFile.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }
        messageConfig = new YamlConfiguration();
        try {
            messageConfig.load(messageFile);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
    }

    public FileConfiguration getMessageConfig() {
        return messageConfig;
    }
}
