import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
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
    static Process p;

    static boolean APPIUM_STARTED = false;

    // This method Is responsible for starting appium server.
    public static void appiumStart() throws IOException, InterruptedException {
        // Execute command string to start appium server.
        p = Runtime.getRuntime().exec("appium");
        final InputStream stream = p.getInputStream();
        new Thread(new Runnable() {
            public void run() {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(stream));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        if (line.contains("Appium REST http interface listener started on 0.0.0.0:4723")) {
                            System.out.println("Appium server Is started now.");
                            APPIUM_STARTED = true;
                        }
                    }
                } catch (Exception e) {
                    // TODO
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            // ignore
                        }
                    }
                }
            }
        }).start();

        while (!APPIUM_STARTED) {
            System.out.println("not started yet");
            Thread.sleep(1000);
        }
    }

    @BeforeClass
    public static void setUp() throws Exception {
        // appiumStart();

        System.out.println("Der Test wird in Appium gestartet!!!!");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability(CapabilityType.PLATFORM, "Android");
        capabilities.setCapability("platformVersion", "7");
        capabilities.setCapability("autoGrantPermissions", "true");

        File file = new File("./adesso.spesenverwaltung.apk");
        System.out.println("file.exists() = " + file.exists());

        capabilities.setCapability("app", file.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://appium:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        List<WebElement> userAndPass = driver.findElementsByClassName("android.widget.EditText");
        //user
        userAndPass.get(0).sendKeys("test");
        //pw
        userAndPass.get(1).sendKeys("test");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    /*
    @AfterClass
    public static void tearDown() {
        p.destroy();
        driver.quit();
    }
    */

    @Test
    public void loginStep() throws Exception {
        WebElement login = driver.findElementByClassName("android.widget.Button");
        login.click();

        System.out.println("Lese Elemente !" + driver.getPageSource());
        List<WebElement> alerts = driver.findElementsByAccessibilityId("android:id/alertTitle");
        assertThat(alerts.size(), is(0));
    }

}

