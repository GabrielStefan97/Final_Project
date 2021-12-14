package tests;

import helpers.RandomEmail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CreateAnAccount;
import pages.SingInPage;
import utils.DriverSetup;

import java.time.Duration;

import static com.google.common.base.CharMatcher.isNot;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.*;


public class CreateaAccTest {
    @BeforeEach
    public void setUP(){
        DriverSetup.driverInit();
        DriverSetup.driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }

    protected WebDriver driver;

    @Test
    public void tryValidEmail(){
        SingInPage singin = new SingInPage(DriverSetup.driver);
        DriverSetup.driver.manage().window().maximize();
        String email = RandomEmail.generateRandomEmail();
        new WebDriverWait(DriverSetup.driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("email_create")));

        singin.fillInCreatAcc(email);
        new WebDriverWait(DriverSetup.driver,Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation"));

        assertThat(DriverSetup.driver.getCurrentUrl(),is("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation"));

    }

    @Test
    public void tryInvalidEmail(){
        DriverSetup.driver.manage().window().maximize();
        SingInPage singin2 = new SingInPage(DriverSetup.driver);
        new WebDriverWait(DriverSetup.driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("email_create")));
        singin2.fillInInvalidEmail("email1234");
        new WebDriverWait(DriverSetup.driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(singin2.errMessage));

          assertThat(singin2.errMessage.getText(),is("Invalid email address."));
    }

    @Test
    public void createInvalidAccount(){
        CreateAnAccount create = new CreateAnAccount(DriverSetup.driver);
        tryValidEmail();
        new WebDriverWait(DriverSetup.driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("submitAccount")));
        create.fillInCreateAccount(2,"","","", "", "","", "","","","","","","","");
        create.chooseState("-");
        create.choseDay("3");
        create.choseMounth("5");
        create.choseYear("2000");
        create.chooseState("-");
        create.sumbitForm();

        assertThat(create.errorBoxMassage.getText(),is("There are 8 errors\n"+
                "You must register at least one phone number.\n"+
                        "lastname is required.\n"+
                        "firstname is required.\n"+
                        "passwd is required.\n"+
                        "address1 is required.\n"+
                        "city is required.\n"+
                        "The Zip/Postal code you've entered is invalid. It must follow this format: 00000\n"+
                        "This country requires you to choose a State."));


    }

    @Test
    public void createValidAccount() {
        CreateAnAccount create = new CreateAnAccount(DriverSetup.driver);
        tryValidEmail();
        new WebDriverWait(DriverSetup.driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("submitAccount")));
        create.fillInCreateAccount(2, "Marcel", "Ionescu", "1234567", "Marcel", "Ionescu", "NumaBine", "Strada Mare", "Strada Mica", "Opatita", "12345", "Nu doresc a marturisi nimic", "0123456789", "0123456789", "asdasdasdasdasda");
        create.chooseState("Alabama");
        create.choseDay("3");
        create.choseMounth("5");
        create.choseYear("2000");
        create.choseCountry("United States");
        create.sumbitForm();
        assertThat(DriverSetup.driver.getCurrentUrl(),is("http://automationpractice.com/index.php?controller=my-account"));
    }

    @Test
    public void createInvalidAccountCountry() {
        CreateAnAccount create = new CreateAnAccount(DriverSetup.driver);
        tryValidEmail();
        new WebDriverWait(DriverSetup.driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("submitAccount")));
        create.fillInCreateAccount(2, "Marcel", "Ionescu", "1234567", "Marcel", "Ionescu", "NumaBine", "Strada Mare", "Strada Mica", "Opatita", "12345", "Nu doresc a marturisi nimic", "0123456789", "0123456789", "asdasdasdasdasda");
        create.chooseState("Alabama");
        create.choseDay("3");
        create.choseMounth("5");
        create.choseYear("2016");
        create.choseCountry("-");
        create.sumbitForm();
        assertThat(DriverSetup.driver.getCurrentUrl(),is(not("http://automationpractice.com/index.php?controller=my-account")));
    }

    @Test
    public void check18Years(){
        CreateAnAccount create = new CreateAnAccount(DriverSetup.driver);
        tryValidEmail();
        new WebDriverWait(DriverSetup.driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("submitAccount")));
        create.fillInCreateAccount(2, "Marcel", "Ionescu", "1234567", "Marcel", "Ionescu", "NumaBine", "Strada Mare", "Strada Mica", "Opatita", "12345", "Nu doresc a marturisi nimic", "0123456789", "0123456789", "asdasdasdasdasda");
        create.chooseState("Alabama");
        create.choseDay("3");
        create.choseMounth("5");
        create.choseYear("2016");
        create.choseCountry("United States");
        create.sumbitForm();

        assertThat(create.errorBoxMassage.getText(),is("There is 1 error\n" +
                "Invalid date of birth"));
    }
    @Test
    public void checkInvalidAliasLength() {
        CreateAnAccount create = new CreateAnAccount(DriverSetup.driver);
        tryValidEmail();
        new WebDriverWait(DriverSetup.driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("submitAccount")));
        create.fillInCreateAccount(2, "Marcel", "Ionescu", "1234567", "Marcel", "Ionescu", "NumaBine", "Strada Mare", "Strada Mica", "Opatita", "12345", "Nu doresc a marturisi nimic", "0123456789", "0123456789", "asdasdasdasdasdaasdasdasdasdasdasdaa");
        create.chooseState("Alabama");
        create.choseDay("3");
        create.choseMounth("5");
        create.choseYear("2000");
        create.choseCountry("United States");
        create.sumbitForm();
        assertThat(create.errorBoxMassage.getText(),is("There is 1 error\n" +
                "alias is too long. Maximum length: 32"));
    }

    @Test
    public void checkCapsVisibility(){
        CreateAnAccount create = new CreateAnAccount(DriverSetup.driver);
        tryValidEmail();
        new WebDriverWait(DriverSetup.driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("submitAccount")));
        create.choseCountry("-");
        new WebDriverWait(DriverSetup.driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfAllElements(create.state, create.poscode));
        assertFalse(create.state.isDisplayed());
        assertFalse(create.poscode.isDisplayed());

    }
    @AfterEach
    public void tearDown(){
        DriverSetup.driver.quit();
    }
}

