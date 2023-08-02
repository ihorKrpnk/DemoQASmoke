import TestRail.TestRailApiCalls;
import com.google.common.base.Throwables;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static TestRail.TestRailApiCalls.addResultToCase;
import static org.testng.ITestResult.*;

public class BaseTest {

    protected WebDriver driver;
    private String url = "https://demoqa.com/login";

    public static final String USER_NAME = "demoqatest2";
    public static final String PASSWORD = "Demoqatest1!";
    public static String AUTOMATION_RUN_ID = "";
    public static final String TEST_RAIL_PROJECT_ID = "14";
    public static final String TEST_RAIL_SUITE_ID = "204";

    @BeforeSuite
    public void beforeSuite() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Demo Automation Run");
        data.put("suite_id", TEST_RAIL_SUITE_ID);
        data.put("include_all", true);
        AUTOMATION_RUN_ID = TestRailApiCalls.addTestRun(TEST_RAIL_PROJECT_ID, data);
    }

    @BeforeTest
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }


    @AfterTest
    public void tearDown(ITestResult result, Method method){
        driver.quit();
        Map<String, Object> data = new HashMap<>();
        String testCaseId = getTestCaseId(method);
        String runID = AUTOMATION_RUN_ID;
        if (result.getStatus() == FAILURE) {
            data.put("status_id", 5);
            data.put("comment",  " \n" + Throwables.getStackTraceAsString(result.getThrowable()));
            addResultToCase(runID, testCaseId, data);
        } else if (result.getStatus() == SUCCESS) {
            data.put("status_id", 1);
            addResultToCase(runID, testCaseId, data);
        } else if (result.getStatus() == SKIP) {
            data.put("status_id", 8);
            addResultToCase(runID, testCaseId, data);
        }
    }

    private String  getTestCaseId(Method method) {
        return  method.getAnnotation(TmsLink.class).value();
    }

}
