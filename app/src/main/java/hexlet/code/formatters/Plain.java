package hexlet.code.formatters;

import java.util.TreeMap;

public class Plain extends Format {
    public String generate(TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
        super.init(map1, map2);
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
