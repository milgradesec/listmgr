package com.github.milgradesec.listmgr;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Parser {
    private static final Logger logger = LogManager.getLogger(Parser.class.getSimpleName());

    /**
     * This hashset stores all parsed and valid domains without duplicates.
     */
    public Set<String> list = new HashSet<>();

    private final Pattern rxDomain = Pattern.compile("^([a-z0-9][a-z0-9.-]*[.][a-z]{2,})$");

    private final Pattern rxIPDomain = Pattern
            .compile("^[0-9]{1,3}[.][0-9]{1,3}[.][0-9]{1,3}[.][0-9]{1,3}\\s+([a-z0-9][a-z0-9.-]*[.][a-z]{2,})$");

    public int parse(final String data) {
        return parseResponse(data);
    }

    private int parseResponse(final String data) {
        final String[] lines = data.split("\n");
        int size = 0;

        for (final String line : lines) {
            if (parseLine(line)) {
                size += 1;
            }
        }
        return size;
    }

    private boolean parseLine(String line) {
        if (line.startsWith("#") || line.isEmpty() || line.isBlank()) {
            return false;
        }

        if (line.contains("#")) {
            final int pos = line.indexOf("#", 0);
            line = line.substring(0, pos - 1);
            line = line.stripTrailing();
        }
        line = line.toLowerCase();
        line = line.replaceAll("\r", "");

        Matcher m = rxDomain.matcher(line);
        if (m.matches()) {
            addToList(line);
            return true;
        }

        m = rxIPDomain.matcher(line);
        if (m.matches()) {
            addToList(m.group(1));
            return true;
        }

        return false;
    }

    /**
     * Adds a new entry to the hashset.
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
            logger.error("failed to write data to file '{}': {}", file, e.toString());
        }
    }
}
