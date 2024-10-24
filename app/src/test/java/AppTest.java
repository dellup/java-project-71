import static hexlet.code.Parser.getData;
import static hexlet.code.Parser.readFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.Formatter;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.TreeMap;


public class AppTest {
    String json1 = "src/test/resources/fixtures/file1.json";
    String json2 = "src/test/resources/fixtures/file2.json";
    String yaml1 = "src/test/resources/fixtures/file1.yaml";
    String yaml2 = "src/test/resources/fixtures/file2.yaml";

    @Test
    public void testReadFileJSon() throws Exception {
        String expected = "{\r\n"
                + "  \"timeout\": 20,\r\n"
                + "  \"verbose\": true,\r\n"
                + "  \"host\": \"hexlet.io\",\r\n"
                + "  \"obj1\": {\r\n"
                + "    \"nestedKey\": \"value\",\r\n"
                + "    \"isNested\": true\r\n"
                + "  }\r\n"
                + "}";
        String actual = readFile(new File(json2));
        assertEquals(expected, actual);
    }
    @Test
    public void testReadFileYaml() throws Exception {
        String expected = "timeout: 20\n"
                + "verbose: true\n"
                + "host: hexlet.io\n"
                + "obj1:\n"
                + "  nestedKey: value\n"
                + "  isNested: true";
        String actual = readFile(new File(yaml2));
        assertEquals(expected, actual);
    }
    @Test
    public void testGenerate() throws Exception {
        String strJson1 = readFile(new File(json1));
        String strJson2 = readFile(new File(json2));
        var map1 = new TreeMap<String, Object>(getData(strJson1, "json"));
        var map2 = new TreeMap<String, Object>(getData(strJson2, "json"));
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        String actual = Formatter.format(map1, map2, "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateYaml() throws Exception {
        String strYaml1 = readFile(new File(yaml1));
        String strYaml2 = readFile(new File(yaml2));
        var map1 = new TreeMap<String, Object>(getData(strYaml1, "yaml"));
        var map2 = new TreeMap<String, Object>(getData(strYaml2, "yaml"));
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        String actual = Formatter.format(map1, map2, "stylish");
        assertEquals(expected, actual);
    }
    @Test
    public void testGeneratePlain() throws Exception {
        String strYaml1 = readFile(new File(yaml1));
        String strYaml2 = readFile(new File(yaml2));
        var map1 = new TreeMap<String, Object>(getData(strYaml1, "yaml"));
        var map2 = new TreeMap<String, Object>(getData(strYaml2, "yaml"));
        String expected = "Property 'follow' was removed\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'proxy' was removed\n"
                + "Property 'timeout' was updated. From 50 to 20\n"
                + "Property 'verbose' was added with value: true";
        String actual = Formatter.format(map1, map2, "plain");
        assertEquals(expected, actual);
    }
}
