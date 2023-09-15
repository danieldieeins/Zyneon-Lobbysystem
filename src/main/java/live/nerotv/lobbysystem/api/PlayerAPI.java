package live.nerotv.lobbysystem.api;

import live.nerotv.api.paper.Nero;
import live.nerotv.lobbysystem.Main;
import live.nerotv.lobbysystem.commands.Nametags;
import live.nerotv.lobbysystem.jumper.Jumper;
import live.nerotv.lobbysystem.jumper.JumperAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.io.File;

public class PlayerAPI {

    public static void updateFishingCount(Player player,int i) {
        Nero.getUser(player).addCoins(i);
    }

    public static boolean hasAnimations(Player player) {
        File TempFile = new File("Nero/Lobbysystem/temp/"+player.getUniqueId().toString()+"_lobbysettings.temp");
        YamlConfiguration TF = YamlConfiguration.loadConfiguration(TempFile);
        if(TempFile.exists()) {
            if(TF.contains("Settings.Animations")) {
                return TF.getBoolean("Settings.Animations");
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static int fishingCount(Player player) {
        File TempFile = new File("Nero/Lobbysystem/temp/" + player.getUniqueId().toString() + "_lobbysettings.temp");
        YamlConfiguration TF = YamlConfiguration.loadConfiguration(TempFile);
        if(TempFile.exists()) {
            if(TF.contains("Stats.FishingCount")) {
                return TF.getInt("Stats.FishingCount");
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static String rankName(Player player) {
        if(player.hasPermission("nero.leading")) {
            return "Leitung";
        } else if(player.hasPermission("nero.team")) {
            return "Team";
        } else if(player.hasPermission("nero.creator")) {
            return "Creator";
        } else if(player.hasPermission("nero.premium")) {
            return "Premium";
        } else {
            return "Spieler";
        }
    }

    public static void setScoreboard(Player player,boolean renew) {
        if(JumperAPI.Jumpers.containsKey(player)) {
            Jumper j = JumperAPI.Jumpers.get(player);
            Scoreboard board = player.getScoreboard();
            Objective boardContent = board.getObjective("object");
            board.resetScores("object");
            boardContent.setDisplayName(API.animatedJumper());
            Score placeholder0 = boardContent.getScore("§0");
            Score topList00 = boardContent.getScore("§f§lJumper§7§l-§f§lToplist§8§l:");
            Score topList01 = boardContent.getScore("§e" + JumperAPI.topList.get(0) + "§8: §a" + JumperAPI.getHighscore(Bukkit.getOfflinePlayer(JumperAPI.topList.get(0)).getUniqueId()));
            Score topList02 = boardContent.getScore("§e" + JumperAPI.topList.get(1) + "§8: §a" + JumperAPI.getHighscore(Bukkit.getOfflinePlayer(JumperAPI.topList.get(1)).getUniqueId()));
            Score topList03 = boardContent.getScore("§e" + JumperAPI.topList.get(2) + "§8: §a" + JumperAPI.getHighscore(Bukkit.getOfflinePlayer(JumperAPI.topList.get(2)).getUniqueId()));
            Score topList04 = boardContent.getScore("§e" + JumperAPI.topList.get(3) + "§8: §a" + JumperAPI.getHighscore(Bukkit.getOfflinePlayer(JumperAPI.topList.get(3)).getUniqueId()));
            Score topList05 = boardContent.getScore("§e" + JumperAPI.topList.get(4) + "§8: §a" + JumperAPI.getHighscore(Bukkit.getOfflinePlayer(JumperAPI.topList.get(4)).getUniqueId()));
            Score topList06 = boardContent.getScore("§e" + JumperAPI.topList.get(5) + "§8: §a" + JumperAPI.getHighscore(Bukkit.getOfflinePlayer(JumperAPI.topList.get(5)).getUniqueId()));
            Score topList07 = boardContent.getScore("§7[§8...§7]§");
            Score placeholder1 = boardContent.getScore("§1");
            Score highscoreTitle = boardContent.getScore("§f§lJumper§7§l-§f§lHighscore§8§l:");
            Score highscore = boardContent.getScore("§e"+j.getHighscore());
            Score placeholder2 = boardContent.getScore("§2");
            Score streakTitle = boardContent.getScore("§f§lServer§7§l-§f§lIP§8§l:");
            Score streak = boardContent.getScore("§ea.nerotv.live");

            placeholder0.setScore(15);
            topList00.setScore(14);
            if(!topList01.isScoreSet()) {
                topList01.setScore(13);
            }
            if(!topList02.isScoreSet()) {
                topList02.setScore(12);
            }
            if(!topList03.isScoreSet()) {
                topList03.setScore(11);
            }
            if(!topList04.isScoreSet()) {
                topList04.setScore(10);
            }
            if(!topList05.isScoreSet()) {
                topList05.setScore(9);
            }
            if(!topList06.isScoreSet()) {
                topList06.setScore(8);
            }
            topList07.setScore(7);
            placeholder1.setScore(6);
            highscoreTitle.setScore(5);
            highscore.setScore(4);
            placeholder2.setScore(3);
            streakTitle.setScore(2);
            streak.setScore(1);

            Main.setPrefix(player);
        } else {
            if (!Nametags.noRenew.contains(player)) {
                Scoreboard board = player.getScoreboard();
                boolean hasScoreboard = true;
                File TempFile = new File("Nero/Lobbysystem/temp/" + player.getUniqueId().toString() + "_lobbysettings.temp");
                YamlConfiguration TF = YamlConfiguration.loadConfiguration(TempFile);
                if (TempFile.exists()) {
                    if (TF.contains("Settings.Scoreboard")) {
                        hasScoreboard = TF.getBoolean("Settings.Scoreboard");
                    }
                }
                if (hasScoreboard) {
                    Objective boardContent = board.getObjective("object");
                    board.resetScores("object");
                    Score object = boardContent.getScore("§fServer§7-§fIP§8:");
                    boardContent.setDisplayName(API.animatedString());
                    Score objectContent = boardContent.getScore("§ea.nerotv.live");
                    Score placeholder0 = boardContent.getScore("§0");
                    Score placeholder1 = boardContent.getScore("§1");
                    Score placeholder2 = boardContent.getScore("§2");
                    Score placeholder3 = boardContent.getScore("§3");
                    Score placeholder4 = boardContent.getScore("§4");
                    Score project = boardContent.getScore("§fProjekt§8:");
                    Score projectContent = boardContent.getScore("§e" + ConfigAPI.CFG.getString("Core.Compass.Names.Item01".replace("&", "§")));
                    Score rank = boardContent.getScore("§fRang§8:");
                    Score rankContent = boardContent.getScore("§e" + rankName(player));
                    Score time = boardContent.getScore("§fZeit§8:");
                    Score timeContent = boardContent.getScore("§e" + API.date);

                    Score jumperCount = boardContent.getScore("§fJumper§7-§fScore§8:");
                    Score jumperContent = boardContent.getScore("§e" + JumperAPI.getHighscore(player.getUniqueId()));
                    Score fishingCount = boardContent.getScore("§fCoins§8:");
                    Score fishingContent = boardContent.getScore("§e" + Nero.getUser(player).getCoins());

                    placeholder4.setScore(15);
                    jumperCount.setScore(14);
                    jumperContent.setScore(13);
                    placeholder0.setScore(12);
                    fishingCount.setScore(11);
                    fishingContent.setScore(10);
                    placeholder1.setScore(9);
                    time.setScore(8);
                    timeContent.setScore(7);
                    placeholder2.setScore(6);
                    rank.setScore(5);
                    rankContent.setScore(4);
                    placeholder3.setScore(3);
                    object.setScore(2);
                    objectContent.setScore(1);
                }
            }
            Main.setPrefix(player);
        }
    }

    public static void renewScoreboard(Player player,boolean switchScore) {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        player.setScoreboard(sm.getNewScoreboard());
        boolean hasScoreboard = true;
        File TempFile = new File("Nero/Lobbysystem/temp/" + player.getUniqueId().toString() + "_lobbysettings.temp");
        YamlConfiguration TF = YamlConfiguration.loadConfiguration(TempFile);
        if(TempFile.exists()) {
            if(TF.contains("Settings.Scoreboard")) {
                hasScoreboard = TF.getBoolean("Settings.Scoreboard");
            }
        }
        Scoreboard board = player.getScoreboard();
        if(hasScoreboard&&!Nametags.noRenew.contains(player)) {
            if (board.getObjective("object") == null) {
                board.registerNewObjective("object", "object");
            }
            Objective boardContent = board.getObjective("object");
            boardContent.setDisplaySlot(DisplaySlot.SIDEBAR);
        }
        setScoreboard(player,true);
    }
}