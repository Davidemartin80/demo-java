package com.realdevice.unifiedplatform;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

public class IOSNativeAppUnifiedPlatformTest {
    @Rule
    public TestName name = new TestName() {
        public String getMethodName() {
            return String.format("%s", super.getMethodName());
        }
    };
    private AppiumDriver<MobileElement> driver;

    public AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

    @Before
    public void setUp() throws MalformedURLException {
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("appiumVersion", "1.17.1");
        capabilities.setCapability("idleTimeout", "90");
        capabilities.setCapability("noReset", "true");
        capabilities.setCapability("newCommandTimeout", "90");
        capabilities.setCapability("language", "en");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "13.2");
        capabilities.setCapability("deviceName", "iPhone 11 Pro Max");
        capabilities.setCapability("name", name.getMethodName());

        /**
         * You need to upload your own Native Mobile App to Sauce Storage!
         * https://wiki.saucelabs.com/display/DOCS/Uploading+your+Application+to+Sauce+Storage
         *
         * You can use either storage:<app-id> or storage:filename=
         * */
        //
        //
        //capabilities.setCapability("app", "storage:" + "bc21cdfc-0920-4288-9713-6a119c2f3b4d");
        capabilities.setCapability("app", "storage:filename=" + "iOS.RealDevice.SauceLabs.Mobile.Sample.app.2.3.0.ipa");

        driver = new IOSDriver(
                new URL("https://" + System.getenv("SAUCE_USERNAME") + ":" +
                        System.getenv("SAUCE_ACCESS_KEY") +
                        "@ondemand.us-west-1.saucelabs.com/wd/hub"),
                capabilities);
    }


    @After
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    @Test
    public void shouldOpenApp() {
        assertTrue(getDriver().findElement(By.id("test-LOGIN")).isDisplayed());
    }
}
