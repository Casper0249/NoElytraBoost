package nl.casperlambers.noelytraboost.listener;

import com.destroystokyo.paper.event.player.PlayerElytraBoostEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class Listener_Message implements Listener {
    private final Component message;

    public Listener_Message(Component message) {
        this.message = message;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void sendMessage(PlayerElytraBoostEvent event) {
        if (!event.isCancelled()) return;
        event.getPlayer().sendMessage(message);
    }
}
