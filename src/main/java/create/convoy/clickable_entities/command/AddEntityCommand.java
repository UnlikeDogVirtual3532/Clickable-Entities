package create.convoy.clickable_entities.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import create.convoy.clickable_entities.ClickableEntities;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class AddEntityCommand implements Command<ServerCommandSource> {

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        String hand = StringArgumentType.getString(context, "left/right");
        Entity target = EntityArgumentType.getEntity(context, "target");
        String command = StringArgumentType.getString(context, "command");

        context.getSource().getPlayer().sendMessage(Text.literal(target.getUuidAsString()));

        ClickableEntities.configMap.get(hand).put(target.getUuidAsString(), command);
        
       return 1;
    }
}
