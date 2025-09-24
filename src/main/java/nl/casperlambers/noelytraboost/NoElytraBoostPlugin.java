package nl.casperlambers.noelytraboost;

import net.kyori.adventure.text.minimessage.MiniMessage;
import nl.casperlambers.noelytraboost.listener.Listener_Message;
import nl.casperlambers.noelytraboost.listener.cancellation.Listener_NoOverride;
import nl.casperlambers.noelytraboost.listener.cancellation.Listener_OpAndPermissionOverride;
import nl.casperlambers.noelytraboost.listener.cancellation.Listener_OpOverride;
import nl.casperlambers.noelytraboost.listener.cancellation.Listener_PermissionOverride;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

// Seperate listeners because runtime performance and micro-optimizations are favoured over code readability
// Does it have a meaningful effect? Probably not, but this makes the plugin at least one nanosecond faster at runtime, except for loads and reloads, but those dont matter!
public class NoElytraBoostPlugin extends JavaPlugin {

    void reload() {
        Listener_Message.message = null;

        HandlerList.unregisterAll(new Listener_Message());
        HandlerList.unregisterAll(new Listener_NoOverride());
        HandlerList.unregisterAll(new Listener_OpOverride());
        HandlerList.unregisterAll(new Listener_PermissionOverride());
        HandlerList.unregisterAll(new Listener_OpAndPermissionOverride());

        load();
    }

    private void load() {
        final FileConfiguration config = getConfig();

        if (!config.getBoolean("plugin-enabled")) return;

        if (config.getBoolean("send-message")) {
            Listener_Message.message = MiniMessage.miniMessage().deserialize(Objects.requireNonNull(config.getString("message")));
            getServer().getPluginManager().registerEvents(new Listener_Message(), this);
        }

        // 0 - No override
        // 1 - Op override
        // 2 - Permission override
        // 3 - Both override
        byte overrideLevel = 0;

        if (config.getBoolean("allow-op-players")) {
            overrideLevel = 1;
        }

        if (config.getBoolean("allow-permitted-players")) {
            if (overrideLevel == 1) overrideLevel = 3;
            else overrideLevel = 2;
        }

        final PluginManager pluginManager = getServer().getPluginManager();

        switch(overrideLevel) {
            case 0:
                pluginManager.registerEvents(new Listener_NoOverride(),this );
                break;
            case 1:
                pluginManager.registerEvents(new Listener_OpOverride(), this);
                break;
            case 2:
                pluginManager.registerEvents(new Listener_PermissionOverride(), this);
                break;
            case 3:
                pluginManager.registerEvents(new Listener_OpAndPermissionOverride(), this);
                break;
        }
    }

    @Override
    public void onEnable() {
        load();
    }
}
