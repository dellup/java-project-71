package hexlet.code.formatters;

import java.util.TreeMap;

public class Stylish extends Format {
    public String generate(TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
        super.init(map1, map2);
        str = new StringBuilder("{\n");
        for (String key : sortedKeys) {
            if (minus.containsKey(key)) {
                str.append(new StringBuilder("  - ")
                        .append(key + ": " + minus.get(key) + "\n"));
            }
            if (plus.containsKey(key)) {
                str.append(new StringBuilder("  + ")
                        .append(key + ": " + plus.get(key) + "\n"));
            }
            if (noDiff.containsKey(key)) {
                str.append(new StringBuilder("    ")
                        .append(key + ": " + noDiff.get(key) + "\n"));
            }
        }
        str.append("}");
        return str.toString();
    }

}
