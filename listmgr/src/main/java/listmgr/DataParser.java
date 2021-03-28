package listmgr;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DataParser retrieves and parses lists from sources and generates an unified
 * list
 */
public class DataParser {
    public Set<String> list = new HashSet<String>();

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

            p = Pattern.compile("^([a-z0-9][a-z0-9.-]*[.][a-z]{2,})$");
            m = p.matcher(line);
            if (m.matches()) {
                addToList(line);
                size += 1;
                continue;
            }

            p = Pattern
                    .compile("^[0-9]{1,3}[.][0-9]{1,3}[.][0-9]{1,3}[.][0-9]{1,3}\s+([a-z0-9][a-z0-9.-]*[.][a-z]{2,})$");
            m = p.matcher(line);
            if (m.matches()) {
                addToList(m.group(1));
                size += 1;
            }
        }
        return size;
    }

    private void addToList(final String name) {
        list.add(name);
    }

    public void writeToFile(final String file) {
        try (FileWriter fw = new FileWriter(file)) {
            for (final String line : list) {
                fw.write(line + "\n");
            }
        } catch (final IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
