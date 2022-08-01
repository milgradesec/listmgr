package com.github.milgradesec.listmgr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class.getSimpleName());

    private static final String defaultSourcesFile = "sources.conf";
    private static final String defaultOutputFile = "out.list";

    public static void main(final String[] argv) throws ParseException {
        Configurator.setRootLevel(Level.DEBUG);

        Options options = new Options();
        options.addOption("s", "sources", true, "Input file with urls to download and parse.");
        options.addOption("o", "output", true, "Output file.");
        options.addOption("h", "help", false, "Prints this message.");

        CommandLineParser cmdParser = new DefaultParser();
        CommandLine cmd = cmdParser.parse(options, argv);

        // String configFile = "sources.conf";
        // String outputFile = "blocklist.list";

        // if (cmd.hasOption("sources")) {
        // configFile = cmd.getOptionValue("sources");
        // }

        // if (cmd.hasOption("output")) {
        // outputFile = cmd.getOptionValue("output");
        // }

        logger.info("No sources file specified, using default: '{}'", defaultSourcesFile);
        ArrayList<String> sources = loadSourcesFromFile(defaultSourcesFile);
        if (sources.isEmpty()) {
            logger.error("No valid sources found on '{}'", defaultSourcesFile);
            return;
        }
        logger.info("Loaded {} sources from '{}'", sources.size(), defaultSourcesFile);

        Parser parser = new Parser();
        for (String list : sources) {
            String data = Fetcher.fetch(list);
            int size = parser.parse(data);
            logger.info("Added {} unique domains from '{}'", size, list);
        }
        parser.flush(defaultOutputFile);

        int size = parser.list.size();
        if (size == 0) {
            logger.error("Found 0 valid domains from sources, exiting...");
            return;
        }
        logger.info("Total list size: {}", parser.list.size());
    }

    public static ArrayList<String> loadSourcesFromFile(final String fileName) {
        ArrayList<String> sources = new ArrayList<>();

        try {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(fileName)));
            for (String line : lines) {

                if (line.startsWith("#") || line.isEmpty() || line.isBlank()) {
                    continue;
                }
                sources.add(line);
            }
        } catch (IOException e) {
            logger.error("Failed to read sources from '{}': {}", fileName, e.toString());
        }

        return sources;
    }
}
