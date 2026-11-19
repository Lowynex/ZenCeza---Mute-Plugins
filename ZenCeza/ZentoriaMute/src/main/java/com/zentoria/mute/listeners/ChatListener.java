package com.zentoria.mute.listeners;

import com.zentoria.mute.MutePlugin;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final MutePlugin plugin;

    public ChatListener(MutePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (plugin.getMuteManager().isMuted(player.getUniqueId())) {
            event.setCancelled(true);

            long remainingSeconds = plugin.getMuteManager().getRemainingSeconds(player.getUniqueId());

            // Chat engellendiğinde oyuncunun kendi ekranında çıkacak kart
            player.sendMessage("§8§m----------------------------------------------------");
            player.sendMessage("  §c§l🚫 SOHBET ENGELENDİ");
            player.sendMessage("  §7Susturulduğunuz için mesaj gönderemezsiniz.");
            player.sendMessage("  §7Kalan Süreniz: §e" + remainingSeconds + " saniye");
            player.sendMessage("§8§m----------------------------------------------------");

            // Oyuncuya mini uyarı sesi
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
        }
    }
}