package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {
    public static Map<String, Object> parse(String content, String type) throws Exception {
        if (type.equals("json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map
                    = objectMapper.readValue(content, new TypeReference<Map<String, Object>>() { });
            return map;
        } else if (type.equals("yaml")) {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            Map<String, Object> map
                    = objectMapper.readValue(content, new TypeReference<Map<String, Object>>() { });
            return map;
        }
        return new HashMap<>();

    }
    public static Map<String, Object> getData(String content, String type) throws Exception {
        return parse(content, type);
    }
    public static String readFile(File filepath) throws Exception {
        Path writeFilePath = Paths.get(filepath.toURI());
        return Files.readString(writeFilePath);
    }
}
