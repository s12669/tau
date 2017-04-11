package com.example.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;


public class LoginPage extends WebDriverPage {

    public LoginPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void open() {
        get("http://szuflandia.pjwstk.edu.pl/~s12669/TAU/panel/index.php?page=admin");
        manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    public void enterCorrectPassword() {
        WebElement e = findElement(By.name("password"));
        e.sendKeys("haslo");
    }

    public void enterIncorrectPassword() {
        WebElement e = findElement(By.name("password"));
        e.sendKeys("2345678");
    }

    public void logIn() {
        WebElement e = findElement(By.name("zaloguj"));
        e.click();
    }

    public WebElement logInSuccess() {
        //assertNotNull(element);
        return findElement(By.name("wyloguj"));
    }

    public String logInFail(){
        //assertTrue(html.contains("Niepoprawne hasło!"));
        return getPageSource();
    }
}
