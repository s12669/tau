package com.example;

import com.example.pages.LoginPage;
import org.jbehave.web.selenium.WebDriverProvider;

class Pages {

    private WebDriverProvider driverProvider;

    //Pages -- moze byc ich kilka
    private LoginPage loginPage;

    Pages(WebDriverProvider driverProvider) {
        super();
        this.driverProvider = driverProvider;
    }

    LoginPage loginpage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driverProvider);
        }
        return loginPage;
    }
}
