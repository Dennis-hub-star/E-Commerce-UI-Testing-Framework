package ui.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to declare the JSON file path to use as test data for a TestNG test method.
 *
 * Usage example:
 *
 * @Test(dataProvider = "jsonDataProvider", dataProviderClass = TestDataProviders.class)
 * @JsonData("/src/test/resources/data/positive_test_data/loginData.json")
 * public void myTest(HashMap<String,String> input) { ... }
 *
 * The path is expected to be relative to the project root (System.getProperty("user.dir")
 * will be prepended by the provider), but TestDataProvider supports passing an absolute
 * path as well.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JsonData {
    /** The path to the JSON file to use for this test's data. */
    String value();
}
