package live.nerotv.lobbysystem.commands;

import live.nerotv.api.paper.Nero;
import live.nerotv.api.paper.utils.user.User;
import live.nerotv.api.utils.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SRL implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (s.hasPermission("nero.leading")) {
            if (s instanceof Player p) {
                User u = Nero.getAPI().getOnlineUser(p.getUniqueId());
                u.sendMessage("§7Du hast den §aStopvorgang §7gestartet§8...");
            } else {
                s.sendMessage(Strings.prefix() + "§7Du hast den §aStopvorgang §7gestartet§8...");
            }
            Nero.getNeroServer().stopServer();
        } else {
            if (s instanceof Player p) {
                User u = Nero.getAPI().getOnlineUser(p.getUniqueId());
                u.sendErrorMessage(Strings.noPerms());
            } else {
                s.sendMessage(Strings.noPerms());
            }
        }
        return false;
    }
}
