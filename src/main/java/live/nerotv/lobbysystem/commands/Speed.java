package live.nerotv.lobbysystem.commands;

import live.nerotv.lobbysystem.api.API;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Speed implements CommandExecutor {

    private void sendSyntax(CommandSender s) {
        if(s instanceof Player) {
            Player p = (Player)s;
            API.sendErrorMessage(s,"§4Fehler: §c/speed [0-9/d/default] §7[Spieler]");
        } else {
            API.sendErrorMessage(s,"§4Fehler: §c/speed [0-9/d/default] [Spieler]");
        }
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Speed")) {
            if(s instanceof Player) {
                Player p = (Player)s;
                if(p.hasPermission("nero.team.speed")) {
                    if(args.length == 0) {
                        sendSyntax(p);
                    } else if(args.length == 1) {
                        if(isSpeedCompatible(args[0])) {
                            if (p.isFlying()) {
                                String Speed = "0." + args[0];
                                p.setFlySpeed(Float.parseFloat(Speed));
                                API.sendMessage(p, "Du hast deine §eFluggeschwindigkeit§7 auf §e" + args[0] + "§7 gesetzt!");
                            } else {
                                String Speed = "0." + args[0];
                                p.setWalkSpeed(Float.parseFloat(Speed));
                                API.sendMessage(p, "Du hast deine §eLaufgeschwindigkeit§7 auf §e" + args[0] + "§7 gesetzt!");
                            }
                        } else if(args[0].equalsIgnoreCase("d")||args[0].equalsIgnoreCase("default")) {
                            if (p.isFlying()) {
                                String Speed = "0.1";
                                p.setFlySpeed(Float.parseFloat(Speed));
                                API.sendMessage(p, "Du hast deine §eFluggeschwindigkeit§7 auf §eStandard§7 gesetzt!");
                            } else {
                                String Speed = "0.2";
                                p.setWalkSpeed(Float.parseFloat(Speed));
                                API.sendMessage(p, "Du hast deine §eLaufgeschwindigkeit§7 auf §eStandard§7 gesetzt!");
                            }
                        } else {
                            API.sendErrorMessage(s, "§cDas ist keine gültige Zahl!");
                        }
                    } else {
                        if(p.hasPermission("nero.team.speed.other")) {
                            if (Bukkit.getPlayer(args[1]) != null) {
                                if (isSpeedCompatible(args[0])) {
                                    Player t = Bukkit.getPlayer(args[1]);
                                    if (t.isFlying()) {
                                        String Speed = "0."+args[0];
                                        t.setFlySpeed(Float.parseFloat(Speed));
                                        API.sendMessage(s, "Du hast die §eFluggeschwindigkeit§7 von §a" + t.getName() + "§7auf §e" + args[0] + "§7 gesetzt!");
                                    } else {
                                        String Speed = "0."+args[0];
                                        t.setWalkSpeed(Float.parseFloat(Speed));
                                        API.sendMessage(s, "Du hast die §eLaufgeschwindigkeit§7 von §a" + t.getName() + "§7auf §e" + args[0] + "§7 gesetzt!");
                                    }
                                } else if(args[0].equalsIgnoreCase("d")||args[0].equalsIgnoreCase("default")) {
                                    Player t = Bukkit.getPlayer(args[1]);
                                    if (t.isFlying()) {
                                        String Speed = "0.1";
                                        t.setFlySpeed(Float.parseFloat(Speed));
                                        API.sendMessage(s, "Du hast die §eFluggeschwindigkeit§7 von §a" + t.getName() + "§7auf §eStandard§7 gesetzt!");
                                    } else {
                                        String Speed = "0.2";
                                        t.setWalkSpeed(Float.parseFloat(Speed));
                                        API.sendMessage(s, "Du hast die §eLaufgeschwindigkeit§7 von §a" + t.getName() + "§7auf §eStandard§7 gesetzt!");
                                    }
                                } else {
                                    API.sendErrorMessage(s, "§cDas ist keine gültige Zahl!");
                                }
                            } else {
                                API.sendErrorMessage(s, API.noPlayer);
                            }
                        } else {
                            API.sendErrorMessage(p,API.noPerms);
                        }
                    }
                } else {
                    API.sendErrorMessage(p,API.noPerms);
                }
            } else {
                if(args.length == 0) {
                    sendSyntax(s);
                } else if(args.length == 1) {
                    sendSyntax(s);
                } else {
                    if(Bukkit.getPlayer(args[1])!=null) {
                        if(isSpeedCompatible(args[0])) {
                            Player t = Bukkit.getPlayer(args[1]);
                            if(t.isFlying()) {
                                String Speed = "0."+args[0];
                                t.setFlySpeed(Float.parseFloat(Speed));
                                API.sendMessage(s,"Du hast die §eFluggeschwindigkeit§7 von §a"+t.getName()+"§7auf §e"+args[0]+"§7 gesetzt!");
                            } else {
                                String Speed = "0."+args[0];
                                t.setWalkSpeed(Float.parseFloat(Speed));
                                API.sendMessage(s,"Du hast die §eLaufgeschwindigkeit§7 von §a"+t.getName()+"§7auf §e"+args[0]+"§7 gesetzt!");
                            }
                        } else if(args[0].equalsIgnoreCase("d")||args[0].equalsIgnoreCase("default")) {
                            Player t = Bukkit.getPlayer(args[1]);
                            if (t.isFlying()) {
                                String Speed = "0.1";
                                t.setFlySpeed(Float.parseFloat(Speed));
                                API.sendMessage(s, "Du hast die §eFluggeschwindigkeit§7 von §a" + t.getName() + "§7auf §eStandard§7 gesetzt!");
                            } else {
                                String Speed = "0.2";
                                t.setWalkSpeed(Float.parseFloat(Speed));
                                API.sendMessage(s, "Du hast die §eLaufgeschwindigkeit§7 von §a" + t.getName() + "§7auf §eStandard§7 gesetzt!");
                            }
                        } else {
                            API.sendErrorMessage(s,"§cDas ist keine gültige Zahl!");
                        }
                    } else {
                        API.sendErrorMessage(s,API.noPlayer);
                    }
                }
            }
        }
        return false;
    }

    private boolean isSpeedCompatible(String Check) {
        if(API.isNumeric(Check)) {
            int i = Integer.parseInt(Check);
            return i >= 0 && i <= 9;
        } else {
            return false;
        }
    }
}