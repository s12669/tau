package com.example;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.JVM)
public class LogTest {

    private static ChromeDriver driver;
    WebElement element;
    String login = "1234";

    @BeforeClass
    public static void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "/Users/macie/Downloads/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void panelExistsTest() {
        driver.get("http://localhost/panel/index.php");
        element = driver.findElement(By.name("panel"));
        assertNotNull(element);
    }

    @Test
    public void loginFormScreenshotTest() {
        driver.get("http://localhost/panel/index.php");
        element = driver.findElement(By.name("panel"));
        assertNotNull(element);
        element.click();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        assertNotNull(screenshot);
        try {
            FileUtils.copyFile(screenshot, new File("./target/screenshots/FormularzLogowania.png"));
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void notLoggedTest() {
        Cookie login = driver.manage().getCookieNamed("login");
        Cookie logged = driver.manage().getCookieNamed("logged");
        String url = driver.getCurrentUrl();
        assertTrue(driver.manage().getCookies().size() <= 1); // 1 - autogenerowany cookie z ID sesji
        assertNull(login);
        assertNull(logged);
        assertFalse(url.contains("logged"));
    }

    @Test
    public void loginInorrectTest() {
        driver.get("http://localhost/panel/index.php");
        element = driver.findElement(By.name("panel"));
        assertNotNull(element);
        element.click();
        WebElement form = driver.findElement(By.name("password"));
        element = driver.findElement(By.name("zaloguj"));
        form.sendKeys("553535");

        assertEquals("553535", form.getAttribute("value"));
        element.click();

        String html = driver.getPageSource();
        assertTrue(html.contains("Niepoprawne hasło!"));

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        assertNotNull(screenshot);
        try {
            FileUtils.copyFile(screenshot, new File("./target/screenshots/NieprawidłoweHasło.png"));
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false);
        }

    }

    @Test
    public void loginCorrectTest() {
        driver.get("http://localhost/panel/index.php");
        element = driver.findElement(By.name("panel"));
        assertNotNull(element);
        element.click();
        WebElement form = driver.findElement(By.name("password"));
        element = driver.findElement(By.name("zaloguj"));
        form.sendKeys("haslo");

        assertEquals("haslo", form.getAttribute("value"));
        element.click();

        String html = driver.getPageSource();
        assertTrue(html.contains("Zalogowanie poprawne."));

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        assertNotNull(screenshot);
        try {
            FileUtils.copyFile(screenshot, new File("./target/screenshots/Zalogowano.png"));
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false);
        }

    }

    @Test
    public void logoutTest() {
        driver.get("http://localhost/panel/index.php?page=admin");
        element = driver.findElement(By.name("wyloguj"));
        assertNotNull(element);
        element.click();

        Boolean logged = driver.getCurrentUrl().contains("admin");
        assertFalse(logged);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        assertNotNull(screenshot);
        try {
            FileUtils.copyFile(screenshot, new File("./target/screenshots/Wylogowanie.png"));
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false);
        }

    }

    @Test
    public void javascriptTest() {
        JavascriptExecutor jsEx = (JavascriptExecutor) driver;
        assertNotNull(jsEx);
        jsEx.executeScript("function alertPop() { alert('success'); }; alertPop()");
        Alert alert = driver.switchTo().alert();
        assertEquals("success", alert.getText());
        alert.dismiss();
    }

    @Test
    public void jQueryTest() {
        driver.get("http://localhost/logowanie/");
        element = (WebElement) ((JavascriptExecutor) driver).executeScript("return $('.img')[0]");
        assertNull(element);
    }


    @AfterClass
    public static void cleanup() {
        driver.quit();
    }
}