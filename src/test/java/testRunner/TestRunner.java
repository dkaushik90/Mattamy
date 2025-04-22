package testRunner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
		features= "C://Users//dkaushik//eclipse-workspace//bdd_mattamyhomes//Features",
	  glue ="stepDefinations",
	  plugin = {"pretty","html:target/cucumber-reports/reports.html",
			  "json:target/cucumber-reports/reports.json",
			 "junit:target/cucumber-reports/junit.xml" },
		tags="@smoke and not @ignore",
		dryRun=false,//set to true to check missing steps without executing
		monochrome=true //improves console readability
)

public class TestRunner {

}
