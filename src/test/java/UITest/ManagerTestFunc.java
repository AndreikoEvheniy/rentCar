package UITest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Test manager page function
 *
 * @author E.Andreiko
 */
public class ManagerTestFunc {
    WebDriver webDriver = new ChromeDriver();

    WebElement login;
    WebElement password;
    WebElement loginButton;
    WebElement acceptButton;
    WebElement sendButton;

    @Before
    public void setUp() {
        webDriver.manage().window().maximize();
        webDriver.get("http://localhost:8080/rentalcar/");
        login = webDriver.findElement(By.xpath("//input[@name='login']"));
        password = webDriver.findElement(By.xpath("//input[@name='password']"));
        loginButton = webDriver.findElement(By.xpath("//input[@value='Отправить']"));
        login.sendKeys("manager");
        password.sendKeys("managerpass");
        loginButton.click();
    }

    @After
    public void driverClose() {
        webDriver.close();
    }

    @Test
    public void acceptOrder(){
        acceptButton = webDriver.findElement(By.xpath("//select [@name='decide']"));
        sendButton = webDriver.findElement(By.xpath("/html/body/div/form/table/tbody/tr/td[10]/input"));
        acceptButton.sendKeys("[@onchange='accepted']");
        sendButton.click();
    }
}
