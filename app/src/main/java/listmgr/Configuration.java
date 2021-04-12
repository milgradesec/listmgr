package listmgr;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Configuration {

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
            System.out.println(e.getMessage());
        }

        System.out.printf("Loaded %d lists from '%s'\n", lists.size(), file);
        return lists;
    }
}
