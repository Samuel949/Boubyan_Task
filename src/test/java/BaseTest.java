import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    public AppiumDriver driver;


    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("automationName","UIAutomator2");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformVersion","15.0");
        capabilities.setCapability("apps",System.getProperty("user.dir")+"Android-MyDemoAppRN.apk");
        //If we are testing an app already installed we should use Package and activity for the key at the capabilities
        //get both capabilities from the apkDemo from the device
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"),capabilities);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
