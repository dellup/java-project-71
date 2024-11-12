package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
    public static String readFile(File filepath) throws Exception {
        Path writeFilePath = Paths.get(filepath.toURI());
        return Files.readString(writeFilePath);
    }
}
