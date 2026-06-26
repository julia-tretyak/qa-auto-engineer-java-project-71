package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String content, String format) throws Exception {
        ObjectMapper mapper = switch (format) {
            case "json" -> new ObjectMapper();
            case "yml", "yaml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
        return mapper.readValue(content, Map.class);
    }
}
