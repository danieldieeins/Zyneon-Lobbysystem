package live.nerotv.lobbysystem.commands;

import live.nerotv.lobbysystem.api.API;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Clearchat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Clearchat")) {
            if(!(s instanceof Player p)) {
                for(Player all : Bukkit.getOnlinePlayers()) {
                    if(all.hasPermission("nero.team")) {
                        API.sendMessage(all,"§7Der Chat wurde geleert, aber du kannst ihn noch sehen, weil du die Rechte dazu hast.");
                    } else {
                        API.clearPlayerChat(all);
                    }
                }
                Bukkit.getServer().getConsoleSender().sendMessage("§7Der Chat wurde geleert, aber du kannst ihn noch sehen, weil du die Rechte dazu hast.");
            } else {
                if(p.hasPermission("nero.team")) {
                    p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG,100,100);
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        if(all.hasPermission("nero.team")) {
                            API.sendMessage(all,"§7Der Chat wurde geleert, aber du kannst ihn noch sehen, weil du die Rechte dazu hast.");
                        } else {
                            API.clearPlayerChat(all);
                        }
                    }
                    Bukkit.getServer().getConsoleSender().sendMessage("§7Der Chat wurde geleert, aber du kannst ihn noch sehen, weil du die Rechte dazu hast.");
                } else {
                    API.sendErrorMessage(p,API.noPerms);
                }
            }
        }
        return false;
    }
}