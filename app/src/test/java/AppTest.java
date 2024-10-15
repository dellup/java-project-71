
import static hexlet.code.App.getData;
import static hexlet.code.App.readFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.TreeMap;


public class AppTest {
    String json1 = "src/test/resources/fixtures/file1.json";
    String json2 = "src/test/resources/fixtures/file2.json";

    @Test
    public void testReadFile() throws Exception {
        String expected = "{\r\n"
                + "  \"host\": \"hexlet.io\",\r\n"
                + "  \"timeout\": 50,\r\n"
                + "  \"proxy\": \"123.234.53.22\",\r\n"
                + "  \"follow\": false\r\n"
                + "}";
        String actual = readFile(new File(json1));
        assertEquals(expected, actual);
    }
    @Test
    public void testGenerate() throws Exception {
        String strJson1 = readFile(new File(json1));
        String strJson2 = readFile(new File(json2));
        var map1 = new TreeMap<String, Object>(getData(strJson1));
        var map2 = new TreeMap<String, Object>(getData(strJson2));
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        String actual = Differ.generate(map1, map2);
        assertEquals(expected, actual);
    }
}
