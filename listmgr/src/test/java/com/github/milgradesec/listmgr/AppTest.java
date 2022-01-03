package com.github.milgradesec.listmgr;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class AppTest {
    @Test
    public void testLoadLists() {
        ArrayList<String> sources = App.loadLists("src/test/resources/lists.conf");
        assertEquals(30, sources.size());
    }
}
