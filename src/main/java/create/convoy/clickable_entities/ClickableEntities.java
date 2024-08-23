package create.convoy.clickable_entities;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import create.convoy.clickable_entities.config.ConfigManager;

import java.util.Map;

public class ClickableEntities implements ModInitializer {
	public static final String MOD_NAME = "Clickable Entities";
	public static final String MOD_ID = "clickable-entities";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static String configFileLocation = "config/clickable-entities/";
    public static String configFileName = "ClickableEntities.yaml";
    public static String defaultConfigFilePath = "assets/clickable-entities/default_config.yaml";
    public static Map<String, Object> configMap;

	@Override
	public void onInitialize() {
		ConfigManager configManager = new ConfigManager(configFileLocation, configFileName, defaultConfigFilePath);

        try {
            configMap = configManager.getConfig();
        } catch (Exception e) {
            configMap = configManager.getDefaultConfig();
        }

		

	}
}