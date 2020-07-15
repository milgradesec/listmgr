package com.paesa.listmgr;

import java.util.ArrayList;

public class App {
    public static void main(final String[] args) {
        ArrayList<String> lists = Config.read();
        final Parser parser = new Parser(lists);
        parser.generate();
        parser.writeToFile("blocklist.list");
    }
}
