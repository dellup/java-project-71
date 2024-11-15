package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.util.concurrent.Callable;

import static hexlet.code.cnst.Style.JSON;
import static hexlet.code.cnst.Style.STYLISH;
import static hexlet.code.cnst.Type.YAML;

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
    private File filepathFirst;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private File filepathSecond;
    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private static String format = STYLISH;
    public static String getFormat() {
        return format;
    }
    public static void setFormat() {
        if (getFormat().equals("yaml")) {
            format = YAML;
        } else if (getFormat().equals("json")) {
            format = JSON;
        }
    }

    @Override
    public String call() throws Exception {
        try {
            setFormat();
            return Differ.generateResult(filepathFirst, filepathSecond, format);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
