package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> data1 = parseJson(readFile(filePath1));
        Map<String, Object> data2 = parseJson(readFile(filePath2));

        return buildDiff(data1, data2);
    }

    private static String readFile(String filePath) throws Exception {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    private static Map<String, Object> parseJson(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }

    private static String buildDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> allKeys = new HashSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        Map<String, Object> sortedKeys = new TreeMap<>();
        for (String key : allKeys) {
            sortedKeys.put(key, null);
        }

        List<String> lines = new ArrayList<>();
        lines.add("{");

        for (String key : sortedKeys.keySet()) {
            boolean inFirst = data1.containsKey(key);
            boolean inSecond = data2.containsKey(key);

            if (inFirst && inSecond) {
                Object value1 = data1.get(key);
                Object value2 = data2.get(key);

                if (value1.equals(value2)) {
                    lines.add("    " + key + ": " + formatValue(value1));
                } else {
                    lines.add("  - " + key + ": " + formatValue(value1));
                    lines.add("  + " + key + ": " + formatValue(value2));
                }
            } else if (inFirst) {
                lines.add("  - " + key + ": " + formatValue(data1.get(key)));
            } else {
                lines.add("  + " + key + ": " + formatValue(data2.get(key)));
            }
        }

        lines.add("}");
        return String.join("\n", lines);
    }

    private static String formatValue(Object value) {
        if (value instanceof String) {
            return (String) value;
        } else if (value instanceof Boolean) {
            return value.toString();
        } else if (value instanceof Number) {
            return value.toString();
        } else {
            return String.valueOf(value);
        }
    }
}
