package listmgr;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Configuration {
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
            System.out.printf("error: failed to read config from '%s': %s", file, e.toString());
        }

        System.out.printf("Loaded %d lists from '%s'\n\n", lists.size(), file);
        return lists;
    }
}
