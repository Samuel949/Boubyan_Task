import Pages.LoginPage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class LoginTests extends BaseTest{

    LoginPage loginPage;
    private JsonNode testData;
    String username;
    String password;

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        testData = objectMapper.readTree(new File("src/main/resources/testData.json"));
        loginPage = new LoginPage(driver);
        //retrieving test data from Json file by test case name
        username = testData.path("LOGIN_USERS").path(method.getName()).path("username").asText();
        password = testData.path("LOGIN_USERS").path(method.getName()).path("password").asText();
    }

    @Test
    public void testSuccessfulLogin() {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isWelcomeMessageDisplayed(), "Welcome message is not displayed.");
    }

    @Test
    public void testLockedUserLogin() {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message is not displayed for locked user.");
    }

    @Test
    public void testNoUserDetailsLogin() {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message is not displayed for no user details.");
    }

    @Test
    public void testNoPasswordLogin() {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message is not displayed for no password.");
    }


    @Test
    public void testNoMatchLogin() {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message is not displayed for no matching user.");
    }
}
