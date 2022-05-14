package com.github.milgradesec.listmgr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class App {
    private static final Logger logger = LogManager.getLogger();

    public static void main(final String[] argv) throws ParseException {
        Options options = new Options();
        options.addOption("c", "config", true, "File with lists to parse");
        options.addOption("o", "output", true, "Output file");
        options.addOption("h", "help", false, "Print this message");

        CommandLineParser cmdParser = new DefaultParser();
        CommandLine cmd = cmdParser.parse(options, argv);

        String configFile = "lists.conf";
        String outputFile = "blocklist.list";

        if (cmd.hasOption("config")) {
            configFile = cmd.getOptionValue("config");
        }

        if (cmd.hasOption("output")) {
            outputFile = cmd.getOptionValue("output");
        }

        ArrayList<String> lists = loadLists(configFile);
        if (lists.isEmpty()) {
            return;
        }
        logger.info("Loaded {} sources from {}", lists.size(), configFile);

        Parser parser = new Parser();
        for (String list : lists) {
            String data = Fetcher.fetch(list);
            int size = parser.parse(data);
            logger.info("Added {} unique domains from {}", size, list);
        }

        parser.flush(outputFile);
        logger.info("Total list size: {}", parser.list.size());

        Path path = Paths.get(outputFile);
        long bytes;
        try {
            bytes = Files.size(path);
            logger.info("File size: {} KB", bytes / 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> loadLists(final String file) {
        ArrayList<String> sources = new ArrayList<>();

        try {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(file)));
            for (String line : lines) {

                if (line.startsWith("#") || line.isEmpty() || line.isBlank()) {
                    continue;
                }
                sources.add(line);
            }
        } catch (IOException e) {
            logger.error("failed to read config from {}: {}", file, e.toString());
        }

        return sources;
    }
}
