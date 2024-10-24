package hexlet.code;

import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;
import java.util.TreeMap;

public class Formatter {
    public static String format(TreeMap<String, Object> map1, TreeMap<String, Object> map2, String formatTitle) {
        switch (formatTitle) {
            case "stylish":
                return Stylish.generate(map1, map2);
            case "plain":
                return Plain.generate(map1, map2);
            default:
                return null;
        }
    }
}
