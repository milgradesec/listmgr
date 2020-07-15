package com.paesa.listmgr;

import java.util.ArrayList;

public class App {
    public static void main(final String[] args) {
        final ArrayList<String> lists = Config.read("lists.conf");
        final Parser parser = new Parser(lists);
        parser.generate();
        parser.writeToFile("blocklist.list");
    }
}
