package live.nerotv.lobbysystem.listener;

import live.nerotv.lobbysystem.api.API;
import live.nerotv.lobbysystem.commands.Fly;
import live.nerotv.lobbysystem.manager.ItemManager;
import live.nerotv.lobbysystem.pixel.PixelAPI;
import live.nerotv.lobbysystem.utils.LabyModUtils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.inventory.Inventory;

public class PlayerWorld implements Listener {

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        boolean lobby = true;
        Player p = e.getPlayer();
        if(p.getWorld().getName().equals("Primal2")) {
            API.getPlayerTime(p);
            API.getPlayerWeather(p);
            p.setAllowFlight(true);
            if(!Fly.flyPlayers.contains(p)) {
                Fly.flyPlayers.add(p);
            }
            p.setFlying(true);
        } else if(p.getWorld().getName().equals("Primal3")) {
                API.getPlayerTime(p);
                API.getPlayerWeather(p);
                p.setAllowFlight(true);
            if(!Fly.flyPlayers.contains(p)) {
                Fly.flyPlayers.add(p);
            }
                p.setFlying(true);
        } else if(p.getWorld().getName().contains("pixels-")) {
            p.resetPlayerTime();
            p.resetPlayerWeather();
            p.getWorld().setTime(6000);
            p.setAllowFlight(true);
            if(!Fly.flyPlayers.contains(p)) {
                Fly.flyPlayers.add(p);
            }
            p.setFlying(true);
        } else if(p.getWorld().getName().equals("Argria1")) {
            API.getPlayerTime(p);
            API.getPlayerWeather(p);
            p.setAllowFlight(true);
            if(!Fly.flyPlayers.contains(p)) {
                Fly.flyPlayers.add(p);
            }
            p.setFlying(true);
        } else if(p.getWorld().getName().equals("Deadwood")) {
            API.getPlayerTime(p);
            API.getPlayerWeather(p);
            p.setAllowFlight(true);
            if(!Fly.flyPlayers.contains(p)) {
                Fly.flyPlayers.add(p);
            }
            p.setFlying(true);
        } else if(p.getWorld().getName().equals("Shervann")) {
            API.getPlayerTime(p);
            API.getPlayerWeather(p);
            p.setAllowFlight(true);
            if(!Fly.flyPlayers.contains(p)) {
                Fly.flyPlayers.add(p);
            }
            p.setFlying(true);
        } else if(p.getWorld().getName().equals("pixels")) {
            LabyModUtils.sendCurrentPlayingGamemode(p,true,"Pixels");
            lobby = false;
            p.resetPlayerTime();
            p.resetPlayerWeather();
            ItemManager.giveItems(p);
            p.setAllowFlight(true);
            if(!Fly.flyPlayers.contains(p)) {
                Fly.flyPlayers.add(p);
            }
            p.setFlying(true);
            Inventory Inv = p.getInventory();
            Inv.clear();
            p.setGameMode(GameMode.SURVIVAL);
            p.setAllowFlight(true);
            if(!Fly.flyPlayers.contains(p)) {
                Fly.flyPlayers.add(p);
            }
            p.setFlying(true);

            PixelAPI.givePixelItems(p);



            API.sendMessage(p,"§7Um zurückzukehren, gebe §e/back §8(oder einen anderen Befehl)§7 ein§8.");
        } else {
            API.getPlayerTime(p);
            API.getPlayerWeather(p);
            ItemManager.giveItems(p);
            p.setFlying(false);
            Fly.flyPlayers.remove(p);
            p.setAllowFlight(false);
        }
        if(lobby) {
            LabyModUtils.sendCurrentPlayingGamemode(p,true,"Lobby");
        }
    }

    @EventHandler
    public void onExplosion(ExplosionPrimeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onIce(BlockFadeEvent e) {
        if(e.getNewState().getType().equals(Material.ICE)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onIce(BlockFormEvent e) {
        if(e.getNewState().getType().equals(Material.ICE)) {
            e.setCancelled(true);
        }
    }
}