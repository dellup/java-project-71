package hexlet.code.formatters;

import java.util.TreeMap;

public final class Stylish extends Format {
    public String generate(TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
        init(map1, map2);
        StringBuilder str = new StringBuilder("{\n");
        for (String key : getSortedKeys()) {
            if (getMinus().containsKey(key)) {
                str.append(new StringBuilder("  - ")
                        .append(key + ": " + getMinus().get(key) + "\n"));
            }
            if (getPlus().containsKey(key)) {
                str.append(new StringBuilder("  + ")
                        .append(key + ": " + getPlus().get(key) + "\n"));
            }
            if (getNoDiff().containsKey(key)) {
                str.append(new StringBuilder("    ")
                        .append(key + ": " + getNoDiff().get(key) + "\n"));
            }
        }
        str.append("}");
        return str.toString();
    }

}
