package hexlet.code;

import java.util.TreeMap;

public class Differ {
    public static TreeMap[] generate(TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
        var keys1 = map1.navigableKeySet();
        var keys2 = map2.navigableKeySet();
        var minus = new TreeMap<String, Object>();
        var plus = new TreeMap<String, Object>();
        var noDiff = new TreeMap<String, Object>();
        for (String key : keys1) {
            if (!keys2.contains(key)) {
                minus.put(key, map1.get(key));
            } else {
                if (map1.get(key).equals(map2.get(key))) {
                    noDiff.put(key, map1.get(key));
                } else {
                    minus.put(key, map1.get(key));
                    plus.put(key, map2.get(key));
                }
            }
        }
        for (String key : keys2) {
            if (!keys1.contains(key)) {
                plus.put(key, map2.get(key));
            }
        }
        var res = new TreeMap[] {minus, plus, noDiff};
        return res;
    }
}
