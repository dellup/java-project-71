package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.TreeMap;

public class Json extends Format {
    @Override
    public String generate(TreeMap<String, Object> map1, TreeMap<String, Object> map2) throws JsonProcessingException {
        super.init(map1, map2);
        TreeMap<String, Object> result = new TreeMap<>();
        result.put("After", plus);
        result.put("Before", minus);
        result.put("No changes", noDiff);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
    }
}
