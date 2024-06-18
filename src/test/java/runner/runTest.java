package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "json:target/surefire-reports/cucumber.json",
                "html:target/cucumber-html-report.html",
                "junit:target/surefire-reports/cucumber.xml"},
        glue = {"step"},
        features = {"src/test/resources/features"}
)

public class runTest {
}
