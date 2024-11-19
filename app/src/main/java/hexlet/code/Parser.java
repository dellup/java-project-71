package hexlet.code;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


public class Parser {
    public static Map<String, Object> parse(String content, String type) throws Exception {
        if (type.equals("json")) {
            return parseJson(content);
        } else if (type.equals("yaml")) {
            return parseYaml(content);
        }
        return new HashMap<>();

    }
    public static Map<String, Object> parseJson(String content) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        Map<String, Object> map
                = objectMapper.readValue(content, new TypeReference<>() { });
        return map;
    }
    public static Map<String, Object> parseYaml(String content) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        Map<String, Object> map
                = objectMapper.readValue(content, new TypeReference<>() { });
        return map;
    }
    public static Map<String, Object> getData(String content, String type) throws Exception {
        return parse(content, type);
    }

}
