package com.paesa.listmgr;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Config {
    public static ArrayList<String> read() {
        final ArrayList<String> lists = new ArrayList<String>();

        try {
            final ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get("lists.conf")));
            for (final String line : lines) {

                // Remove commented lines
                if (line.startsWith("#") || line.isEmpty()) {
                    continue;
                }
            }
        } catch (final Exception e) {
            System.out.println(e);
        }

        return lists;
    }
}