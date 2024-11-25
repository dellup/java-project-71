package hexlet.code.formatters;

import java.util.TreeMap;

public final class Plain extends Format {
    public String generate(TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
        init(map1, map2);
        StringBuilder str = new StringBuilder();
        for (String key : getSortedKeys()) {
            if (getMinus().containsKey(key) && getPlus().containsKey(key)) {
                Object valueMinus = getMinus().get(key);
                String objMinus = isComplex(valueMinus) ? "[complex value]" : toJsonCompatibleString(valueMinus);

                Object valuePlus = getPlus().get(key);
                String objPlus = isComplex(valuePlus) ? "[complex value]" : toJsonCompatibleString(valuePlus);


                str.append("Property ")
                        .append(strFormat(key))
                        .append(" was updated. From ")
                        .append(strFormat(objMinus))
                        .append(" to ")
                        .append(strFormat(objPlus))
                        .append("\n");
            } else if (getMinus().containsKey(key)) {
                str.append("Property ")
                        .append(strFormat(key))
                        .append(" was removed\n");
            } else if (getPlus().containsKey(key)) {
                Object valuePlus = getPlus().get(key);
                Object objPlus = isComplex(valuePlus) ? "[complex value]" : strFormat(safeToString(valuePlus));
                str.append("Property ")
                        .append(strFormat(key))
                        .append(" was added with value: ")
                        .append(objPlus)
                        .append("\n");
            }
        }
        return str.substring(0, str.length() - 1);
    }
    private static boolean isComplex(Object value) {
        if (value == null) {
            return false;
        }
        String stringValue = value.toString();
        return stringValue.startsWith("{") || stringValue.startsWith("[");
    }
    private static String toJsonCompatibleString(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Boolean || value instanceof Number) {
            return value.toString();
        }
        return value.toString().replace("=", ":");
    }

    private static String safeToString(Object value) {
        return value == null ? "null" : value.toString();
    }
    public static String strFormat(String value) {
        if (value == null || value.equals("null")) {
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
