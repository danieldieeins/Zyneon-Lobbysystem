package live.nerotv.lobbysystem.commands;

import live.nerotv.api.paper.Nero;
import live.nerotv.api.paper.utils.user.User;
import live.nerotv.api.utils.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GameMode implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("GameMode")) {
            if(s instanceof Player p) {
                User u = Nero.getAPI().getOnlineUser(p.getUniqueId());
                if(p.hasPermission("nero.team")) {
                    if (args.length == 0) {
                        u.sendErrorMessage("§4Fehler: §c/gamemode [Spielmodus] §7(Spieler)");
                    } else {
                        if(args[0].equalsIgnoreCase("0")||args[0].equalsIgnoreCase("survival")) {
                            if(args.length == 1) {
                                u.getPlayer().setGameMode(org.bukkit.GameMode.SURVIVAL);
                                u.sendMessage("§7Du bist nun im §eSurvival§8-§eModus§8!");
                            } else {
                                if(Bukkit.getPlayer(args[1])!=null) {
                                    User t = Nero.getAPI().getOnlineUser(p.getUniqueId());
                                    t.getPlayer().setGameMode(org.bukkit.GameMode.SURVIVAL);
                                    t.sendMessage("§7Du bist nun im §eSurvival§8-§eModus§8!");
                                    u.sendMessage("§7Du hast §a"+t.getPlayer().getName()+"§7 in den §eSurvival§8-§eModus§7 gesetzt§8!");
                                } else {
                                    u.sendErrorMessage(Strings.playerNotFound(args[1]));
                                }
                            }
                        } else if(args[0].equalsIgnoreCase("1")||args[0].equalsIgnoreCase("creative")) {
                            if(args.length == 1) {
                                u.getPlayer().setGameMode(org.bukkit.GameMode.CREATIVE);
                                u.sendMessage("§7Du bist nun im §eCreative§8-§eModus§8!");
                            } else {
                                if(Bukkit.getPlayer(args[1])!=null) {
                                    User t = Nero.getAPI().getOnlineUser(p.getUniqueId());
                                    t.getPlayer().setGameMode(org.bukkit.GameMode.CREATIVE);
                                    t.sendMessage("§7Du bist nun im §eCreative§8-§eModus§8!");
                                    u.sendMessage("§7Du hast §a"+t.getPlayer().getName()+"§7 in den §eCreative§8-§eModus§7 gesetzt§8!");
                                } else {
                                    u.sendErrorMessage(Strings.playerNotFound(args[1]));
                                }
                            }
                        } else if(args[0].equalsIgnoreCase("2")||args[0].equalsIgnoreCase("adventure")) {
                            if(args.length == 1) {
                                u.getPlayer().setGameMode(org.bukkit.GameMode.ADVENTURE);
                                u.sendMessage("§7Du bist nun im §eAdventure§8-§eModus§8!");
                            } else {
                                if(Bukkit.getPlayer(args[1])!=null) {
                                    User t = Nero.getAPI().getOnlineUser(p.getUniqueId());
                                    t.getPlayer().setGameMode(org.bukkit.GameMode.ADVENTURE);
                                    t.sendMessage("§7Du bist nun im §eAdventure§8-§eModus§8!");
                                    u.sendMessage("§7Du hast §a"+t.getPlayer().getName()+"§7 in den §eAdventure§8-§eModus§7 gesetzt§8!");
                                } else {
                                    u.sendErrorMessage(Strings.playerNotFound(args[1]));
                                }
                            }
                        } else if(args[0].equalsIgnoreCase("3")||args[0].equalsIgnoreCase("spectator")) {
                            if(args.length == 1) {
                                u.getPlayer().setGameMode(org.bukkit.GameMode.SPECTATOR);
                                u.sendMessage("§7Du bist nun im §eSpectator§8-§eModus§8!");
                            } else {
                                if(Bukkit.getPlayer(args[1])!=null) {
                                    User t = Nero.getAPI().getOnlineUser(p.getUniqueId());
                                    t.getPlayer().setGameMode(org.bukkit.GameMode.SPECTATOR);
                                    t.sendMessage("§7Du bist nun im §eSpectator§8-§eModus§8!");
                                    u.sendMessage("§7Du hast §a"+t.getPlayer().getName()+"§7 in den §eSpectator§8-§eModus§7 gesetzt§8!");
                                } else {
                                    u.sendErrorMessage(Strings.playerNotFound(args[1]));
                                }
                            }
                        } else {
                            u.sendErrorMessage("§4Fehler: §cDer Spielmodus §4"+args[0]+"§c existiert nicht§8! §cVersuche 0, 1, 2, 3, survival, creative, adventure oder spectator§8.");
                        }
                    }
                } else {
                    u.sendErrorMessage(Strings.noPerms());
                }
            } else {
                if (s.hasPermission("nero.team")) {
                    if (args.length == 2) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            User t = Nero.getAPI().getOnlineUser(Bukkit.getPlayer(args[1]).getUniqueId());
                            if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                                t.getPlayer().setGameMode(org.bukkit.GameMode.SURVIVAL);
                                t.sendMessage("§7Du bist nun im §eSurvival§8-§eModus§8!");
                            } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                                t.getPlayer().setGameMode(org.bukkit.GameMode.CREATIVE);
                                t.sendMessage("§7Du bist nun im §eCreative§8-§eModus§8!");
                            } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                                t.getPlayer().setGameMode(org.bukkit.GameMode.ADVENTURE);
                                t.sendMessage("§7Du bist nun im §eAdventure§8-§eModus§8!");
                            } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                                t.getPlayer().setGameMode(org.bukkit.GameMode.SPECTATOR);
                                t.sendMessage("§7Du bist nun im §eSpectator§8-§eModus§8!");
                            } else {
                                s.sendMessage("§4Fehler: §cDer Spielmodus §4" + args[0] + "§c existiert nicht§8! §cVersuche 0, 1, 2, 3, survival, creative, adventure oder spectator§8.");
                                return false;
                            }
                            s.sendMessage(Strings.prefix()+"§7Du hast den Spieler §a"+t.getPlayer().getName()+"§7 in den Spielmodus §e"+args[0]+"§7 gesetzt§8!");
                        } else {
                            s.sendMessage(Strings.playerNotFound(args[1]));
                        }
                    } else {
                        s.sendMessage("§4Fehler: §c/gamemode [Spielmodus] [Spieler]");
                    }
                } else {
                    s.sendMessage(Strings.noPerms());
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completer = new ArrayList<>();
        if(args.length == 1) {
            completer.add("survival");
            completer.add("creative");
            completer.add("adventure");
            completer.add("spectator");
            completer.add("0");
            completer.add("1");
            completer.add("2");
            completer.add("3");
        } else if(args.length == 2) {
            for(Player all:Bukkit.getOnlinePlayers()) {
                completer.add(all.getName());
            }
        }
        return completer;
    }
}