package com.paesa.listmgr;

import com.beust.jcommander.Parameter;

public class Args {
    @Parameter(names = { "--config", "-c" })
    String config = "lists.conf";

    @Parameter(names = { "--output", "-o" })
    String output = "blocklist.list";

    @Parameter(names = "--help", help = true)
    private boolean help;
}