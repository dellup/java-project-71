package hexlet.code.formatters;

import hexlet.code.Differ;

import java.io.IOException;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public abstract class Format {
    static NavigableSet<String> keys1;
    static NavigableSet<String> keys2;
    static TreeMap<String, Object> minus;
    static TreeMap<String, Object> plus;
    static TreeMap<String, Object> noDiff;
    static TreeMap[] diff;
    static Set<String> sortedKeys;
    StringBuilder str = new StringBuilder();
    public void init(TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
        keys1 = map1.navigableKeySet();
        keys2 = map2.navigableKeySet();
        diff = Differ.makeDiff(map1, map2);
        minus = diff[0];
        plus = diff[1];
        noDiff = diff[2];
        sortedKeys = new TreeSet<>(keys1);
        sortedKeys.addAll(keys2);
    }

    public abstract String generate(TreeMap<String, Object> map1, TreeMap<String, Object> map2) throws IOException;
}
