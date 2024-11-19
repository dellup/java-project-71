package hexlet.code.formatters;

import java.util.TreeMap;

public class Plain extends Format {
    public String generate(TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
        super.init(map1, map2);
        for (String key : sortedKeys) {
            if (minus.containsKey(key) && plus.containsKey(key)) {
                Object valueMinus = minus.get(key);
                String objMinus = isComplex(valueMinus) ? "[complex value]" : safeToString(valueMinus);

                Object valuePlus = plus.get(key);
                String objPlus = isComplex(valuePlus) ? "[complex value]" : safeToString(valuePlus);

                str.append("Property ")
                        .append(strFormat(key))
                        .append(" was updated. From ")
                        .append(strFormat(objMinus))
                        .append(" to ")
                        .append(strFormat(objPlus))
                        .append("\n");
            } else if (minus.containsKey(key)) {
                str.append("Property ")
                        .append(strFormat(key))
                        .append(" was removed\n");
            } else if (plus.containsKey(key)) {
                Object valuePlus = plus.get(key);
                Object objPlus = isComplex(valuePlus) ? "[complex value]" : strFormat(safeToString(valuePlus));
                str.append("Property ")
                        .append(strFormat(key))
                        .append(" was added with value: ")
                        .append(objPlus)
                        .append("\n");
            }
        }
        return str.toString().substring(0, str.length() - 1);
    }
    private static boolean isComplex(Object value) {
        if (value == null) {
            return false;
        }
        String stringValue = value.toString();
        return stringValue.startsWith("{") || stringValue.startsWith("[");
    }

    private static String safeToString(Object value) {
        return value == null ? "null" : value.toString();
    }
    public static String strFormat(String value) {
        if (value == null) {
            return "null";
        }
        if (value.charAt(0) == '{'
                || value.charAt(0) == '['
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
