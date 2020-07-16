package com.paesa.listmgr;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Parser retrieves and parses lists from sources and generates an unified list
 */
public class Parser {
    private final ArrayList<String> sources;
    private final Set<String> list = new HashSet<String>();

    private static HttpClient client = HttpClient.newHttpClient();
    private Filter filter;

    public Parser(final ArrayList<String> sources) {
        this.sources = sources;
    }

    public void generate() {
        for (final String url : sources) {
            fetchAndParse(url);
        }
        System.out.println(list.size());
    }

    private void fetchAndParse(final String url) {
        final HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            System.out.println("Loading data from " + "[" + url + "]");
            final HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            parseResponse(response.body());

        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void parseResponse(final String body) {
        final String[] lines = body.split("\n");
        for (String line : lines) {

            if (line.startsWith("#") || line.isEmpty() || line.isBlank()) {
                // System.out.println(line);
                continue;
            }

            // Remove inline comments.
            if (line.contains("#")) {
                final int pos = line.indexOf("#", 0);
                line = line.substring(0, pos - 1);
            }

            // Remove all leading and trailing white spaces.
            line = line.strip();

            // Split IP from name
            final String[] splits = line.split(" ");
            if (splits.length == 2) {
                list.add(splits[1]);
            }
        }
    }

    public void writeToFile(final String file) {
        try (FileWriter fw = new FileWriter(file)) {
            for (final String line : list) {
                fw.write(line + "\n");
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}