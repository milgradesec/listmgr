package com.github.milgradesec.listmgr;

import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class App {

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

        ArrayList<String> lists = Configuration.read(configFile);
        if (lists.isEmpty()) {
            return;
        }

        DataParser parser = new DataParser();
        for (String list : lists) {
            String data = DataFetcher.fetch(list);
            int size = parser.parse(data);
            System.out.printf("Added %d domains from [%s]\n", size, list);
        }

        parser.writeToFile(outputFile);
        System.out.printf("Total list size: %d\n", parser.list.size());
    }
}
