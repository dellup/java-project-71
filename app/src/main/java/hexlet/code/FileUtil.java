package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

import static hexlet.code.Parser.getData;
import static hexlet.code.cnst.Type.JSON;
import static hexlet.code.cnst.Type.YAML;

public class FileUtil {
    public static String readFile(File filepath) throws Exception {
        Path writeFilePath = Paths.get(filepath.toURI());
        return Files.readString(writeFilePath);
    }
    public static TreeMap<String, Object> makeTree(String filepath) throws Exception {
        String content = isPath(filepath) ? Files.readString(Path.of(filepath)) : filepath;
        Map<String, Object> map = getData(content, filepath.endsWith("json") ? JSON : YAML);
        return new TreeMap<>(map);
    }
    private static boolean isPath(String input) {
        return Files.exists(Path.of(input));
    }
}
