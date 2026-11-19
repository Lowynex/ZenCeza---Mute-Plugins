package com.zentoria.mute.commands;

import com.zentoria.mute.MutePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UncezaCommand implements CommandExecutor {

    private final MutePlugin plugin;

    public UncezaCommand(MutePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(plugin.getPrefix() + "&x&F&F&3&F&3&F&oᴋᴜʟʟᴀɴıᴍ: /ᴜɴᴄᴇᴢᴀ <ᴏʏᴜɴᴄᴜ>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        // DÜZELTME: getMutedPlayers yerine MuteManager üzerinden kontrol ediyoruz
        if (target == null || !plugin.getMuteManager().isMuted(target.getUniqueId())) {
            sender.sendMessage(plugin.getPrefix() + "&x&F&F&3&F&3&F&oʙᴜ ᴏʏᴜɴᴄᴜ ʙᴜʟᴜɴᴀᴍᴀᴅı ᴠᴇʏᴀ ᴍᴜᴛᴇʟɪ ᴅᴇɢɪʟ!");
            return true;
        }

        // Mute kaldırılıyor ve config'den siliniyor
        plugin.getMuteManager().removeMute(target.getUniqueId());

        // Duyuru ve Efektler
        String broadcastMsg = plugin.getPrefix() + "§e" + target.getName() +
                " §7ɪꜱɪᴍʟɪ ᴏʏᴜɴᴄᴜɴᴜɴ ᴍᴜᴛᴇꜱɪ §a" + sender.getName() + " §7ᴛᴀʀᴀꜰıɴᴅᴀɴ ᴋᴀʟᴅıʀıʟᴅı!";

        Bukkit.broadcastMessage(broadcastMsg);
        target.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.5f);

        return true;
    }
}
