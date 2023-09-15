package live.nerotv.lobbysystem.listener;

import live.nerotv.api.paper.Nero;
import live.nerotv.lobbysystem.Main;
import live.nerotv.lobbysystem.api.API;
import live.nerotv.lobbysystem.api.ConfigAPI;
import live.nerotv.lobbysystem.api.PlayerAPI;
import live.nerotv.lobbysystem.api.WarpAPI;
import live.nerotv.lobbysystem.commands.Fly;
import live.nerotv.lobbysystem.commands.Nametags;
import live.nerotv.lobbysystem.jumper.Jumper;
import live.nerotv.lobbysystem.jumper.JumperAPI;
import live.nerotv.lobbysystem.manager.ItemManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(PlayerAPI.fishingCount(p)!=0) {
            Nero.getUser(p).addCoins(PlayerAPI.fishingCount(p));
            File TempFile = new File("Nero/Lobbysystem/temp/" + p.getUniqueId().toString() + "_lobbysettings.temp");
            YamlConfiguration TF = YamlConfiguration.loadConfiguration(TempFile);
            TF.set("Stats.FishingCount",0);
            ConfigAPI.saveConfig(TempFile,TF);
            ConfigAPI.reloadConfig(TempFile,TF);
        }
        e.setJoinMessage(null);
        Nametags.noRenew.remove(p);
        p.setOp(false);
        API.checkRotating();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"deop "+p.getName());
        p.setFlying(false);
        p.setAllowFlight(false);
        Fly.flyPlayers.remove(p);
        PlayerAPI.renewScoreboard(p,false);
        p.setGameMode(GameMode.ADVENTURE);
        for(Player all : Bukkit.getOnlinePlayers()) {
            Main.setPrefix(all);
        }
        if(API.canPlayerBuild(p)) {
            API.setBuildMode(p,false);
        }
        if(WarpAPI.isWarpEnabled("Spawn")) {
            p.teleport(WarpAPI.getWarp("Spawn"));
        } else if(Bukkit.getWorlds().get(0)!=null) {
            p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
        }
        ItemManager.giveItems(p);
        for(Player all:Bukkit.getOnlinePlayers()) {
            if(all.getUniqueId()!=p.getUniqueId()) {
                all.sendMessage("§8» §a"+p.getName());
            }
        }
        p.setInvulnerable(true);
        API.setTablist();
        API.getPlayerTime(p);
        API.getPlayerWeather(p);
        Component test = MiniMessage.miniMessage().deserialize("\n<gradient:#3696ff:#36ffff:#3696ff>a.nerotv.live\n<gray>Minecraft <dark_gray>-<gray> aber <white>mehr<dark_gray>!\n<gray>Server <dark_gray>● <aqua>Lobby-1\n");
        p.sendPlayerListHeader(test);
        if(JumperAPI.Jumpers.containsKey(p)) {
            Jumper j = JumperAPI.Jumpers.get(p);
            j.fail();
        }
        API.users.getCFG().set("Users."+p.getUniqueId().toString(),p.getName());
        API.users.getCFG().set("Users."+p.getName(),p.getUniqueId().toString());
        API.users.saveConfig();
    }
}