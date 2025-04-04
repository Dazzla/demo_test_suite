package runner;

import WebDriverHelpers.WebDriverFactory;
import WebDriverHelpers.WebDriverParameterResolver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(WebDriverParameterResolver.class)
public class BaseTest {

    private WebDriver driver;


    @BeforeAll
    void setUp(WebDriverParameterResolver parameterResolver) {
        this.driver = WebDriverFactory.createWebDriver();  // Or webDriverFactory.createWebDriver()
        parameterResolver.setDriver(driver);
    } // <-- Added missing closing brace


    @AfterAll
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
