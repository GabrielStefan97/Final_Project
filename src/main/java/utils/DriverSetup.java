package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverSetup {

    public static WebDriver driver;

    public static ChromeDriver setDriver(){
        System.setProperty("webdriver.chrome.driver", "src\\resources\\chromedriver.exe");
        return new ChromeDriver();
    }

    public static void driverInit(){
        driver = setDriver();
    }

}
