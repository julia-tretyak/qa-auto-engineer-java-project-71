package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import hexlet.code.DiffEntry;

public final class PlainFormatter {
    public static String format(List<DiffEntry> diff) {
        StringBuilder result = new StringBuilder();

        for (DiffEntry entry : diff) {
            String key = entry.getKey();
            String status = entry.getStatus();

            switch (status) {
                case "changed":
                    result.append("Property '").append(key).append("' was updated. From ")
                          .append(formatValue(entry.getOldValue())).append(" to ")
                          .append(formatValue(entry.getNewValue())).append("\n");
                    break;
                case "removed":
                    result.append("Property '").append(key).append("' was removed\n");
                    break;
                case "added":
                    result.append("Property '").append(key).append("' was added with value: ")
                          .append(formatValue(entry.getNewValue())).append("\n");
                    break;
                default:
                    break;
            }
        }

        return result.toString().stripTrailing();
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Boolean || value instanceof Number) {
            return value.toString();
        } else if (value instanceof List || value instanceof Map) {
            return "[complex value]";
        } else {
            return String.valueOf(value);
        }
    }
}
