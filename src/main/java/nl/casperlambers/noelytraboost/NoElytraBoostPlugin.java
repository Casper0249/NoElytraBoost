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
// Does it have a noticable effect? No really, but this makes the plugin at least one nanosecond faster at runtime, except for loads and reloads, but those dont matter!
public class NoElytraBoostPlugin extends JavaPlugin {

    void reload() {
        HandlerList.unregisterAll(this);
        load();
    }

    private void load() {
        final FileConfiguration config = getConfig();

        if (!config.getBoolean("plugin-enabled")) return;

        if (config.getBoolean("send-message")) {
            getServer().getPluginManager().registerEvents(new Listener_Message(MiniMessage.miniMessage().deserialize(Objects.requireNonNull(config.getString("message")))), this);
        }

        // Determine override level
        // 0 - No override, 1 - Op override, 2 - Permission override, 3 - Both
        int overrideLevel = 0;
        if (config.getBoolean("allow-op-players")) overrideLevel = 1;
        if (config.getBoolean("allow-permitted-players")) overrideLevel = (overrideLevel == 1) ? 3 : 2;

        // Register appropriate override listener
        final PluginManager pm = getServer().getPluginManager();
        switch (overrideLevel) {
            case 0 -> pm.registerEvents(new Listener_NoOverride(), this);
            case 1 -> pm.registerEvents(new Listener_OpOverride(), this);
            case 2 -> pm.registerEvents(new Listener_PermissionOverride(), this);
            case 3 -> pm.registerEvents(new Listener_OpAndPermissionOverride(), this);
        }
    }

    @Override
    public void onEnable() {
        load();
    }
}
