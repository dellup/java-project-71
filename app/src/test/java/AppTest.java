import static hexlet.code.FileUtil.readFile;
import static hexlet.code.Parser.getData;
import static hexlet.code.cnst.Style.JSON;
import static hexlet.code.cnst.Style.STYLISH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static hexlet.code.cnst.Style.PLAIN;
import hexlet.code.Formatter;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.TreeMap;



public class AppTest {
    private String json1 = "src/test/resources/fixtures/file1.json";
    private String json2 = "src/test/resources/fixtures/file2.json";
    private String yaml1 = "src/test/resources/fixtures/file1.yaml";
    private String yaml2 = "src/test/resources/fixtures/file2.yaml";

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
        TreeMap<String, Object> map1 = new TreeMap<>(getData(strJson1, "json"));
        TreeMap<String, Object> map2 = new TreeMap<>(getData(strJson2, "json"));
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        String actual = Formatter.format(map1, map2, STYLISH);
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateYaml() throws Exception {
        String strYaml1 = readFile(new File(yaml1));
        String strYaml2 = readFile(new File(yaml2));
        TreeMap<String, Object> map1 = new TreeMap<>(getData(strYaml1, "yaml"));
        TreeMap<String, Object> map2 = new TreeMap<>(getData(strYaml2, "yaml"));
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        String actual = Formatter.format(map1, map2, STYLISH);
        assertEquals(expected, actual);
    }
    @Test
    public void testGeneratePlain() throws Exception {
        String strYaml1 = readFile(new File(yaml1));
        String strYaml2 = readFile(new File(yaml2));
        TreeMap<String, Object> map1 = new TreeMap<>(getData(strYaml1, "yaml"));
        TreeMap<String, Object> map2 = new TreeMap<>(getData(strYaml2, "yaml"));
        String expected = "Property 'follow' was removed\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'proxy' was removed\n"
                + "Property 'timeout' was updated. From 50 to 20\n"
                + "Property 'verbose' was added with value: true";
        String actual = Formatter.format(map1, map2, PLAIN);
        assertEquals(expected, actual);
    }
    @Test
    public void testGenerateJson() throws Exception {
        String strYaml1 = readFile(new File(yaml1));
        String strYaml2 = readFile(new File(yaml2));
        TreeMap<String, Object> map1 = new TreeMap<>(getData(strYaml1, "yaml"));
        TreeMap<String, Object> map2 = new TreeMap<>(getData(strYaml2, "yaml"));
        String expected = "{\n"
                          + "  \"After\" : {\n"
                          + "    \"obj1\" : {\n"
                          + "      \"nestedKey\" : \"value\",\n"
                          + "      \"isNested\" : true\n"
                          + "    },\n"
                          + "    \"timeout\" : 20,\n"
                          + "    \"verbose\" : true\n"
                          + "  },\n"
                          + "  \"Before\" : {\n"
                          + "    \"follow\" : false,\n"
                          + "    \"proxy\" : \"123.234.53.22\",\n"
                          + "    \"timeout\" : 50\n"
                          + "  },\n"
                          + "  \"No changes\" : {\n"
                          + "    \"host\" : \"hexlet.io\"\n"
                          + "  }\n"
                          + "}";

        String actual = Formatter.format(map1, map2, JSON);
        assertEquals(expected, actual);
    }
}
