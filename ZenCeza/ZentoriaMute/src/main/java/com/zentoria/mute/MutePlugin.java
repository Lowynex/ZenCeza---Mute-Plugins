package com.zentoria.mute;

import com.zentoria.mute.commands.CezaCommand;
import com.zentoria.mute.commands.UncezaCommand;
import com.zentoria.mute.listeners.ChatListener;
import org.bukkit.plugin.java.JavaPlugin;

public class MutePlugin extends JavaPlugin {

    // 1. MuteManager nesnesini tanımlıyoruz
    private MuteManager muteManager;
    private final String prefix = "§8[§ᴄᴇᴢᴇ - ᴍᴜᴛᴇ§8] §r";

    @Override
    public void onEnable() {
        // 2. MuteManager'ı başlatıyoruz
        this.muteManager = new MuteManager(this);

        getCommand("ceza").setExecutor(new CezaCommand(this));
        getCommand("unceza").setExecutor(new UncezaCommand(this));
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
    }

    // 3. Kırmızı yanan getMuteManager() metodunu buraya ekliyoruz
    public MuteManager getMuteManager() {
        return muteManager;
    }

    public String getPrefix() {
        return prefix;
    }

    public long parseTime(String input) {
        if (input == null || input.length() < 2) return -1;
        char unit = input.charAt(input.length() - 1);
        try {
            long duration = Long.parseLong(input.substring(0, input.length() - 1));
            return switch (unit) {
                case 's' -> duration * 1000;
                case 'd' -> duration * 60 * 1000;
                case 'g' -> duration * 24 * 60 * 60 * 1000;
                default -> -1;
            };
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}