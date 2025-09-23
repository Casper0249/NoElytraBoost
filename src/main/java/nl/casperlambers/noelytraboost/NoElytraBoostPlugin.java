package nl.casperlambers.noelytraboost;

import com.destroystokyo.paper.event.player.PlayerElytraBoostEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class NoElytraBoostPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvent(
                PlayerElytraBoostEvent.class,
                new Listener() {},
                EventPriority.LOWEST,
                (l, e) -> ((PlayerElytraBoostEvent) e).setCancelled(true),
                this,
                true
        );
    }
}
