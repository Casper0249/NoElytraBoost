package nl.casperlambers.noelytraboost.paper;

import com.destroystokyo.paper.event.player.PlayerElytraBoostEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoElytraBoost extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            private void onBoost(PlayerElytraBoostEvent event) {
                event.setCancelled(true);
            }
        }, this);
    }
}
