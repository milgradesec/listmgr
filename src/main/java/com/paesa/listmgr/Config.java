package com.paesa.listmgr;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Config {

    /**
     * Reads configuration from a file.
     * 
     * @param file filename or full path
     */
    public static ArrayList<String> read(final String file) {
        final ArrayList<String> lists = new ArrayList<String>();

        try {
            final ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(file)));
            for (final String line : lines) {

                if (line.startsWith("#") || line.isEmpty() || line.isBlank()) {
                    continue;
                }
                lists.add(line);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return lists;
    }
}