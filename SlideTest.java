import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class SlideTest {

    public AndroidDriver driver;
    private WebDriverWait wait;
    private String mainContainer = "com.alibaba.aliexpresshd:id/container_main";
    private String sideBar = "com.alibaba.aliexpresshd:id/navdrawer";

    @BeforeMethod
    public void setup () throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Google Pixel 9.0 -US");
        capabilities.setCapability("appPackage", "com.alibaba.aliexpresshd");
        capabilities.setCapability("forceMjsonwp", "True");
        capabilities.setCapability("bitbar_project", "Test 2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("bitbar_target", "Android");
        capabilities.setCapability("bitbar_testrun", "Test1");
        capabilities.setCapability("bitbar_device", "Google Pixel 9.0 -US");
        capabilities.setCapability("bitbar_app", "09c7b8f3-7eac-4a2f-a445-aeaa87a00ffd/Ali.apk");
        capabilities.setCapability("bitbar_apiKey", "uqdTw92YL5L06pym6nuGMrarfFSrpTge");
        driver = new AndroidDriver<MobileElement>(new URL("http://appium.bitbar.com/wd/hub"), capabilities);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void sideBarTest() throws InterruptedException {
        driver.startActivity(new Activity("com.alibaba.aliexpresshd", "com.aliexpress.module.home.MainActivity"));
        //Open sidebar
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id(mainContainer)));
        new TouchAction(driver)
                .longPress(PointOption.point(26, 920))
                .moveTo(PointOption.point(700, 920))
                .release()
                .perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id(sideBar)));

        //Close sidebar
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id(mainContainer)));
        new TouchAction(driver)
                .longPress(PointOption.point(824, 920))
                .moveTo(PointOption.point(26, 920))
                .release()
                .perform();

        wait.until(ExpectedConditions.invisibilityOfElementLocated
                (By.id(sideBar)));
    }


    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}