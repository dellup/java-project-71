package hexlet.code;

import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;

public class Formatter {

    public static String stylishFormat(TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
        var keys1 = map1.navigableKeySet();
        var keys2 = map2.navigableKeySet();
        var diff = Differ.generate(map1, map2);
        var minus = diff[0];
        var plus = diff[1];
        var noDiff = diff[2];
        Set<String> sortedKeys = new TreeSet<>(keys1);

        sortedKeys.addAll(keys2);
        StringBuilder str = new StringBuilder("{\n");
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
