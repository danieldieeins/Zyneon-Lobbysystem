package live.nerotv.lobbysystem.commands;

import live.nerotv.lobbysystem.api.API;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ping implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Ping")) {
            if(!(s instanceof Player)) {
                API.sendMessage(s,"Du hast einen Ping von §a0ms§7!");
            } else {
                Player p = (Player)s;
                if(p.getUniqueId().toString().replace("-","").equals("b609a4b23e6e4f46ae792d8f8404075c")) {
                    int Ping = API.getPing(p);
                    String ping;
                    if (Ping < 10) {
                        ping = "§5" + Ping;
                    } else if (Ping < 20) {
                        ping = "§d" + Ping;
                    } else if (Ping < 30) {
                        ping = "§e" + Ping;
                    } else if (Ping < 40) {
                        ping = "§6" + Ping;
                    } else if (Ping < 100) {
                        ping = "§c" + Ping;
                    } else {
                        ping = "§4" + Ping;
                    }
                    API.sendMessage(s, "Du hast einen Ping von " + ping + "ms§7!");
                } else {
                    int Ping = API.getPing(p);
                    String ping;
                    if(Ping < 10) {
                        ping = "§a"+Ping;
                    } else if(Ping < 20) {
                        ping = "§2"+Ping;
                    } else if(Ping < 30) {
                        ping = "§e"+Ping;
                    } else if(Ping < 40) {
                        ping = "§6"+Ping;
                    } else if(Ping < 100) {
                        ping = "§c"+Ping;
                    } else {
                        ping = "§4"+Ping;
                    }
                    API.sendMessage(s,"Du hast einen Ping von "+ping+"ms§7!");
                }
            }
        }
        return false;
    }
}