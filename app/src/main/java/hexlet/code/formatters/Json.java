package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public class Json extends Format {
    public String generate(TreeMap<String, Object> map1, TreeMap<String, Object> map2) throws IOException {
        super.init(map1, map2);
        ObjectMapper objectMapper = new ObjectMapper();
        str.append("Before: ").append("\n");
        str.append(minus).append("\n");
        objectMapper.writeValue(new File("src/test/resources/fixtures/minus.json"), minus);
        str.append("After: ").append("\n");
        str.append(plus).append("\n");
        objectMapper.writeValue(new File("src/test/resources/fixtures/plus.json"), plus);
        str.append("No changes: ").append("\n");
        str.append(noDiff);
        objectMapper.writeValue(new File("src/test/resources/fixtures/noDiff.json"), noDiff);
        return str.toString();
    }
}
