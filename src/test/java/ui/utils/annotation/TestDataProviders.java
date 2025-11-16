package ui.utils.annotation;


import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import ui.utils.TestDataProvider;

/**
 * Annotation-driven DataProvider for JSON test data.
 *
 * This lives under utils.annotation so you can opt-in to the explicit approach
 * later without changing the convention-based setup.
 *
 * Usage:
 *
 * @Test(dataProvider = "jsonDataProvider", dataProviderClass = utils.annotation.TestDataProviders.class)
 * @JsonData("/src/test/resources/data/positive_test_data/loginData.json")
 * public void myTest(java.util.HashMap<String,String> input) { ... }
 *
 * The provider simply reads the @JsonData value on the test Method and delegates
 * parsing to the shared utils.TestDataProvider.getData(...).
 */
public class TestDataProviders {

    @DataProvider(name = "jsonDataProvider")
    public static Object[][] jsonDataProvider(Method m) throws IOException {
        JsonData jsonData = m.getAnnotation(JsonData.class);
        if (jsonData == null) {
            throw new IllegalArgumentException(
                    "Missing @JsonData on method '" + m.getName() + "'. Please annotate the test method with @JsonData(\"/path/to/file.json\")");
        }

        String path = jsonData.value();
        // delegate to the shared JSON utility
        return TestDataProvider.getData(path);
    }
}
