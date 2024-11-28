package hexlet.code;

import java.io.File;
import java.util.TreeMap;

import static hexlet.code.FileUtil.makeTree;
import static hexlet.code.FileUtil.readFile;
import static hexlet.code.cnst.Style.STYLISH;

public class Differ {
    public static String generate(String filepathFirst, String filepathSecond, String format) throws Exception {
        TreeMap<String, Object> treeFirst = makeTree(filepathFirst);
        TreeMap<String, Object> treeSecond = makeTree(filepathSecond);
        String gen = Formatter.format(treeFirst, treeSecond, format);
        System.out.println(gen);
        return gen;
    }
    public static String generate(String filepathFirst, String filepathSecond) throws Exception {
        return generate(filepathFirst, filepathSecond, STYLISH);
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
                Object value1 = map1.get(key);
                Object value2 = map2.get(key);

                if (value1 != null && value1.equals(value2)) {
                    noDiff.put(key, value1);
                } else {
                    minus.put(key, value1);
                    plus.put(key, value2);
                }
            }
        }
        for (String key : keys2) {
            if (!keys1.contains(key)) {
                plus.put(key, map2.get(key));
            }
        }
        return new TreeMap[] {minus, plus, noDiff};
    }
    public static String generateResult(File filepathFirst, File filepathSecond, String format) throws Exception {
        String strJsonFirst = readFile(filepathFirst);
        String strJsonSecond = readFile(filepathSecond);
        return generate(strJsonFirst, strJsonSecond, format);
    }
}
