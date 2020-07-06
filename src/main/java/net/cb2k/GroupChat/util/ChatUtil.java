package net.cb2k.GroupChat.util;

import net.cb2k.GroupChat.GroupChat;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatUtil {

    private static final GroupChat plugin = GroupChat.getInstance();

    public static void sendMessage(CommandSender sender, String string) {
        sender.sendMessage(color(string));
    }

    public static void sendConfigMessage(CommandSender sender, String path, String... placeholders) {
        if (!path.contains(".")) return;
        String prefix = plugin.getMessageConfig().getString(path.split("\\.")[0] + ".prefix");
        String message = plugin.getMessageConfig().getString(path);
        for (int i = 0; i < placeholders.length; i++) {
            message = message.replaceAll("\\{" + i + "}", placeholders[i]);
        }
        sendMessage(sender, prefix + message);
    }

    public static boolean sendConfigMessageRT(CommandSender sender, String path, String... placeholders) {
        sendConfigMessage(sender, path, placeholders);
        return true;
    }

    public static String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
