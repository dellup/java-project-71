package hexlet.code.formatters;

import hexlet.code.Differ;

import java.io.IOException;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public abstract class Format {
    private static NavigableSet<String> keys1;
    private static NavigableSet<String> keys2;
    private static TreeMap<String, Object> minus;
    private static TreeMap<String, Object> plus;
    private static TreeMap<String, Object> noDiff;
    private static TreeMap[] diff;
    private static Set<String> sortedKeys;

    public static Set<String> getSortedKeys() {
        return sortedKeys;
    }

    private StringBuilder str = new StringBuilder();

    public static TreeMap<String, Object> getPlus() {
        return plus;
    }

    public static TreeMap<String, Object> getMinus() {
        return minus;
    }

    public static TreeMap<String, Object> getNoDiff() {
        return noDiff;
    }


    public static void init(TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
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
