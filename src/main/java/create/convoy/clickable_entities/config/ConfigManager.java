package create.convoy.clickable_entities.config;

import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.*;
import org.yaml.snakeyaml.Yaml;

import create.convoy.clickable_entities.ClickableEntities;

public class ConfigManager {
    private String configFileLocation;
    private String configFileName;
    private String defaultConfigFilePath = "assets/clickable-entities/default_config.yaml";
    private File configDir;
    private File configFile;

    public String getConfigFileLocation() {return configFileLocation;}
    public String getConfigFileName() {return configFileName;}
    public File getConfigDir() {return configDir;}
    public File getConfigFile() {return configFile;}

    public String getDefaultConfigFilePath() {return defaultConfigFilePath;}
    public void setDefaultConfigFilePath(String DefaultConfigFilePath) {defaultConfigFilePath = DefaultConfigFilePath;}

    public ConfigManager(String ConfigFileLocation, String ConfigFileName, String DefaultConfigFilePath) {
        configFileLocation = ConfigFileLocation;
        configFileName = ConfigFileName;
        defaultConfigFilePath = DefaultConfigFilePath;
        configDir = new File(configFileLocation);

        if (!configDir.exists()) {
            boolean newDir = configDir.mkdirs();

            if (newDir) {
                ClickableEntities.LOGGER.info(ClickableEntities.MOD_NAME + ": " + "Successfully Created Config Directory");
            } else {
                ClickableEntities.LOGGER.error(ClickableEntities.MOD_NAME + ": " + "Failed to Create Config Directory");
                return;
            }
        }

        configFile = new File(configFileLocation + configFileName);
        try {
            if (configFile.createNewFile()) {
                ClickableEntities.LOGGER.info(ClickableEntities.MOD_NAME + ": " + "File created: " + configFile.getPath());
                
                copyFile(this.getClass().getClassLoader().getResourceAsStream(defaultConfigFilePath), configFile);
            } else {
                ClickableEntities.LOGGER.info(ClickableEntities.MOD_NAME + ": " + "Config file already exists.");
            }
        } catch (IOException e) {
            ClickableEntities.LOGGER.info(ClickableEntities.MOD_NAME + ": " + "An error occurred.");
            e.printStackTrace();
        }
    }

    public Map<String, Map<String, String>> getConfig() {
        Yaml yaml = new Yaml();

        try {
            FileInputStream inputStream = new FileInputStream(configFile);
            return yaml.load(inputStream);
        } catch (Exception e) {
            Map<String, Map<String, String>> returnMap = Map.of();
            return returnMap;
        }
    }

    public Map<String, Map<String, String>> getDefaultConfig() {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(defaultConfigFilePath);
        return yaml.load(inputStream);
    }

    public void saveConfig(Map<String, Map<String, String>> configMap) {
        Yaml yaml = new Yaml();
        
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(configFile));
            yaml.dump(configMap, outputStreamWriter);
        } catch (Exception e) {return;}
    }

    private void copyFile(InputStream sourceFile, File writeFile) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(writeFile);
        try { 
            int i; 
            while ((i = sourceFile.read()) != -1) {
                outputStream.write(i); 
            } 
        } finally { 
            if (sourceFile != null) {
                sourceFile.close(); 
            }
            if (outputStream != null) { 
                outputStream.close(); 
            } 
        }
        ClickableEntities.LOGGER.info("Successfully Copied File");
    } 
}