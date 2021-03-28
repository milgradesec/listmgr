package listmgr;

import java.util.ArrayList;

import com.beust.jcommander.JCommander;

public class App {
    public static void main(final String[] argv) {
        Args args = new Args();
        JCommander.newBuilder().addObject(args).build().parse(argv);

        ArrayList<String> lists = Configuration.read(args.config);
        if (lists.size() == 0) {
            return;
        }

        DataParser parser = new DataParser();
        for (String list : lists) {
            String data = DataFetcher.fetch(list);
            int size = parser.parse(data);
            System.out.printf("Added %d from [%s]\n", size, list);
        }
        parser.writeToFile(args.output);
        System.out.printf("Total list size: %d", parser.list.size());
    }
}
