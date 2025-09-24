package nl.casperlambers.noelytraboost;


import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class Command_noelytraboost {
    public LiteralCommandNode<CommandSourceStack> command() {
        return Commands.literal("noelytraboost")
                .executes(this::main)
                .then(
                        Commands.literal("reload")
                                .executes(this::reload)
                ).build();
    }

    private int reload(CommandContext<CommandSourceStack> commandSourceStackCommandContext) {
        NoElytraBoostPlugin.getPlugin(NoElytraBoostPlugin.class).reload();
        commandSourceStackCommandContext.getSource().getSender().sendMessage(Component.text("NoElytraBoost has been reloaded", NamedTextColor.AQUA));
        return 1;
    }

    private int main(CommandContext<CommandSourceStack> commandSourceStackCommandContext) {
        commandSourceStackCommandContext.getSource().getSender().sendMessage(
                Component.text("NoElytraBoost", NamedTextColor.AQUA)
                        .append(Component.text(" version 1.0", NamedTextColor.WHITE))
        );
        return 1;
    }
}
