package listmgr;

import com.beust.jcommander.Parameter;

/**
 * Command line arguments
 */
public class CmdLineParser {
    @Parameter(names = { "-config" }, description = "File with lists to parse")
    public String config = "lists.conf";

    @Parameter(names = { "-output" }, description = "Output file")
    public String output = "blocklist.list";

    @Parameter(names = "-help", help = true)
    private boolean help;

    @Parameter(names = { "-debug" }, description = "Enable detailed logging")
    public boolean debug;
}
