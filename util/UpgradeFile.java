package net.fathomtech.plugins.EnhancedFactions.util;

import org.bukkit.configuration.file.YamlConfiguration;
import net.fathomtech.plugins.EnhancedFactions.Main;
import java.io.File;


@SuppressWarnings("unused")
public class UpgradeFile {

    private final Main main;
    public final YamlConfiguration data = new YamlConfiguration();
    private final File upgradeFile;

    public UpgradeFile(Main main) {
        this.main = main;
        upgradeFile = new File(main.getDataFolder(), "upgrades.yml");
        if (!focusFile.exists())
            try {
                upgradeFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        load();
    }

    public void save() {
        try {
            data.save(upgradeFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            data.load(upgradeFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
