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

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
class App implements Runnable{
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.out.println("Hello, World!");
        System.exit(exitCode);
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

    public static Map parse(String content) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map
                = objectMapper.readValue(content, new TypeReference<Map<String,Object>>(){});
        return map;
    }
    public static Map getData(String content) throws Exception {
        return parse(content);
    }

    @Override
    public void run() {
        try {
            String strJson1 = readFile(filepath1);
            String strJson2 = readFile(filepath2);
            System.out.println(getData(strJson1));
            System.out.println(getData(strJson2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}