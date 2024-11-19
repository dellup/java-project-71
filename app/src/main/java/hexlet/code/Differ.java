package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

import static hexlet.code.App.getFormat;
import static hexlet.code.FileUtil.readFile;
import static hexlet.code.Parser.getData;
import static hexlet.code.cnst.Type.JSON;
import static hexlet.code.cnst.Type.YAML;

public class Differ {
    public static String generate(String filepathFirst, String filepathSecond, String format) throws Exception {
        String contentFirst = isPath(filepathFirst) ? Files.readString(Path.of(filepathFirst)) : filepathFirst;
        String contentSecond = isPath(filepathSecond) ? Files.readString(Path.of(filepathSecond)) : filepathSecond;
        Map<String, Object> mapFirst = getData(contentFirst, filepathFirst.endsWith("json") ? JSON : YAML);
        Map<String, Object> mapSecond = getData(contentSecond, filepathSecond.endsWith("json") ? JSON : YAML);
        TreeMap<String, Object> treeFirst = new TreeMap<>(mapFirst);
        TreeMap<String, Object> treeSecond = new TreeMap<>(mapSecond);
        String gen = Formatter.format(treeFirst, treeSecond, format);
        System.out.println(gen);
        return gen;
    }
    private static boolean isPath(String input) {
        return Files.exists(Path.of(input));
    }
    public static String generate(String filepathFirst, String filepathSecond) throws Exception {
        return generate(filepathFirst, filepathSecond, getFormat());
    }
    public static TreeMap[] makeDiff(TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
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
    public static String generateResult(File filepathFirst, File filepathSecond, String format) throws Exception {
        String strJsonFirst = readFile(filepathFirst);
        String strJsonSecond = readFile(filepathSecond);
        return generate(strJsonFirst, strJsonSecond, format);
    }
}
