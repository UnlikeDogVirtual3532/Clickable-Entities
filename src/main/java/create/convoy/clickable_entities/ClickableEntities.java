package create.convoy.clickable_entities;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import create.convoy.clickable_entities.config.ConfigManager;

import java.util.Map;

public class ClickableEntities implements ModInitializer {
	public static final String MOD_NAME = "Clickable Entities";
	public static final String MOD_ID = "clickable-entities";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static String configFileLocation = "config/clickable-entities/";
    public static String configFileName = "RegisteredEntities.yaml";
    public static String defaultConfigFilePath = "assets/clickable-entities/default_config.yaml";
    public static Map<String, Map<String, String>> configMap;

	@Override
	public void onInitialize() {
		ConfigManager configManager = new ConfigManager(configFileLocation, configFileName, defaultConfigFilePath);

		ServerLifecycleEvents.SERVER_STARTING.register((MinecraftServer server) -> {
			try {
				configMap = configManager.getConfig();
			} catch (Exception e) {
				configMap = configManager.getDefaultConfig();
			}
	
			if (configMap == null) {configMap = configManager.getDefaultConfig();}
		});

		ServerLifecycleEvents.BEFORE_SAVE.register((MinecraftServer server, boolean flush, boolean force) -> {
			configManager.saveConfig(configMap);
		});

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAcces, enviroment) -> {
			CommandRegistry.register(dispatcher);
		});

		UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (world.isClient) {
                return ActionResult.PASS;
            }

			if (configMap.get("right").containsKey(entity.getUuidAsString())) {
				world.getServer().getCommandManager().executeWithPrefix(world.getServer().getCommandSource(), (String) configMap.get("right").get(entity.getUuidAsString()));
				return ActionResult.SUCCESS;
			}

			return ActionResult.PASS;
		});

		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (world.isClient) {
				return ActionResult.PASS;
			}

			if (configMap.get("left").containsKey(entity.getUuidAsString())) {
				world.getServer().getCommandManager().executeWithPrefix(world.getServer().getCommandSource(), (String) configMap.get("left").get(entity.getUuidAsString()));
				return ActionResult.SUCCESS;
			}

			return ActionResult.PASS;
		});
	}
}