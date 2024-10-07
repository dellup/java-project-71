package hexlet.code;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(TreeMap<String,Object> map1, TreeMap<String,Object> map2) {
        var keys1 = map1.navigableKeySet();
        var keys2 = map2.navigableKeySet();
        ArrayList<String> res = new ArrayList<>();
        for (String key : keys1) {
            if (!keys2.contains(key)) {
                res.add(String.valueOf(new StringBuilder("  - ")
                        .append(key + ": " + map1.get(key))));
            } else {
                if (map1.get(key).equals(map2.get(key))) {
                    res.add(String.valueOf(new StringBuilder("    ")
                            .append(key + ": " + map1.get(key))));
                } else {
                    res.add(String.valueOf(new StringBuilder("  - ")
                            .append(key + ": " + map1.get(key))));
                    res.add(String.valueOf(new StringBuilder("  + ")
                            .append(key + ": " + map2.get(key))));
                }
            }
        }
        for (String key : keys2) {
            if (!keys1.contains(key)) {
                res.add(String.valueOf(new StringBuilder("  + ")
                        .append(key + ": " + map2.get(key))));
            }
        }
        res = (ArrayList<String>) res.stream()
                .sorted(Comparator.comparing(s -> s.charAt(4)))
                .collect(Collectors.toList());
        StringBuilder str = new StringBuilder("{\n");
        res.forEach(n -> str.append(n + "\n"));
        str.append("}");
        return str.toString();
    }
}
