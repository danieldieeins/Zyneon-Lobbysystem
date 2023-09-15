package live.nerotv.lobbysystem.commands;

import live.nerotv.api.paper.Nero;
import live.nerotv.api.paper.utils.user.User;
import live.nerotv.api.utils.Strings;
import live.nerotv.lobbysystem.api.API;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Build implements CommandExecutor {

    private void sendSyntax(CommandSender s) {
        if(!(s instanceof Player)) {
            s.sendMessage("§4Fehler: §c/build [Spieler]");
        } else {
            Player p = (Player)s;
            p.sendMessage("§4Fehler: §c/build §7[Spieler]");
            p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_BREAK,100,100);
        }
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(!(s instanceof Player p)) {
            if(args.length == 0) {
                sendSyntax(s);
            } else {
                if(Bukkit.getPlayer(args[0])!=null) {
                    Player t = Bukkit.getPlayer(args[0]);
                    API.toggleBuildMode(t);
                    Nero.getNeroServer().sendMessage("Der Build-Mode für den Spieler §e"+t.getName()+"§7 steht nun auf§8: §e"+API.canPlayerBuild(t));
                    Nero.getNeroServer().sendMessage("Dein Build-Mode steht nun auf§8: §e"+API.canPlayerBuild(t));
                } else {
                   Nero.getNeroServer().sendErrorMessage(Strings.playerNotFound());
                }
            }
        } else {
            User u = Nero.getAPI().getOnlineUser(p.getUniqueId());
            if(p.hasPermission("nero.team")) {
                if(args.length == 0) {
                    API.toggleBuildMode(p);
                    u.sendMessage("Dein Build-Mode steht nun auf§8: §e"+API.canPlayerBuild(p));
                } else {
                    Player t = Bukkit.getPlayer(args[0]);
                    User tU = Nero.getAPI().getOnlineUser(t.getUniqueId());
                    API.toggleBuildMode(t);
                    u.sendMessage("Der Build-Mode für den Spieler §e"+t.getName()+"§7 steht nun auf§8: §e"+API.canPlayerBuild(t));
                    tU.sendMessage("Dein Build-Mode steht nun auf§8: §e"+API.canPlayerBuild(t));
                }
            } else {
                u.sendErrorMessage(Strings.noPerms());
            }
        }
        return false;
    }
}