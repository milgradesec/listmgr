package com.github.milgradesec.listmgr;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class ParserTest {
         
    @Test
    public void testParse() throws IOException {
        Parser parser = new Parser();

        String testdata = Files.readString(Paths.get("src/test/resources/testdata.txt"));
        parser.parse(testdata);

        assertEquals(4, parser.list.size());
    }
}
