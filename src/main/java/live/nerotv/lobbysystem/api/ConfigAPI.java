package live.nerotv.lobbysystem.api;

import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;

public class ConfigAPI {

    public static File Config = new File("Nero/Lobbysystem/config.yml");
    public static YamlConfiguration CFG = YamlConfiguration.loadConfiguration(Config);


    public static void checkEntry(String path,Object content, File file, YamlConfiguration cfg) {
        reloadConfig(file,cfg);
        if(!(file.exists())) {
            cfg.set(path, content);
            saveConfig(file,cfg);
        } else {
            if(!(cfg.contains(path))) {
                cfg.set(path, content);
                saveConfig(file,cfg);
            }
        }
    }

    public static void saveConfig(File file, YamlConfiguration cfg) {
        try {
            cfg.save(file);
        }
        catch (IOException ef) {
            ef.printStackTrace();
        }
        reloadConfig(file, cfg);
    }

    public static void reloadConfig(File file, YamlConfiguration cfg) {
        cfg = YamlConfiguration.loadConfiguration(file);
    }
}