package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.util.TreeMap;
import java.util.concurrent.Callable;

import static hexlet.code.Formatter.stylishFormat;
import static hexlet.code.Parser.getData;
import static hexlet.code.Parser.readFile;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<String> {
    public static void main(String[] args) {
        if (args.length > 0) {
            int exitCode = new CommandLine(new App()).execute(args);
        } else {
            System.out.println("Hello, World!");
        }

    }
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private File filepath1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private File filepath2;
    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private static String format = "stylish";

    public static String getFormat() {
        return format;
    }

    @Override
    public String call() throws Exception {
        try {
            String strJson1 = readFile(filepath1);
            String strJson2 = readFile(filepath2);
            var map1 = filepath1.toString().substring(filepath1.toString().length() - 4).equals("json")
                    ? new TreeMap<String, Object>(getData(strJson1, "json"))
                    : new TreeMap<String, Object>(getData(strJson1, "yaml"));
            var map2 = filepath2.toString().substring(filepath2.toString().length() - 4).equals("json")
                    ? new TreeMap<String, Object>(getData(strJson2, "json"))
                    : new TreeMap<String, Object>(getData(strJson2, "yaml"));
            String gen = "";
            if (format.equals("stylish")) {
                gen = stylishFormat(map1, map2);
            }
            System.out.println(gen);
            return gen;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
