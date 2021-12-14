package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

public class CreateAnAccount {
    @FindBy(className = "radio")
    private List<WebElement> radioButtons;
    @FindBy(id = "customer_firstname")
    private WebElement enterFName;
    @FindBy(id = "customer_lastname")
    private WebElement enterLName;
    @FindBy(id = "passwd")
    private WebElement enterPassword;
    @FindBy(id = "firstname")
    private WebElement enterAddressFN;
    @FindBy(id = "lastname")
    private WebElement enterAddressLN;
    @FindBy(id = "company")
    private WebElement company;
    @FindBy(id = "address1")
    private WebElement address;
    @FindBy(id = "address2")
    private WebElement address2;
    @FindBy(id = "city")
    private WebElement city;
    @FindBy(id = "postcode")
    public WebElement poscode;
    @FindBy(id = "other")
    private WebElement other;
    @FindBy(id = "phone")
    private WebElement homePhone;
    @FindBy(id = "phone_mobile")
    private WebElement phone;
    @FindBy(id = "alias")
    private WebElement alias;
    @FindBy(id = "days")
    private WebElement days;
    @FindBy(id = "months")
    private WebElement months;
    @FindBy(id = "years")
    private WebElement years;
    @FindBy(id = "newsletter")
    private WebElement newsletter;
    @FindBy(id = "optin")
    private WebElement option;
    @FindBy(id = "id_state")
    public WebElement state;
    @FindBy(id = "id_country")
    private WebElement country;
    @FindBy(id = "submitAccount")
    private WebElement submitButton;
    @FindBy(xpath = "//*[contains(@class, 'alert alert-danger')][1]")
    public WebElement errorBoxMassage;

    protected WebDriver driver;

    public CreateAnAccount(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void fillInCreateAccount(int pers ,String fname,String lname, String password, String aFname,String aLname,String Company, String Address,String Address2, String City, String zipcode,String additionalInformation, String HomePhone,String mobilePhone,String assingAnAddress){
        if(pers == 1){
            radioButtons.get(0).click();
        }
        else if(pers == 2){
            radioButtons.get(1).click();
        }
        else{
            System.out.println("No option availible");
        }
        enterFName.sendKeys(fname);
        enterLName.sendKeys(lname);
        enterPassword.sendKeys(password);
        enterAddressFN.sendKeys(aFname);
        enterAddressLN.sendKeys(aLname);
        company.sendKeys(Company);
        address.sendKeys(Address);
        address2.sendKeys(Address2);
        city.sendKeys(City);
        poscode.sendKeys(zipcode);
        other.sendKeys(additionalInformation);
        homePhone.sendKeys(HomePhone);
        phone.sendKeys(mobilePhone);
        alias.sendKeys(assingAnAddress);
        newsletter.click();
        option.click();

    }

    public void sumbitForm(){
        submitButton.click();
    }

    public void chooseState(String value){
    Select select = new Select(state);
    select.selectByVisibleText(value);
    }

    public void choseDay(String value){
        Select select = new Select(days);
        select.selectByValue(value);
    }

    public void choseMounth(String value){
        Select select = new Select(months);
        select.selectByValue(value);
    }

    public void choseYear(String value){
        Select select = new Select(years);
        String years= value;
        if(Integer.parseInt(years) < (2004)){
            select.selectByValue(value);
        }
//        else {
//
//        }

//        select.selectByValue(value);
    }

    public void choseCountry(String value){
        Select select = new Select(country);
        select.selectByVisibleText(value);
    }
}
