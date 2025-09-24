package nl.casperlambers.noelytraboost.listener.cancellation;

import com.destroystokyo.paper.event.player.PlayerElytraBoostEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class Listener_OpOverride implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void sendMessage(PlayerElytraBoostEvent event) {
        if (event.getPlayer().isOp()) return;
        event.setCancelled(true);
    }
}
