package com.paesa.listmgr;

import com.beust.jcommander.Parameter;

public class Args {
    @Parameter(names = { "--config", "-c" })
    public String config = "lists.conf";

    @Parameter(names = { "--filter", "-f" })
    public String filter;

    @Parameter(names = { "--output", "-o" })
    public String output = "blocklist.list";

    @Parameter(names = "--help", help = true)
    private boolean help;
}