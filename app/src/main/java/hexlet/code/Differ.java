package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public final class Differ {
    public static String generate(final String filePath1, final String filePath2)
            throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(
            final String filePath1,
            final String filePath2,
            final String format) throws Exception {
        final String content1 = readFile(filePath1);
        final String content2 = readFile(filePath2);

        final String format1 = getFileExtension(filePath1);
        final String format2 = getFileExtension(filePath2);

        final Map<String, Object> data1 = Parser.parse(content1, format1);
        final Map<String, Object> data2 = Parser.parse(content2, format2);

        final List<DiffEntry> diff = buildDiff(data1, data2);
        return Formatter.format(diff, format);
    }

    private static String readFile(final String filePath) throws Exception {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    private static String getFileExtension(final String filePath) {
        final int lastDot = filePath.lastIndexOf('.');
        if (lastDot == -1) {
            return "json";
        }
        return filePath.substring(lastDot + 1).toLowerCase();
    }

    private static List<DiffEntry> buildDiff(
            final Map<String, Object> data1,
            final Map<String, Object> data2) {
        final Set<String> allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        final List<DiffEntry> result = new ArrayList<>();

        for (final String key : allKeys) {
            final boolean inFirst = data1.containsKey(key);
            final boolean inSecond = data2.containsKey(key);

            if (inFirst && inSecond) {
                final Object value1 = data1.get(key);
                final Object value2 = data2.get(key);

                if (value1 == null && value2 == null) {
                    result.add(new DiffEntry(key, null, null, "unchanged"));
                } else if (value1 != null && value1.equals(value2)) {
                    result.add(new DiffEntry(key, value1, value2, "unchanged"));
                } else {
                    result.add(new DiffEntry(key, value1, value2, "changed"));
                }
            } else if (inFirst) {
                result.add(new DiffEntry(key, data1.get(key), null, "removed"));
            } else {
                result.add(new DiffEntry(key, null, data2.get(key), "added"));
            }
        }

        return result;
    }
}
