package create.convoy.clickable_entities.command.removeentitycommand;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import create.convoy.clickable_entities.ClickableEntities;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;

public class RemoveLeftEntityCommand implements Command<ServerCommandSource> {

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Entity target = EntityArgumentType.getEntity(context, "target");

        ClickableEntities.configMap.get("left").remove(target.getUuidAsString());
        
        return 1;
    }
}
