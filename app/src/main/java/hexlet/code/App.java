package hexlet.code;

import hexlet.code.cnst.Style;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.util.Map;
import java.util.concurrent.Callable;

import static hexlet.code.cnst.Style.STYLISH;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public final class App implements Callable<String> {
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

    private static String format;

    private static final Map<String, String> FORMAT_MAPPING = Map.of(
            "plain", Style.PLAIN,
            "json", Style.JSON
    );

    public static void setFormat(String inputFormat) {
        if (format == null) {
            format = STYLISH;
        } else {
            format = FORMAT_MAPPING.get(inputFormat);
        }
    }

    @Override
    public String call() throws Exception {
        try {
            setFormat(format);
            return Differ.generateResult(filepathFirst, filepathSecond, format);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
