package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static java.awt.SystemColor.text;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CallbackTest {
    private WebDriver driver; //создание драйвера


    @BeforeAll  //метод запускается перед тестами
    static void setUpAll(){
        //System.setProperty("webdriver.chrome.driver", "driver/win/chromedriver.exe"); // путь к драйверу
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach  //запускатся перед запуском каждого теста
    void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach //закрытие браузера после каждого автотеста
    void tearsDown(){
        driver.quit();
        driver=null;
    }

    @Test
    void test(){
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Екатерина");
        elements.get(1).sendKeys("+79999999999");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();
        String text = driver.findElement(By.className("paragraph")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void test1(){
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Энжэ");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79887776633");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.className("paragraph")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }


}
