package org.guardian.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.guardian.Guardian;
import org.guardian.util.BukkitUtils;
import org.guardian.util.Utils;

public class MaterialData {

    private final Guardian plugin = Guardian.getInstance();
    private final HashMap<Integer, String> materialNames = new HashMap<Integer, String>();
    private final HashMap<Integer, Map<Byte, String>> materialDataNames = new HashMap<Integer, Map<Byte, String>>();

    public MaterialData(File file) {
        // Load the conf
        if (!file.exists()) {
            plugin.saveResource("materials.yml", false);
        }
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(file);
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(plugin.getResource("materials.yml"));
        conf.setDefaults(defConfig);
        save(conf, file);
        // Add all known materials
        for (final Material mat : Material.values()) {
            materialNames.put(mat.getId(), mat.toString().replace('_', ' ').toLowerCase());
        }
        // Parse the rest
        for (String entry : conf.getKeys(false)) {
            if (Utils.isInt(entry)) {
                if (conf.isString(entry)) {
                    materialNames.put(Integer.valueOf(entry), conf.getString(entry));
                } else if (conf.isConfigurationSection(entry)) {
                    final Map<Byte, String> dataNames = new HashMap<Byte, String>();
                    materialDataNames.put(Integer.valueOf(entry), dataNames);
                    final ConfigurationSection sec = conf.getConfigurationSection(entry);
                    for (final String data : sec.getKeys(false)) {
                        if (Utils.isByte(data)) {
                            if (sec.isString(data)) {
                                dataNames.put(Byte.valueOf(data), sec.getString(data));
                            } else {
                                BukkitUtils.warning("Parsing materials.yml: '" + data + "' is not a string.");
                            }
                        } else {
                            BukkitUtils.warning("Parsing materials.yml: '" + data + "' is no valid material data");
                        }
                    }
                } else {
                    BukkitUtils.warning("Parsing materials.yml: '" + entry + "' is neither a string nor a section.");
                }
            } else {
                BukkitUtils.warning("Parsing materials.yml: '" + entry + "' is no valid material id");
            }
        }
    }

    /**
     * @param type
     * @return Name of the material, or if it's unknown, the id.
     */
    public String materialName(int type) {
        return materialNames.containsKey(type) ? materialNames.get(type) : String.valueOf(type);
    }

    /**
     * @param type
     * @param data
     * @return Name of the material regarding it's data, or if it's unknown, the
     * basic name.
     */
    public String materialName(int type, byte data) {
        final Map<Byte, String> dataNames = materialDataNames.get(type);
        if (dataNames != null) {
            if (dataNames.containsKey(data)) {
                return dataNames.get(data);
            }
        }
        return materialName(type);
    }

    private static void save(YamlConfiguration conf, File file) {
        try {
            conf.save(file);
        } catch (IOException ex) {
            BukkitUtils.warning("Unable to save material.yml:", ex);
        }
    }
}
