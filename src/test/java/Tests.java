import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;


public class Tests {
    WebDriver driver;
    JavascriptExecutor js;

    @Before
    public void prepareDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(3));

        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testCase() throws InterruptedException {

        driver.get("https://www.tunisianet.com.tn/");

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        String firstName = "tesnime";
        String lastName = "sb";
        String email = "stesnime@yahoo.com";
        String password = "password";
        String birthday = "26-05-1999";

        // create an account
        Thread.sleep(1500);
        WebElement userInfoDropdown = driver.findElement(By.cssSelector("#_desktop_user_info > div > div > svg"));
        userInfoDropdown.click();

        Thread.sleep(1500);
        WebElement signinButton = driver.findElement(By.cssSelector(".user-down > li > a > span"));
        signinButton.click();

        Thread.sleep(1500);
        WebElement createAccountButton = driver.findElement(By.className("no-account"));
        Assert.assertEquals("Pas de compte ? Créez-en un", createAccountButton.findElement(By.cssSelector("*")).getText());
        createAccountButton.click();

        Thread.sleep(1500);
        List<WebElement> genderOptions = driver.findElements(By.className("custom-radio"));
        genderOptions.get(0).click();

        Thread.sleep(1500);
        List<WebElement> signupFormFields = driver.findElements(By.cssSelector("input.form-control"));

        signupFormFields.get(1).sendKeys(firstName);
        signupFormFields.get(2).sendKeys(lastName);
        signupFormFields.get(3).sendKeys(email);
        signupFormFields.get(4).sendKeys(password);
        signupFormFields.get(5).sendKeys(dateFormatter.format(birthday));

        js.executeScript("window.scrollBy(0,250)", ""); // Scroll down

        Thread.sleep(1500);
        WebElement signupButton = driver.findElement(By.className("form-control-submit"));
        signupButton.click();

        Thread.sleep(1500);
        userInfoDropdown = driver.findElement(By.cssSelector("#_desktop_user_info > div > div > svg"));
        userInfoDropdown.click();

        Thread.sleep(1500);
        WebElement signoutButton = driver.findElement(By.className("logout"));
        signoutButton.click();

        // login
        Thread.sleep(1500);
        userInfoDropdown = driver.findElement(By.cssSelector("#_desktop_user_info > div > div > svg"));
        userInfoDropdown.click();

        Thread.sleep(1500);
        signinButton = driver.findElement(By.cssSelector(".user-down > li > a > span"));
        signinButton.click();

        Thread.sleep(1500);
        WebElement emailTextField = driver.findElement(By.cssSelector(".form-group > div > input"));
        emailTextField.sendKeys(email);

        Thread.sleep(1500);
        WebElement pwdTextField = driver.findElement(By.cssSelector(".form-group > div > div > input"));
        pwdTextField.sendKeys(password);

        Thread.sleep(1500);
        WebElement submitButton = driver.findElement(By.id("submit-login"));
        submitButton.click();

        // look for a product and order it (Laptop)
        Thread.sleep(1500);
        WebElement searchBar = driver.findElement(By.className("search_query"));
        searchBar.sendKeys("PC portable");

        Thread.sleep(1500);
        WebElement searchButton = driver.findElement(By.cssSelector("#sp-btn-search > button"));
        searchButton.click();

        Thread.sleep(1500);
        List<WebElement> productsTitle = driver.findElements(By.className("product-title"));
        productsTitle.get(0).click();

        Thread.sleep(1500);
        WebElement addToCartButton = driver.findElement(By.className("add-to-cart"));
        addToCartButton.click();

        Thread.sleep(1500);
        WebElement orderButton = driver.findElement(By.cssSelector("a.btn-block"));
        orderButton.click();
    }

    @After
    public void quitDriver() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}
