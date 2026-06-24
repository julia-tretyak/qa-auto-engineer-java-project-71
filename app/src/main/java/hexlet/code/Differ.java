package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        String content1 = readFile(filePath1);
        String content2 = readFile(filePath2);

        Map<String, Object> data1 = parseJson(content1);
        Map<String, Object> data2 = parseJson(content2);

        return "Data1: " + data1 + "\nData2: " + data2;
    }

    private static String readFile(String filePath) throws Exception {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    private static Map<String, Object> parseJson(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }
}
