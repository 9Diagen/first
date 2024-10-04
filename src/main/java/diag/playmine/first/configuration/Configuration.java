package diag.playmine.first.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.logging.Level;

public class Configuration {

    private final String fileName;
    public FileConfiguration yamlConfig;
    public File configurationFile;
    private final JavaPlugin plugin;

    public Configuration(@NotNull String fileName, @NotNull JavaPlugin plugin) {
        this.fileName = fileName;
        this.plugin = plugin;
        initialize();
    }

    public void initialize() {
        configurationFile = new File(plugin.getDataFolder(), fileName);
        plugin.saveResource(fileName, false);
        yamlConfig = YamlConfiguration.loadConfiguration(configurationFile);
    }

    public void saveConfig() {
        try {
            yamlConfig.save(configurationFile);
        } catch (Exception exception) {
            plugin.getLogger().log(Level.SEVERE, exception.getMessage(), exception);
        }
    }

    public void set(@NotNull String path, @NotNull Object value) {
        yamlConfig.set(path, value);
    }

    public double getDouble(@NotNull String path, double def) {
        if (yamlConfig.isInt(path))
            return getInt(path);
        return yamlConfig.getDouble(path, def);
    }

    public double getDouble(@NotNull String path) {
        return getDouble(path, 0.0);
    }

    public int getInt(@NotNull String path, int def) {
        return yamlConfig.getInt(path, def);
    }

    public int getInt(@NotNull String path) {
        return getInt(path, 0);
    }

}
