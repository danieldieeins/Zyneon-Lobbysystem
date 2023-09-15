package live.nerotv.lobbysystem.commands;

import live.nerotv.lobbysystem.api.API;
import live.nerotv.lobbysystem.api.WorldAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Weather implements CommandExecutor {

    private void sendSyntax(CommandSender s) {
        if(!(s instanceof Player)) {
            s.sendMessage("§4Fehler: §c/weather [sun/rain/thunder] [Welt]");
        } else {
            API.sendErrorMessage(s,"§4Fehler: §c/weather [sun/rain/thunder] §7[Welt]");
        }
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Weather")) {
            if(!(s instanceof Player)) {
                if(args.length >= 2) {
                    if(Bukkit.getWorld(args[1]) != null) {
                        World world = Bukkit.getWorld(args[1]);
                        if(args[0].equalsIgnoreCase("sun")) {
                            WorldAPI.setSun(world);
                            API.sendMessage(s, "Das Wetter der Welt §e" + world.getName() + "§7 steht nun auf §eSonnig§7!");
                        } else if(args[0].equalsIgnoreCase("clear")) {
                            WorldAPI.setSun(world);
                            API.sendMessage(s,"Das Wetter der Welt §e"+world.getName()+"§7 steht nun auf §eSonnig§7!");
                        } else if(args[0].equalsIgnoreCase("rain")) {
                            WorldAPI.setRain(world);
                            API.sendMessage(s,"Das Wetter der Welt §e"+world.getName()+"§7 steht nun auf §eRegen§7!");
                        } else if(args[0].equalsIgnoreCase("thunder")) {
                            WorldAPI.setStorm(world);
                            API.sendMessage(s,"Das Wetter der Welt §e"+world.getName()+"§7 steht nun auf §eGewitter§7!");
                        } else if(args[0].equalsIgnoreCase("storm")) {
                            WorldAPI.setStorm(world);
                            API.sendMessage(s,"Das Wetter der Welt §e"+world.getName()+"§7 steht nun auf §eGewitter§7!");
                        } else {
                            sendSyntax(s);
                        }
                    } else {
                        s.sendMessage("§cDie Welt §4"+args[1]+"§c existiert nicht!");
                    }
                } else {
                    sendSyntax(s);
                }
            } else {
                Player p = (Player)s;
                if(p.hasPermission("nero.team")) {
                    if(args.length == 0) {
                        sendSyntax(p);
                    } else if(args.length == 1) {
                        if(args[0].equalsIgnoreCase("sun")) {
                            WorldAPI.setSun(WorldAPI.getPlayerWorld(p));
                            API.sendMessage(p, "Das Wetter steht nun auf §eSonnig§7!");
                        } else if(args[0].equalsIgnoreCase("clear")) {
                            WorldAPI.setSun(WorldAPI.getPlayerWorld(p));
                            API.sendMessage(p,"Das Wetter steht nun auf §eSonnig§7!");
                        } else if(args[0].equalsIgnoreCase("rain")) {
                            WorldAPI.setRain(WorldAPI.getPlayerWorld(p));
                            API.sendMessage(p,"Das Wetter steht nun auf §eRegen§7!");
                        } else if(args[0].equalsIgnoreCase("thunder")) {
                            WorldAPI.setStorm(WorldAPI.getPlayerWorld(p));
                            API.sendMessage(p,"Das Wetter steht nun auf §eGewitter§7!");
                        } else if(args[0].equalsIgnoreCase("storm")) {
                            WorldAPI.setStorm(WorldAPI.getPlayerWorld(p));
                            API.sendMessage(p,"Das Wetter steht nun auf §eGewitter§7!");
                        } else {
                            sendSyntax(p);
                        }
                    } else {
                        if(Bukkit.getWorld(args[1]) != null) {
                            World world = Bukkit.getWorld(args[1]);
                            if(args[0].equalsIgnoreCase("sun")) {
                                WorldAPI.setSun(world);
                                API.sendMessage(p, "Das Wetter der Welt §e" + world.getName() + "§7 steht nun auf §eSonnig§7!");
                            } else if(args[0].equalsIgnoreCase("clear")) {
                                    WorldAPI.setSun(world);
                                    API.sendMessage(p,"Das Wetter der Welt §e"+world.getName()+"§7 steht nun auf §eSonnig§7!");
                            } else if(args[0].equalsIgnoreCase("rain")) {
                                WorldAPI.setRain(world);
                                API.sendMessage(p,"Das Wetter der Welt §e"+world.getName()+"§7 steht nun auf §eRegen§7!");
                            } else if(args[0].equalsIgnoreCase("thunder")) {
                                WorldAPI.setStorm(world);
                                API.sendMessage(p,"Das Wetter der Welt §e"+world.getName()+"§7 steht nun auf §eGewitter§7!");
                            } else if(args[0].equalsIgnoreCase("storm")) {
                                WorldAPI.setStorm(world);
                                API.sendMessage(p,"Das Wetter der Welt §e"+world.getName()+"§7 steht nun auf §eGewitter§7!");
                            } else {
                                sendSyntax(p);
                            }
                        } else {
                            API.sendErrorMessage(p,"§cDie Welt §4"+args[1]+"§c existiert nicht!");
                        }
                    }
                } else {
                    API.sendErrorMessage(p,API.noPerms);
                }
            }
        }
        return false;
    }
}