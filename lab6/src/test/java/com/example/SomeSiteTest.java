package com.example;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

// UWAGA -- przerobilem dla phantomjs -- powinno dzialac na pracowni PJATK
public class SomeSiteTest {

    private static WebDriver driver;
    WebElement element;

    @BeforeClass
    public static void driverSetup() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability("takesScreenshot", true);
        //For Windows
        caps.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "windows/bin/phantomjs.exe"
        );
        //For Linux
        /*caps.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "linux/bin/phantomjs.exe"
        );*/
        driver = new PhantomJSDriver(caps);
    }

    @AfterClass
    public static void cleanup() {
        driver.quit();
    }

    @Test
    public void clickAndSelectTab() throws IOException {
        driver.get("http://szuflandia.pjwstk.edu.pl/helpdesk.html");
        // tab
        WebElement e;
        e = driver.findElement(By.partialLinkText("Tags"));
        assertTrue(!e.getAttribute("class").contains("tabSelected"));
        File screenshot =
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("bss1.png"));
        e.click();
        assertTrue(e.getAttribute("class").contains("tabSelected"));
        screenshot =
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("bss2.png"));
        assertNotNull(e);
    }
}
