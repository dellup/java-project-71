package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.TreeMap;

public final class Json extends Format {
    @Override
    public String generate(TreeMap<String, Object> map1, TreeMap<String, Object> map2) throws JsonProcessingException {
        init(map1, map2);
        TreeMap<String, Object> result = new TreeMap<>();
        result.put("After", getPlus());
        result.put("Before", getMinus());
        result.put("No changes", getNoDiff());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
    }
}
