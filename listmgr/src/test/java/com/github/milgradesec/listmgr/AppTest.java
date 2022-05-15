package com.github.milgradesec.listmgr;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class AppTest {

    @Test
    public void testLoadSourcesFromFile() {
        ArrayList<String> sources = App.loadSourcesFromFile("src/test/resources/sources.conf");
        assertEquals(30, sources.size());
    }
}
