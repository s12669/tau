package com.example;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

public class SiteSteps {

    private final com.example.Pages pages;

    SiteSteps(com.example.Pages pages) {
        this.pages = pages;
    }

    @Given("user is on logging page")
    public void userOnLoggingPage() {
        pages.loginpage().open();
    }

    @When("user enters correct password")
    public void userEntersCorrectPassword() {
        pages.loginpage().enterCorrectPassword();
        pages.loginpage().logIn();
    }

    @When("user enters incorrect password")
    public void userEntersIncorrectPassword() {
        pages.loginpage().enterIncorrectPassword();
        pages.loginpage().logIn();
    }

    @Then("the user should log in")
    public void logInSuccessful() {
        assertNotNull(pages.loginpage().logInSuccess());
        pages.loginpage().logInSuccess().click();
    }

    @Then("the user should not log in")
    public void logInUnsuccessful() {
        assertTrue(!pages.loginpage().logInFail().contains("Zalogowanie poprawne."));
    }

    @Given("user is logged in")
    public void userIsLoggedIn(){
        pages.loginpage().open();
        pages.loginpage().enterCorrectPassword();
        pages.loginpage().logIn();
    }

    @When("user enters the logging page")
    public void userEntersLoggingPage() {
        pages.loginpage().open();
    }

    @Then("the user should already be logged in")
    public void userAlreadyLoggedIn() {
        assertNotNull(pages.loginpage().loggedIn());
    }
}
