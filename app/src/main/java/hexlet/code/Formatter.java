package hexlet.code;

import hexlet.code.formatters.Format;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;
import java.io.IOException;
import java.util.TreeMap;

import static hexlet.code.cnst.Style.JSON;
import static hexlet.code.cnst.Style.PLANE;
import static hexlet.code.cnst.Style.STYLISH;

public class Formatter {
    public static String format(TreeMap<String, Object> map1, TreeMap<String, Object> map2, String formatTitle)
            throws IOException {
        Format format;
        switch (formatTitle) {
            case STYLISH:
                format = new Stylish();
                return format.generate(map1, map2);
            case PLANE:
                format = new Plain();
                return format.generate(map1, map2);
            case JSON:
                format = new Json();
                return format.generate(map1, map2);
            default:
                return null;
        }
    }
}
