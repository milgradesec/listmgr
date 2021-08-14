package com.github.milgradesec.listmgr;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    /**
     * This hashset stores all parsed and valid domains without duplicates.
     */
    public Set<String> list = new HashSet<>();

    /**
     * Parses a line to extract any valid domain name.
     * 
     * @param data
     * @return
     */
    public int parse(final String data) {
        return parseResponseRegex(data);
    }

    private int parseResponseRegex(final String data) {
        final String[] lines = data.split("\n");
        Pattern p;
        Matcher m;
        int size = 0;

        for (String line : lines) {
            if (line.startsWith("#") || line.isEmpty() || line.isBlank()) {
                continue;
            }
            if (line.contains("#")) {
                final int pos = line.indexOf("#", 0);
                line = line.substring(0, pos - 1);
                line = line.stripTrailing();
            }
            line = line.toLowerCase();
            line = line.replaceAll("\r", "");

            p = Pattern.compile("^([a-z0-9][a-z0-9.-]*[.][a-z]{2,})$");
            m = p.matcher(line);
            if (m.matches()) {
                addToList(line);
                size += 1;
                continue;
            }

            p = Pattern.compile(
                    "^[0-9]{1,3}[.][0-9]{1,3}[.][0-9]{1,3}[.][0-9]{1,3}\\s+([a-z0-9][a-z0-9.-]*[.][a-z]{2,})$");
            m = p.matcher(line);
            if (m.matches()) {
                addToList(m.group(1));
                size += 1;
            }
        }
        return size;
    }

    /**
     * Adds a new domain to the hashset.
     * 
     * @param name name to add
     */
    private void addToList(final String name) {
        list.add(name);
    }

    /**
     * Writes all entries in the hashset to a file.
     * 
     * @param file file to write
     */
    public void flush(final String file) {
        try (FileWriter fw = new FileWriter(file)) {
            for (final String line : list) {
                fw.write(line + "\n");
            }
        } catch (final IOException e) {
            System.out.printf("error: failed to write data to file '%s': %s\n", file, e.toString());
        }
    }
}
