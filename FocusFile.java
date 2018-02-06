package net.fathomtech.plugins.EnhancedFactions.util;

import org.bukkit.configuration.file.YamlConfiguration;
import net.fathomtech.plugins.EnhancedFactions.Main;
import java.io.File;


@SuppressWarnings("unused")
public class FocusFile {

    private final Main main;
    public final YamlConfiguration data = new YamlConfiguration();
    private final File focusFile;

    public FocusFile(Main main) {
        this.main = main;
        focusFile = new File(main.getDataFolder(), "data.yml");
        if (!focusFile.exists())
            try {
                focusFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        load();
    }

    public void save() {
        try {
            data.save(focusFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            data.load(focusFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
