package ui.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility that reads a JSON array file and converts it into an Object[][] suitable
 * for TestNG DataProviders. Each row contains a single HashMap<String,String>.
 *
 * This implementation is tolerant: JSON values may be strings, numbers, booleans
 * or null. All values are converted to String via toString() (null -> empty string).
 */
public class TestDataProvider {

    /**
     * Read JSON file and return Object[][] where each element is a single Map<String,String>.
     * The provided path may be absolute or relative to project root (System.getProperty("user.dir")).
     */
    public static Object[][] getData(String relativeOrAbsolutePath) throws IOException {
        String path = relativeOrAbsolutePath;
        if (!new File(path).isAbsolute()) {
            path = System.getProperty("user.dir") + relativeOrAbsolutePath;
        }

        String jsonContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        // Read into a list of maps with Object values to be tolerant of booleans/numbers
        List<Map<String, Object>> rawData = mapper.readValue(jsonContent,
                new TypeReference<List<Map<String, Object>>>() {
                });

        // Convert to list of Map<String,String> by calling toString() on values
        List<HashMap<String, String>> data = new ArrayList<>();
        for (Map<String, Object> row : rawData) {
            HashMap<String, String> stringRow = new HashMap<>();
            for (Map.Entry<String, Object> e : row.entrySet()) {
                Object v = e.getValue();
                stringRow.put(e.getKey(), v == null ? "" : v.toString());
            }
            data.add(stringRow);
        }

        Object[][] prepared = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            prepared[i][0] = data.get(i);
        }

        return prepared;
    }
}