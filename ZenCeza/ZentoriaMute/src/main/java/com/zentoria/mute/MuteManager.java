package com.zentoria.mute;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MuteManager {

    private final MutePlugin plugin;
    private final Map<UUID, Long> mutedPlayers = new HashMap<>();

    public MuteManager(MutePlugin plugin) {
        this.plugin = plugin;
        loadMutes();
    }

    // Config'den muteleri yükle
    public void loadMutes() {
        FileConfiguration config = plugin.getConfig();
        if (config.contains("mutes")) {
            for (String uuidStr : config.getConfigurationSection("mutes").getKeys(false)) {
                UUID uuid = UUID.fromString(uuidStr);
                long expireTime = config.getLong("mutes." + uuidStr + ".expire");

                // Süresi geçmediyse RAM'e ekle
                if (expireTime > System.currentTimeMillis()) {
                    mutedPlayers.put(uuid, expireTime);
                }
            }
        }
    }

    // Mute Ekle ve Config'e Kaydet
    public void addMute(UUID uuid, long durationMs, String reason, String executor) {
        long expireTime = System.currentTimeMillis() + durationMs;
        mutedPlayers.put(uuid, expireTime);

        FileConfiguration config = plugin.getConfig();
        config.set("mutes." + uuid.toString() + ".expire", expireTime);
        config.set("mutes." + uuid.toString() + ".reason", reason);
        config.set("mutes." + uuid.toString() + ".executor", executor);
        plugin.saveConfig();
    }

    // Mute Kaldır
    public void removeMute(UUID uuid) {
        mutedPlayers.remove(uuid);
        FileConfiguration config = plugin.getConfig();
        config.set("mutes." + uuid.toString(), null);
        plugin.saveConfig();
    }

    public boolean isMuted(UUID uuid) {
        if (!mutedPlayers.containsKey(uuid)) return false;

        // Süre kontrolü
        if (System.currentTimeMillis() >= mutedPlayers.get(uuid)) {
            removeMute(uuid);
            return false;
        }
        return true;
    }

    public long getRemainingSeconds(UUID uuid) {
        if (!isMuted(uuid)) return 0;
        return (mutedPlayers.get(uuid) - System.currentTimeMillis()) / 1000;
    }
}