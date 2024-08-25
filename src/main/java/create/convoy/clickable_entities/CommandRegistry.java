package create.convoy.clickable_entities;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;

import create.convoy.clickable_entities.command.AddEntityCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;

public class CommandRegistry {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        
        dispatcher.register(CommandManager.literal("addentity").requires(source -> source.hasPermissionLevel(3))
                .then(CommandManager.argument("left/right", StringArgumentType.word())
                .then(CommandManager.argument("target", EntityArgumentType.entity())
                .then(CommandManager.argument("command", StringArgumentType.greedyString())
                .executes(new AddEntityCommand())))));
    }
}
