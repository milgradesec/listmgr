package com.paesa.listmgr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Matcher {
    private final Set<String> hashtable = new HashSet<String>();
    private final ArrayList<String> prefixes = new ArrayList<String>();
    private final ArrayList<String> suffixes = new ArrayList<String>();
    private final ArrayList<String> subStrings = new ArrayList<String>();
    private final ArrayList<Pattern> regexes = new ArrayList<Pattern>();
    private final String[] regexpRunes = new String[] { "[", "]", "(", ")", "|", "?", "+", "$", "{", "}", "^" };

    public Matcher(final String file) {
        try {
            load(file);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private void load(final String file) throws IOException {
        final ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(file)));
        for (String line : lines) {

            if (line.startsWith("#") || line.isEmpty() || line.isBlank()) {
                continue;
            }

            for (final String rune : regexpRunes) {
                if (line.contains(rune)) {
                    final Pattern p = Pattern.compile(line);
                    regexes.add(p);
                    break;
                }
            }

            if (line.contains("*")) {
                if (line.endsWith("*") && line.startsWith("*")) {
                    line = StringUtils.stripStart(line, "*");
                    line = StringUtils.stripEnd(line, "*");
                    subStrings.add(line);
                    continue;
                }

                if (line.endsWith("*")) {
                    line = StringUtils.stripEnd(line, "*");
                    prefixes.add(line);
                    continue;
                }

                if (line.startsWith("*")) {
                    line = StringUtils.stripStart(line, "*");
                    suffixes.add(line);
                    continue;
                }
            } else {
                hashtable.add(line);
            }
        }
    }

    public boolean match(final String name) {
        if (hashtable.contains(name)) {
            return true;
        }

        for (final String prefix : prefixes) {
            if (name.startsWith(prefix)) {
                return true;
            }
        }

        for (final String suffix : suffixes) {
            if (name.endsWith(suffix)) {
                return true;
            }
        }

        for (final String subString : subStrings) {
            if (name.contains(subString)) {
                return true;
            }
        }

        for (Pattern p : regexes) {
            java.util.regex.Matcher m = p.matcher(name);
            if (m.matches()) {
                return true;
            }
        }
        return false;
    }
}