package live.nerotv.lobbysystem.listener;

import live.nerotv.api.paper.Nero;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class PlayerExperience implements Listener {

    @EventHandler
    public void onXP(PlayerExpChangeEvent e) {
        e.getPlayer().setExp(Nero.getAPI().getMonth()*(float)0.083);
        e.getPlayer().setLevel(Nero.getAPI().getYear());
    }
}