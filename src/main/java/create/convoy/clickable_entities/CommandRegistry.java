package create.convoy.clickable_entities;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;

import create.convoy.clickable_entities.command.addentitycommand.AddLeftEntityCommand;
import create.convoy.clickable_entities.command.addentitycommand.AddRightEntityCommand;
import create.convoy.clickable_entities.command.removeentitycommand.RemoveLeftEntityCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;

public class CommandRegistry {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        
        dispatcher.register(CommandManager.literal("entityclick").requires(source -> source.hasPermissionLevel(3))
                .then(CommandManager.literal("remove")
                    .then(CommandManager.literal("left")
                        .then(CommandManager.argument("target", EntityArgumentType.entity())
                        .executes(new RemoveLeftEntityCommand())))
                    .then(CommandManager.literal("right")
                        .then(CommandManager.argument("target", EntityArgumentType.entity())
                        .executes(new RemoveLeftEntityCommand()))))
                .then(CommandManager.literal("add")
                    .then(CommandManager.literal("left")
                        .then(CommandManager.argument("target", EntityArgumentType.entity())
                        .then(CommandManager.argument("command", StringArgumentType.greedyString())
                        .executes(new AddLeftEntityCommand()))))
                    .then(CommandManager.literal("right")
                        .then(CommandManager.argument("target", EntityArgumentType.entity())
                        .then(CommandManager.argument("command", StringArgumentType.greedyString())
                        .executes(new AddRightEntityCommand()))))));
    }
}
