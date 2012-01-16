package org.guardian;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.material.MaterialData;
import org.guardian.util.BukkitUtils;
import org.guardian.util.Utils;

public class MaterialName
{

    private final Guardian plugin = Guardian.getInstance();
    private final Map<Integer, String> materialNames = new HashMap<Integer, String>();
    private final Map<Integer, Map<Byte, String>> materialDataNames = new HashMap<Integer, Map<Byte, String>>();

    public MaterialName() {
        // Add all known materials
        for (final Material mat : Material.values())
            materialNames.put(mat.getId(), mat.toString().replace('_', ' ').toLowerCase());
        // Load config
        final File file = new File(plugin.getDataFolder(), "materials.yml");
        final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (cfg.getKeys(false).isEmpty()) {
            // Generate defaults
            cfg.options().header("Add block or item names you want to be overridden or also names for custom blocks");
            cfg.set("6.1", "redwood sapling");
            cfg.set("6.2", "birch sapling");
            cfg.set("9", "water");
            cfg.set("11", "lava");
            cfg.set("17.1", "redwood log");
            cfg.set("17.2", "birch log");
            cfg.set("18.1", "redwood leaves");
            cfg.set("18.2", "birch leaves");
            cfg.set("31.0", "dead long grass");
            cfg.set("31.2", "fern");
            for (byte i = 0; i < 7; i++) {
                cfg.set("35." + i, toReadable(Material.STEP.getNewData(i)));
                cfg.set("351." + i, toReadable(Material.DOUBLE_STEP.getNewData(i)));
            }
            for (byte i = 0; i < 16; i++) {
                cfg.set("35." + i, toReadable(Material.WOOL.getNewData(i)));
                cfg.set("351." + i, toReadable(Material.INK_SACK.getNewData(i)));
            }
            try {
                cfg.save(file);
            } catch (final IOException ex) {
                BukkitUtils.warning("Unable to save material.yml: ", ex);
            }
        }
        for (final String entry : cfg.getKeys(false))
            if (Utils.isInt(entry)) {
                if (cfg.isString(entry))
                    materialNames.put(Integer.valueOf(entry), cfg.getString(entry));
                else if (cfg.isConfigurationSection(entry)) {
                    final Map<Byte, String> dataNames = new HashMap<Byte, String>();
                    materialDataNames.put(Integer.valueOf(entry), dataNames);
                    final ConfigurationSection sec = cfg.getConfigurationSection(entry);
                    for (final String data : sec.getKeys(false))
                        if (Utils.isByte(data)) {
                            if (sec.isString(data))
                                dataNames.put(Byte.valueOf(data), sec.getString(data));
                            else
                                BukkitUtils.warning("Parsing materials.yml: '" + data + "' is not a string.");
                        } else
                            BukkitUtils.warning("Parsing materials.yml: '" + data + "' is no valid material data");
                } else
                    BukkitUtils.warning("Parsing materials.yml: '" + entry + "' is neither a string nor a section.");
            } else
                BukkitUtils.warning("Parsing materials.yml: '" + entry + "' is no valid material id");
    }

    /**
     * @param type
     * @return Name of the material, or if it's unknown, the id.
     */
    public String get(int type) {
        return materialNames.containsKey(type) ? materialNames.get(type) : String.valueOf(type);
    }

    /**
     * @param type
     * @param data
     * @return Name of the material regarding it's data, or if it's unknown, the basic name.
     */
    public String get(int type, byte data) {
        final Map<Byte, String> dataNames = materialDataNames.get(type);
        if (dataNames != null)
            if (dataNames.containsKey(data))
                return dataNames.get(data);
        return get(type);
    }

    private static String toReadable(MaterialData matData) {
        return matData.toString().toLowerCase().replace('_', ' ').replaceAll("[^a-z ]", "");
    }
}
