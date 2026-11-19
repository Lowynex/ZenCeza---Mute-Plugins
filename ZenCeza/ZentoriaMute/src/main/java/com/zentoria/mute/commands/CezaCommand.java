package com.zentoria.mute.commands;

import com.zentoria.mute.MutePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CezaCommand implements CommandExecutor {

    private final MutePlugin plugin;

    public CezaCommand(MutePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 3) {
            sender.sendMessage(plugin.getPrefix() + "&x&F&F&3&F&3&F&oᴋᴜʟʟᴀɴıᴍ: /ᴄᴇᴢᴀ <ᴏʏᴜɴᴄᴜ> <ꜱᴜʀᴇ (ɢ/ᴅ/ꜱ)> <ꜱᴇʙᴇᴘ>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(plugin.getPrefix() + "&x&F&F&3&F&3&F&oᴏʏᴜɴᴄᴜ ʙᴜʟᴜɴᴀᴍᴀᴅı!");
            return true;
        }

        long durationMs = plugin.parseTime(args[1]);
        if (durationMs <= 0) {
            sender.sendMessage(plugin.getPrefix() + "&x&F&F&3&F&3&F&oɢᴇᴄᴇʀꜱɪᴢ ꜱᴜʀᴇ! ᴏʀɴᴇᴋ:ɢ (ɢᴜɴ),ᴅ (ᴅᴀᴋɪᴋᴀ),ꜱ (ꜱᴀɴɪʏᴇ)");
            return true;
        }

        String reason = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
        long expireTime = System.currentTimeMillis() + durationMs;

        plugin.getMuteManager().addMute(target.getUniqueId(), durationMs, reason, sender.getName());

        // 1. EKRANA TITLE & SUBTITLE GÖNDERME
        target.sendTitle(
                "§c§lCEZA ALDINIZ!",
                "§7Susturuldunuz! Kalan Süre: §e" + args[1],
                10, 70, 20
        );

        // 2. ÖZEL SES EFEKTİ
        target.playSound(target.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 0.7f, 1.2f);

        // 3. ÇOK SATIRLI VE ŞAŞAALI CHAT DUYURUSU (Tüm Sunucuya)
        String[] broadcastLines = {
                "§8§m----------------------------------------------------",
                "  §c§l⚖ ADALET SAĞLANDI §8| §fCeza Bildirimi",
                "",
                "  §7• Ceza Alan: §e" + target.getName(),
                "  §7• Cezalandıran: §a" + sender.getName(),
                "  §7• Süre: §b" + args[1],
                "  §7• Sebep: §f" + reason,
                "",
                "  §8§o» Lütfen sunucu kurallarına uymaya özen gösteriniz.",
                "§8§m----------------------------------------------------"
        };

        for (String line : broadcastLines) {
            Bukkit.broadcastMessage(line);
        }

        return true;
    }
}