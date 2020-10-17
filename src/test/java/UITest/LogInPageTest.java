package UITest;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Test logIn page function
 *
 * @author E.Andreiko
 */
public class LogInPageTest {
    WebDriver driver = new ChromeDriver();

    WebElement login;
    WebElement password;
    WebElement loginButton;

    @Before
    public void setUp() {
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/rentalcar/");
    }

    @After
    public void driverClose() {
        driver.close();
    }

    @org.junit.Test
    public void passedLogInTest() {
        login = driver.findElement(By.xpath("//input[@name='login']"));
        password = driver.findElement(By.xpath("//input[@name='password']"));
        loginButton = driver.findElement(By.xpath("//input[@value='Отправить']"));
        login.sendKeys("client");
        password.sendKeys("clientpass");
        loginButton.click();
    }
}
