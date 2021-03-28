package listmgr;

import com.beust.jcommander.Parameter;

/**
 * Command line arguments.
 */
public class Args {
    @Parameter(names = { "--config", "-c" }, description = "File with lists to parse")
    public String config = "lists.conf";

    @Parameter(names = { "--output", "-o" }, description = "Output file")
    public String output = "blocklist.list";

    @Parameter(names = { "--help", "-h" }, help = true)
    private boolean help;

    @Parameter(names = { "--debug", "-d" }, description = "Enable detailed logging")
    public boolean debug;
}
