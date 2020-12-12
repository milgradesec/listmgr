package com.paesa.listmgr;

import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

public class ConfigTest {
    @Test
    public void testRead() {
        ArrayList<String> lists = Config.read("lists.conf");
        assertNotEquals(lists.size(), 0);
    }
}