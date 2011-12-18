package org.guardian;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipException;
import org.bukkit.plugin.PluginDescriptionFile;
import org.guardian.util.BukkitUtils;

public class DatabaseLoader {

    public static DatabaseBridge loadBridge(File file) {
        DatabaseBridge theBridge = null;
        try {
            // Load the jar
            JarFile jar = new JarFile(file);
            // Load the info about the bridge
            JarEntry entry = jar.getJarEntry("bridge.yml");
            InputStream stream = jar.getInputStream(entry);
            // Get the main class
            String main = new PluginDescriptionFile(stream).getMain();
            // Clean it all up
            stream.close();
            jar.close();
            // Get a new classloader
            URLClassLoader classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()}, DatabaseBridge.class.getClassLoader());
            // Load the class
            Class<?> clazz = classLoader.loadClass(main);
            // Construct it
            Object object = clazz.newInstance();
            // Verify it
            if (!(object instanceof DatabaseBridge)) {
                return null;
            }
            // Its all good
            theBridge = (DatabaseBridge) object;
        } catch (FileNotFoundException ex) {
            BukkitUtils.severe("Database bridge does not contain a valid bridge.yml");
        } catch (ZipException ex) {
            BukkitUtils.severe("The database bridge appears to be an invalid jar file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Lets return it
        return theBridge;
    }
}
