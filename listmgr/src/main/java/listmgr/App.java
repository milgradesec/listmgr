package listmgr;

import java.util.ArrayList;

import com.beust.jcommander.JCommander;

public class App {
    public static void main(final String[] argv) {
        CmdLineParser args = new CmdLineParser();
        JCommander.newBuilder().addObject(args).build().parse(argv);

        ArrayList<String> lists = Configuration.read(args.config);
        if (lists.size() == 0) {
            return;
        }

        DataParser parser = new DataParser();
        for (String list : lists) {
            String data = DataFetcher.fetch(list);
            parser.parse(data);
        }
        parser.writeToFile(args.output);
        System.out.println("Total size: " + parser.list.size());
    }
}
