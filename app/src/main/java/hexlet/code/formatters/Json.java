package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Json implements IFormat {
    public static String generate(TreeMap<String, Object> map1, TreeMap<String, Object> map2) throws IOException {
        var keys1 = map1.navigableKeySet();
        var keys2 = map2.navigableKeySet();
        var diff = Differ.generate(map1, map2);
        var minus = diff[0];
        var plus = diff[1];
        var noDiff = diff[2];
        Set<String> sortedKeys = new TreeSet<>(keys1);

        sortedKeys.addAll(keys2);
        StringBuilder str = new StringBuilder();
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
