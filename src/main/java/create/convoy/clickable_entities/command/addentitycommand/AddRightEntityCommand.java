package create.convoy.clickable_entities.command.addentitycommand;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import create.convoy.clickable_entities.ClickableEntities;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;

public class AddRightEntityCommand implements Command<ServerCommandSource> {

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Entity target = EntityArgumentType.getEntity(context, "target");
        String command = StringArgumentType.getString(context, "command");

        ClickableEntities.configMap.get("right").put(target.getUuidAsString(), command);
        
       return 1;
    }
}
