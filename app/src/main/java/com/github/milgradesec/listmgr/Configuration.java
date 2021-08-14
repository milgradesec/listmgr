package com.github.milgradesec.listmgr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Configuration {

    public static ArrayList<String> read(final String file) {
        ArrayList<String> sources = new ArrayList<>();

        try {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(file)));
            for (String line : lines) {

                if (line.startsWith("#") || line.isEmpty() || line.isBlank()) {
                    continue;
                }
                sources.add(line);
            }
        } catch (IOException e) {
            System.out.printf("error: failed to read config from '%s': %s\n", file, e.toString());
        }
        
        return sources;
    }
}
