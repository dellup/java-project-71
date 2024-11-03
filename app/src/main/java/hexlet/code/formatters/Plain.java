package hexlet.code.formatters;

import hexlet.code.Differ;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Plain implements IFormat {
    public static String generate(TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
        var keys1 = map1.navigableKeySet();
        var keys2 = map2.navigableKeySet();
        var diff = Differ.generate(map1, map2);
        var minus = diff[0];
        var plus = diff[1];
        Set<String> sortedKeys = new TreeSet<>(keys1);
        sortedKeys.addAll(keys2);
        StringBuilder str = new StringBuilder();
        for (String key : sortedKeys) {
            if (minus.containsKey(key) && plus.containsKey(key)) {
                Object valueMinus = minus.get(key);
                Object objMinus = valueMinus.toString().charAt(0) == '{'
                        || valueMinus.toString().charAt(0) == '['
                        ? "[complex value]" : valueMinus;
                Object valuePlus = plus.get(key);
                Object objPlus = valuePlus.toString().charAt(0) == '{'
                        || valuePlus.toString().charAt(0) == '['
                        ? "[complex value]" : valuePlus;
                str.append("Property " + strFormat(key) + " was updated. ")
                        .append("From ")
                        .append(strFormat(objMinus.toString()))
                        .append(" to ")
                        .append(strFormat(objPlus.toString()))
                        .append("\n");
            } else if (minus.containsKey(key)) {
                str.append("Property " + strFormat(key) + " was removed\n");
            } else if (plus.containsKey(key)) {
                Object valuePlus = plus.get(key);
                Object objPlus = valuePlus.toString().charAt(0) == '{'
                        || valuePlus.toString().charAt(0) == '['
                        ? "[complex value]" : strFormat(valuePlus.toString());
                str.append("Property " + strFormat(key) + " was added ")
                        .append("with value: ")
                        .append(objPlus)
                        .append("\n");
            }
        }
        return str.toString().substring(0, str.length() - 1);
    }
    public static String strFormat(String value) {
        if (value.toString().charAt(0) == '{'
                || value.toString().charAt(0) == '['
                || isBoolean(value) || isNumber(value)) {
            return value;
        }
        return "'" + value + "'";
    }
    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isBoolean(String str) {
        return "true".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str);
    }
}
