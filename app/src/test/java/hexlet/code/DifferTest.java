package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    @Test
    void testGenerateJson() throws Exception {
        String file1 = "src/test/resources/file1.json";
        String file2 = "src/test/resources/file2.json";

        String expected = "{"
            + "\n  - follow: false"
            + "\n    host: hexlet.io"
            + "\n  - proxy: 123.234.53.22"
            + "\n  - timeout: 50"
            + "\n  + timeout: 20"
            + "\n  + verbose: true"
            + "\n}";

        String actual = Differ.generate(file1, file2);
        assertEquals(expected, actual);
    }

    @Test
    void testGenerateYaml() throws Exception {
        String file1 = "src/test/resources/file1.yml";
        String file2 = "src/test/resources/file2.yml";

        String expected = "{"
            + "\n  - follow: false"
            + "\n    host: hexlet.io"
            + "\n  - proxy: 123.234.53.22"
            + "\n  - timeout: 50"
            + "\n  + timeout: 20"
            + "\n  + verbose: true"
            + "\n}";

        String actual = Differ.generate(file1, file2);
        assertEquals(expected, actual);
    }

    @Test
    void testJsonYamlMixed() throws Exception {
        String file1 = "src/test/resources/file1.json";
        String file2 = "src/test/resources/file2.yml";

        String expected = "{"
            + "\n  - follow: false"
            + "\n    host: hexlet.io"
            + "\n  - proxy: 123.234.53.22"
            + "\n  - timeout: 50"
            + "\n  + timeout: 20"
            + "\n  + verbose: true"
            + "\n}";

        String actual = Differ.generate(file1, file2);
        assertEquals(expected, actual);
    }
}
