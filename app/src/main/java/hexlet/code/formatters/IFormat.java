package hexlet.code.formatters;

import hexlet.code.Differ;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public interface IFormat {
    static String generate(TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
        var keys1 = map1.navigableKeySet();
        var keys2 = map2.navigableKeySet();
        var diff = Differ.generate(map1, map2);
        var minus = diff[0];
        var plus = diff[1];
        Set<String> sortedKeys = new TreeSet<>(keys1);

        sortedKeys.addAll(keys2);
        StringBuilder str = new StringBuilder();
        return null;
    }
}
