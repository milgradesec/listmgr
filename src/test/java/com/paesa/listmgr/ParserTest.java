package com.paesa.listmgr;

import java.util.ArrayList;

import org.junit.Test;

public class ParserTest {
    @Test
    public void testGenerate() {
        final ArrayList<String> lists = new ArrayList<String>();
        lists.add("https://raw.githubusercontent.com/StevenBlack/hosts/master/hosts");

        final Parser parser = new Parser(lists);
        parser.generate();
    }
}