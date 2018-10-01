import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MobileIT {

    static AppiumDriver driver;
    static WebElement el;
    static String cssVal;
    static AndroidDriver androidDriver;

    @BeforeClass
    public static void setUp() throws Exception {
        System.out.println("Der Test wird in Appium gestartet!!!!");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "testDevice");
        capabilities.setCapability(CapabilityType.PLATFORM, "Android");
        capabilities.setCapability("platformVersion", "7");
        capabilities.setCapability("autoGrantPermissions", "true");

        File file = new File("/home/vagrant/projects/", "adesso.spesenverwaltung.apk");

        capabilities.setCapability("app", file.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        List<WebElement> userAndPass = driver.findElementsByClassName("android.widget.EditText");
        //user
        userAndPass.get(0).sendKeys("test");
        //pw
        userAndPass.get(1).sendKeys("test");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void loginStep() throws Exception {
        WebElement login = driver.findElementByClassName("android.widget.Button");
        login.click();

        System.out.println("Lese Elemente !" + driver.getPageSource());
        List<WebElement> alerts = driver.findElementsByAccessibilityId("android:id/alertTitle");
        assertThat(alerts.size(), is(0));
    }

}

