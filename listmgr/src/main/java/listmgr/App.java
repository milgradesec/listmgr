package listmgr;

import java.util.ArrayList;

import com.beust.jcommander.JCommander;

public class App {
    public static void main(final String[] argv) {
        Args args = new Args();
        JCommander.newBuilder().addObject(args).build().parse(argv);

        ArrayList<String> lists = Config.read(args.config);
        Parser parser = new Parser(lists);

        parser.generate();
        parser.writeToFile(args.output);
    }
}
