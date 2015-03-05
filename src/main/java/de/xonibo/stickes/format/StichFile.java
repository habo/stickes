package de.xonibo.stickes.format;

import de.xonibo.stickes.StichData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.reflections.Reflections;

public class StichFile {

    static private List<StichFileLoad> loadableFormats = new ArrayList<>();
    static private List<StichFileSave> saveableFormats = new ArrayList<>();

    static Set<Class<StichFileLoad>> getLoadableClasses() throws ClassNotFoundException {
        Reflections reflections = new Reflections("de.xonibo.stickes.format");

        Set<Class<StichFileLoad>> subTypes = new HashSet<>();
        Set<String> loadClasses = reflections.getStore().getSubTypesOf(StichFileLoad.class.getName());
        for (String fqn : loadClasses) {
            subTypes.add((Class) Class.forName(fqn));
        }
        return subTypes;
    }

    static Set<Class<StichFileSave>> getSaveableClasses() throws ClassNotFoundException {
        Reflections reflections = new Reflections("de.xonibo.stickes.format");

        Set<Class<StichFileSave>> subTypes = new HashSet<>();
        Set<String> saveClasses = reflections.getStore().getSubTypesOf(StichFileSave.class.getName());
        for (String fqn : saveClasses) {
            subTypes.add((Class) Class.forName(fqn));
        }
        return subTypes;
    }

    public static Iterable<StichFileLoad> getLoadableFileFormats() {
        if (!loadableFormats.isEmpty()) {
            return loadableFormats;
        }
        try {
            Set<Class<StichFileLoad>> cs = getLoadableClasses();
            for (Class<StichFileLoad> c : cs) {
                if (c.getModifiers() != Modifier.ABSTRACT) {
                    try {
                        loadableFormats.add(c.newInstance());
                    } catch (InstantiationException | IllegalAccessException ex) {
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StichFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loadableFormats;
    }

    public static Iterable<StichFileSave> getSaveableFileFormats() {
        if (!saveableFormats.isEmpty()) {
            return saveableFormats;
        }
        try {
            Set<Class<StichFileSave>> cs = getSaveableClasses();
            for (Class<StichFileSave> c : cs) {
                try {
                    saveableFormats.add(c.newInstance());
                } catch (InstantiationException | IllegalAccessException ex) {
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StichFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return saveableFormats;
    }

    public FilenameFilter getLoadFilenameFilter() {
        final List<String> ext = new ArrayList<>();
        for (StichFileLoad f : getLoadableFileFormats()) {
            ext.add(f.getExtention());
        }
        return getFilenameFilter(ext);
    }

    public FilenameFilter getSaveFilenameFilter() {
        final List<String> ext = new ArrayList<>();
        for (StichFileSave f : getSaveableFileFormats()) {
            ext.add(f.getExtention());
        }
        return getFilenameFilter(ext);
    }

    public FilenameFilter getFilenameFilter(final List<String> extentions) {

        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                boolean acceptit = false;
                for (String s : extentions) {
                    acceptit |= name.toLowerCase().endsWith(s.toLowerCase());
                }
                return acceptit;
            }
        };
    }

    public StichData load(File file) throws Exception {
        for (Class<StichFileLoad> cs : getLoadableClasses()) {
            StichFileLoad loader = cs.newInstance();
            if (file.getName().endsWith(loader.getExtention())) {
                return loader.load(new FileInputStream(file));
            }
        }
        return null;
    }

    public void save(File file, StichData stichData) throws Exception {
        for (Class<StichFileSave> cs : getSaveableClasses()) {
            StichFileSave saver = cs.newInstance();
            String ext = saver.getExtention().replace("Save as ", "");
            if (file.getName().endsWith(ext)) {
                saver.save(new FileOutputStream(file), stichData);
                return;
            }
        }
    }
}
