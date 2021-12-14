package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.CreateaAccTest;

public class SingInPage {
    @FindBy(id = "email_create")
    public WebElement enterEmail;
    @FindBy(id = "SubmitCreate")
    private WebElement creatAccButton;
    @FindBy(id="create_account_error")
    public WebElement errMessage;

    protected WebDriver driver;
    protected WebDriverWait wait;

    public SingInPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public CreateAnAccount fillInCreatAcc(String mail){
        enterEmail.sendKeys(mail);
        creatAccButton.click();
        return new CreateAnAccount(driver);
    }

    public void fillInInvalidEmail(String email){
        enterEmail.sendKeys(email);
        creatAccButton.click();
    }

}
