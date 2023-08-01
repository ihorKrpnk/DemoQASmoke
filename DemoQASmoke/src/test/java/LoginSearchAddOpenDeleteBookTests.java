import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;

public class LoginSearchAddOpenDeleteBookTests {

    protected WebDriver driver;
    private String url = "https://demoqa.com/login";

    public static final String USER_NAME = "demoqatest2";
    public static final String PASSWORD = "Demoqatest1!";

    @BeforeTest
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

   /* @TmsLink("39342")
    @Test(description = "Register New User [Test ID - C39342]")
    public void testRegisterNewUser() {
        //OpenLoginPage and navigate to Register Page
        WebElement newUserButton = driver.findElement(By.id("newUser"));
        newUserButton.click();
        //On Register Page fill all data
        WebElement firstNameField = driver.findElement(By.id("firstname"));
        firstNameField.sendKeys("DemoQA");

        WebElement lastNameField = driver.findElement(By.id("lastname"));
        lastNameField.sendKeys("Test");

        WebElement userNameField = driver.findElement(By.id("userName"));
        userNameField.sendKeys("demoqatest6");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Demoqatest1!");

        WebElement registerButton = driver.findElement(By.id("register"));
        registerButton.click();

        // wait till re-captcha is present
        //sleepUninterruptibly(2, TimeUnit.SECONDS);

        //click re-captcha checkbox
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border"))).click();

        //10 seconds timeout for manually passing re-captcha verification
        sleepUninterruptibly(20, TimeUnit.SECONDS);

        WebElement registerButton1 = driver.findElement(By.id("register"));
        registerButton1.click();

        *//*Actions actions = new Actions(driver);
        actions.moveToElement(registerButton);
        actions.click();*//*

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();

    }*/

    @TmsLink("39343")
    @Test(description = "Log in with Existing User [Test ID - C39343]", priority = 1)
    public void testLogInWithExistingUser() {
        //Open Login Page, fill data and log in
        WebElement userNameField = driver.findElement(By.id("userName"));
        userNameField.sendKeys(USER_NAME);
        System.out.println("User name field is filled");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(PASSWORD);
        System.out.println("Password field is filled");

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        System.out.println("'Login' button is clicked");

        sleepUninterruptibly(3, TimeUnit.SECONDS);

        WebElement profilePage = driver.findElement(By.xpath("//div[contains(text(),'Profile')]"));
        profilePage.isDisplayed();
        System.out.println("User is logged in, 'Profile' page is opened");

        WebElement logoutButton = driver.findElement(By.id("submit"));
        logoutButton.click();
        sleepUninterruptibly(3, TimeUnit.SECONDS);
        System.out.println("User is logged out");
    }

    @TmsLinks({@TmsLink("39344"), @TmsLink("39345")})
    @Test(description = "Add Book To collection, Search Book [Test ID - C39344, C39345]", priority = 2)
    public void testAddBookToCollectionSearchBook() {
        WebElement userNameField = driver.findElement(By.id("userName"));
        userNameField.sendKeys(USER_NAME);
        System.out.println("User name field is filled");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(PASSWORD);
        System.out.println("Password field is filled");

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        System.out.println("'Login' button is clicked");

        sleepUninterruptibly(1, TimeUnit.SECONDS);

        WebElement profilePage = driver.findElement(By.xpath("//div[contains(text(),'Profile')]"));
        profilePage.isDisplayed();
        System.out.println("User is logged in, 'Profile' page is opened");

        //scrolling down to view 'gotoStore' button
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");

        WebElement gotoStoreButton = driver.findElement(By.id("gotoStore"));
        gotoStoreButton.click();
        System.out.println("Click 'go to Store' button");

        sleepUninterruptibly(3, TimeUnit.SECONDS);

        WebElement searchBox = driver.findElement(By.id("searchBox"));
        searchBox.sendKeys("Git");
        System.out.println("Enter search request 'Git'");

        sleepUninterruptibly(2, TimeUnit.SECONDS);

        WebElement gitPocketGuideBook = driver.findElement(By.xpath("//a[contains(text(),'Git')]"));
        String gitPocketGuideBookTextExpected = "Git";
        String gitPocketGuideBookTextActual = gitPocketGuideBook.getText().trim();
        gitPocketGuideBookTextActual.contains(gitPocketGuideBookTextExpected);
        System.out.println("Response contains 'Git'");

        gitPocketGuideBook.click();
        sleepUninterruptibly(2, TimeUnit.SECONDS);
        System.out.println("Click 'Git Pocket Guide Book'");

        WebElement titleBook = driver.findElement(By.xpath("//label[contains(text(),'Git')]"));
        titleBook.getText().contains("Git");
        System.out.println("Title contains 'Git'");

        js.executeScript("window.scrollBy(0,250)", "");//scrolling down to view 'addNewRecord' button

        WebElement addGit;
        addGit = new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='text-right fullButton']//button[@id='addNewRecordButton']")));
        addGit.click();
        System.out.println("Add book");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
        System.out.println("Alert accepted");

        sleepUninterruptibly(2, TimeUnit.SECONDS);

        WebElement profileMenu = driver.findElement(By.xpath("//span[contains(text(),'Profile')]"));
        profileMenu.click();
        sleepUninterruptibly(2, TimeUnit.SECONDS);
        System.out.println("Click 'Profile' menu");

        WebElement gitPocketGuideBookInProfile = driver.findElement(By.xpath("//a[contains(text(),'Git')]"));
        gitPocketGuideBookInProfile.isDisplayed();
        System.out.println("Book is present");

        WebElement logoutButton = driver.findElement(By.id("submit"));
        logoutButton.click();
        sleepUninterruptibly(3, TimeUnit.SECONDS);
        System.out.println("User is logged out");
    }

    @TmsLink("39346")
    @Test(description = "Open book from collection [Test ID - C39346]", priority = 3)
    public void testOpenBookFromCollection() {
        WebElement userNameField = driver.findElement(By.id("userName"));
        userNameField.sendKeys(USER_NAME);
        System.out.println("User name field is filled");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(PASSWORD);
        System.out.println("Password field is filled");

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        System.out.println("'Login' button is clicked");

        sleepUninterruptibly(3, TimeUnit.SECONDS);

        WebElement gitPocketGuideBookInProfile = driver.findElement(By.xpath("//a[contains(text(),'Git')]"));
        gitPocketGuideBookInProfile.click();
        System.out.println("Open 'Git Pocket Guide' book");

        sleepUninterruptibly(3, TimeUnit.SECONDS);

        WebElement titleBook = driver.findElement(By.xpath("//label[contains(text(),'Git')]"));
        titleBook.getText().contains("Git");
        System.out.println("Book is opened, title contains 'Git'");

        WebElement backToBookStoreButton = driver.findElement(By.id("addNewRecordButton"));
        backToBookStoreButton.click();
        System.out.println("Click 'Back To Book Store' button");

        sleepUninterruptibly(2, TimeUnit.SECONDS);

        WebElement logoutButton = driver.findElement(By.id("submit"));
        logoutButton.click();
        sleepUninterruptibly(3, TimeUnit.SECONDS);
        System.out.println("User is logged out");
    }

    @TmsLink("39347")
    @Test(description = "Delete book from collection [Test ID - C39347]", priority = 4)
    public void testDeleteBookFromCollection() {
        WebElement userNameField = driver.findElement(By.id("userName"));
        userNameField.sendKeys(USER_NAME);
        System.out.println("User name field is filled");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(PASSWORD);
        System.out.println("Password field is filled");

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        System.out.println("'Login' button is clicked");

        sleepUninterruptibly(3, TimeUnit.SECONDS);

        WebElement profilePage = driver.findElement(By.xpath("//div[contains(text(),'Profile')]"));
        profilePage.isDisplayed();
        System.out.println("User is logged in, 'Profile' page is opened");

        WebElement deleteGitPocketGuideBookInProfileButton = driver.findElement(By.id("delete-record-undefined"));
        deleteGitPocketGuideBookInProfileButton.click();
        System.out.println("Click 'Git Pocket Guide' book 'Delete' button");

        sleepUninterruptibly(3, TimeUnit.SECONDS);

        //Click dialog 'Ok' button
        WebElement dialogOkButton = driver.findElement(By.id("closeSmallModal-ok"));
        dialogOkButton.click();
        System.out.println("Click dialog 'Ok' button");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
        System.out.println("Alert accepted");

        sleepUninterruptibly(2, TimeUnit.SECONDS);

        //Text 'No rows found' is displayed
        WebElement noRowsFoundText = driver.findElement(By.xpath("//*[contains(text(),'No rows found')] "));
        noRowsFoundText.isDisplayed();
        System.out.println("Text 'No rows found' is displayed in the Table");

        WebElement logoutButton = driver.findElement(By.id("submit"));
        logoutButton.click();
        sleepUninterruptibly(3, TimeUnit.SECONDS);
        System.out.println("User is logged out");
    }


    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }


}
