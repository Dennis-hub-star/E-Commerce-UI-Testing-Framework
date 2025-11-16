package ui.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Optional annotation to declare a JSON test-data file for a TestNG test method.
 *
 * This file is provided under utils.annotation as a "drop-in" for teams who
 * prefer explicit per-test data files instead of the convention-based approach.
 *
 * Usage example:
 *
 * @Test(dataProvider = "jsonDataProvider", dataProviderClass = utils.annotation.TestDataProviders.class)
 * @JsonData("/src/test/resources/data/positive_test_data/loginData.json")
 * public void myTest(HashMap<String,String> data) { ... }
 *
 * The path may be absolute or project-root-relative (project root is System.getProperty("user.dir")).
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JsonData {
    String value();
}
