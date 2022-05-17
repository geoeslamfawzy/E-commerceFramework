package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\resources\\Features"
        ,glue= {"src/test/java/stepDefinitions"}
        ,plugin= {"pretty","html:target\\site\\cucumber-pretty", "json:target\\cucumber.json"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
