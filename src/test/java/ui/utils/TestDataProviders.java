package ui.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

/**
 * Convention-based DataProvider for JSON test data.
 *
 * Rules / usage:
 *  - Place JSON files using the convention:
 *      src/test/resources/data/{TestClassSimpleName}/{testMethodName}.json
 *    Example: src/test/resources/data/RegisterAndLogin/orderDetailsConfirmationTest.json
 *
 *  - Each JSON file should be an array of objects, e.g.:
 *      [ {"email":"a@b.com","password":"p"}, { ... } ]
 *
 *  - Test method signature should accept a single Map/HashMap element per row:
 *      @Test(dataProvider = "conventionJsonProvider", dataProviderClass = utils.TestDataProviders.class)
 *      public void orderDetailsConfirmationTest(HashMap<String,String> input) { ... }
 *
 *  - This provider delegates parsing to utils.TestDataProvider.getData(...).
 *
 * Note: the previous annotation-based approach (using @JsonData) has been moved to
 * the utils.annotation package for optional future use (see utils/annotation).
 */
public class TestDataProviders {

    @DataProvider(name = "conventionJsonProvider")
    public static Object[][] conventionJsonProvider(Method m) throws IOException {
        String className = m.getDeclaringClass().getSimpleName();
        String methodName = m.getName();

        // Build the project-root relative path according to the convention above
        String relativePath = "/src/test/resources/data/" + className + "/" + methodName + ".json";

        // Delegate to the shared TestDataProvider utility which returns Object[][]
        return TestDataProvider.getData(relativePath);
    }

}