package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;

import static hexlet.code.Differ.generate;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {
    public static void main(String[] args) {
        if (args.length > 0) {
            int exitCode = new CommandLine(new App()).execute(args);
            System.out.println("Hello, World!");
            System.exit(exitCode);
        }else {System.out.println("Hello, World!");}

    }
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private File filepath1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private File filepath2;
    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private String format = "stylish";

    public String readFile(File filepath) throws Exception {
        Path writeFilePath = Paths.get(filepath.toURI());
        return Files.readString(writeFilePath);
    }

    public static Map<String, Object> parse(String content) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map
                = objectMapper.readValue(content, new TypeReference<Map<String,Object>>(){});
        return map;
    }
    public static Map<String, Object> getData(String content) throws Exception {
        return parse(content);
    }

    @Override
    public Integer call() throws Exception{
        try {
            String strJson1 = readFile(filepath1);
            String strJson2 = readFile(filepath2);
            String gen = generate(new TreeMap<String, Object>(getData(strJson1)), new TreeMap<String, Object>(getData(strJson2)));
            System.out.println(gen);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            return 0;
        }
    }
}