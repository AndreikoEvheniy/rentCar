package UITest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Test client page function
 *
 * @author E.Andreiko
 */
public class ClientTestFunc {

    WebDriver webDriver = new ChromeDriver();

    WebElement login;

    WebElement password;

    WebElement loginButton;

    WebElement idCar;

    WebElement driver;

    WebElement from;

    WebElement to;

    WebElement buttonMakeOrder;

    @Before
    public void setUp() {
        webDriver.manage().window().maximize();
        webDriver.get("http://localhost:8080/rentalcar/");
        login = webDriver.findElement(By.xpath("//input[@name='login']"));
        password = webDriver.findElement(By.xpath("//input[@name='password']"));
        loginButton = webDriver.findElement(By.xpath("//input[@value='Отправить']"));
        login.sendKeys("client");
        password.sendKeys("clientpass");
        loginButton.click();
    }

    @After
    public void driverClose() {
        webDriver.close();
    }

    @Test
    public void rentCarTest(){
        idCar = webDriver.findElement(By.xpath("//input[@name='chooseCar']"));
        driver = webDriver.findElement(By.xpath("//input[@name='driver']"));
        from = webDriver.findElement(By.xpath("//input[@id='fromDate']"));
        to = webDriver.findElement(By.xpath("//input[@id='toDate']"));
        buttonMakeOrder = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[2]/input[3]"));
        idCar.sendKeys("[@id='car_1']");
        driver.sendKeys("[@id='driver_id_false']");
        from.sendKeys("29.10.2020");
        to.sendKeys("30.10.2020");
        buttonMakeOrder.click();
    }
}
