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
        options.addOption("i", "input", true, "Input file with urls to download and parse.");
        options.addOption("o", "output", true, "Output file.");
        options.addOption("h", "help", false, "Prints this message.");

        CommandLineParser cmdParser = new DefaultParser();
        CommandLine cmd = cmdParser.parse(options, argv);

        String src = "";
        String dst = "";

        if (cmd.hasOption("input")) {
            src = cmd.getOptionValue("input");
        } else {
            logger.info("No input file specified, using default: '{}'", defaultSourcesFile);
            src = defaultSourcesFile;
        }

        if (cmd.hasOption("output")) {
            dst = cmd.getOptionValue("output");
        } else {
            logger.info("No output file specified, using default: '{}'", defaultOutputFile);
            dst = defaultOutputFile;
        }

        ArrayList<String> sources = loadSourcesFromFile(src);
        if (sources.isEmpty()) {
            logger.error("No valid sources found on '{}'", src);
            return;
        }
        logger.info("Loaded {} sources from '{}'", sources.size(), src);

        Parser parser = new Parser();
        for (String list : sources) {
            String data = Fetcher.fetch(list);
            int size = parser.parse(data);
            logger.info("Added {} unique domains from '{}'", size, list);
        }
        parser.flush(dst);

        int size = parser.list.size();
        if (size == 0) {
            logger.error("Found no valid domains from sources, exiting...");
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
