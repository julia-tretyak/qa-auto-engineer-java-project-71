package hexlet.code;

import java.util.List;
import java.util.Map;

public final class StylishFormatter {
    private static final String INDENT = "  ";

    public static String format(List<DiffEntry> diff) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (DiffEntry entry : diff) {
            String key = entry.getKey();
            String status = entry.getStatus();

            switch (status) {
                case "unchanged":
                    result.append(INDENT).append("  ").append(key).append(": ")
                          .append(formatValue(entry.getOldValue())).append("\n");
                    break;
                case "changed":
                    result.append(INDENT).append("- ").append(key).append(": ")
                          .append(formatValue(entry.getOldValue())).append("\n");
                    result.append(INDENT).append("+ ").append(key).append(": ")
                          .append(formatValue(entry.getNewValue())).append("\n");
                    break;
                case "removed":
                    result.append(INDENT).append("- ").append(key).append(": ")
                          .append(formatValue(entry.getOldValue())).append("\n");
                    break;
                case "added":
                    result.append(INDENT).append("+ ").append(key).append(": ")
                          .append(formatValue(entry.getNewValue())).append("\n");
                    break;
                default:
                    break;
            }
        }

        result.append("}");
        return result.toString();
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return (String) value;
        } else if (value instanceof Boolean || value instanceof Number) {
            return value.toString();
        } else if (value instanceof List || value instanceof Map) {
            return value.toString();
        } else {
            return String.valueOf(value);
        }
    }
}
