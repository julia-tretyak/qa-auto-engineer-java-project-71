package hexlet.code;

import hexlet.code.formatters.StylishFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.JsonFormatter;
import java.util.List;

public final class Formatter {
    public static String format(List<DiffEntry> diff, String format) throws Exception {
        return switch (format) {
            case "stylish" -> StylishFormatter.format(diff);
            case "plain" -> PlainFormatter.format(diff);
            case "json" -> JsonFormatter.format(diff);
            default -> throw new Exception("Unknown format: " + format);
        };
    }
}
