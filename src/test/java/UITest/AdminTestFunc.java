package UITest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Test admin page function
 *
 * @author E.Andreiko
 */
public class AdminTestFunc {

    WebDriver webDriver = new ChromeDriver();

    WebElement idCar;

    WebElement login;

    WebElement password;

    WebElement loginButton;

    WebElement brand;

    WebElement category;

    WebElement model;

    WebElement cost;

    WebElement button;

    @Before
    public void setUp() {
        webDriver.manage().window().maximize();
        webDriver.get("http://localhost:8080/rentalcar/");
        login = webDriver.findElement(By.xpath("//input[@name='login']"));
        password = webDriver.findElement(By.xpath("//input[@name='password']"));
        loginButton = webDriver.findElement(By.xpath("//input[@value='Отправить']"));
        login.sendKeys("admin");
        password.sendKeys("adminpass");
        loginButton.click();
    }

    @After
    public void driverClose() {
        webDriver.close();
    }

    @Test
    public void addCarTest() {
        brand = webDriver.findElement(By.xpath("//select[@name='brand']"));
        category = webDriver.findElement(By.xpath("//select[@name='category']"));
        model = webDriver.findElement(By.xpath("//input[@name='model']"));
        cost = webDriver.findElement(By.xpath("//input[@name='cost']"));
        button = webDriver.findElement(By.xpath("//input[@name='addCar']"));
        brand.sendKeys("[@value='2']");
        category.sendKeys("[@value='1']");
        model.sendKeys("Rs6");
        cost.sendKeys("250");
        button.click();
    }

    @Test
    public void UpdateCarTest() {
        idCar = webDriver.findElement(By.xpath("//input[@name='id_car']"));
        brand = webDriver.findElement(By.xpath("//select[@name='brand']"));
        category = webDriver.findElement(By.xpath("//select[@name='category']"));
        model = webDriver.findElement(By.xpath("//input[@name='model']"));
        cost = webDriver.findElement(By.xpath("//input[@name='cost']"));
        button = webDriver.findElement(By.xpath("//input[@name='updateCar']"));
        idCar.sendKeys("11");
        brand.sendKeys("[@value='2']");
        category.sendKeys("[@value='2']");
        model.sendKeys("s6");
        cost.sendKeys("350");
        button.click();
    }

    @Test
    public void deleteCarTest() {
        idCar = webDriver.findElement(By.xpath("//input[@name='id_car']"));
        button = webDriver.findElement(By.xpath("//input[@name='deleteCar']"));
        idCar.sendKeys("11");
        button.click();
    }
}
