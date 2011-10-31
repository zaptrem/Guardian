package org.guardian;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.PluginDescriptionFile;

public class DatabaseLoader {

    public static DatabaseBridge loadAddon(File file) {
        DatabaseBridge theBridge = null;
        PluginDescriptionFile description = null;
        try {
            JarFile jar = new JarFile(file);
            JarEntry entry = jar.getJarEntry("bridge.yml");
            InputStream stream = jar.getInputStream(entry);
            description = new PluginDescriptionFile(stream);
            stream.close();
            jar.close();
        } catch (IOException ex) {
        } catch (InvalidDescriptionException ex) {
        }

        ClassLoader parentClassLoader = DatabaseLoader.class.getClassLoader();
        URLClassLoader classLoader = null;
        try {
            URL[] urls = new URL[1];
            urls[0] = file.toURI().toURL();

            classLoader = new URLClassLoader(urls, parentClassLoader);

            Class<?> jarClass = classLoader.loadClass(description.getMain());
            Constructor<?> constructor = jarClass.getConstructor();
            theBridge = (DatabaseBridge) constructor.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return theBridge;
    }
}
