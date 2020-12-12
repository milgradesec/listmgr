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
        ArrayList<String> lists = new ArrayList<String>();

        try {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(file)));
            for (String line : lines) {

                if (line.startsWith("#") || line.isEmpty() || line.isBlank()) {
                    continue;
                }
                lists.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lists;
    }
}