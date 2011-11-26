package org.guardian;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.configuration.file.YamlConfiguration;

public class WorldConfig extends LoggingEnabledMapping
{
	public WorldConfig(File file) throws IOException {
		final Map<String, Object> def = new HashMap<String, Object>();
		def.put("table", "lb-" + file.getName().substring(0, file.getName().length() - 4));
		for (final Logging l : Logging.values())
			def.put("logging." + l.toString(), false);
		final YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		for (final Entry<String, Object> e : def.entrySet())
			if (config.get(e.getKey()) == null)
				config.set(e.getKey(), e.getValue());
		config.save(file);
		for (final Logging l : Logging.values())
			setLogging(l, config.getBoolean("logging." + l.toString()));
	}
}
