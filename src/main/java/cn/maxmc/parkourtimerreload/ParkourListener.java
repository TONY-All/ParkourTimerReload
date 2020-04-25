package cn.maxmc.parkourtimerreload;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ParkourListener implements Listener {
    /**
     * on PlayerJoin, setup {@link Timer} For Player
     * Timer will added to {@link ParkourManager#setupPlayer}
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        ParkourTimerReload.getPm().setupPlayer(p);
    }

    /**
     * on PlayerQuit, Clear {@link Timer} For Player
     * Timer will cleaned at {@link ParkourManager#setupPlayer}
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        ParkourTimerReload.getPm().resetPlayer(p);
    }
}
