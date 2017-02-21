import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by Анастасия Цыбулько on 15.02.2017.
 */
public class ChDriver {
    ChromeDriver driver = new ChromeDriver();

    @Before
    public void createDriver() {


        DesiredCapabilities chrome = new DesiredCapabilities().chrome();
    }

    @Test
    public void chromeWebTest() throws Exception {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        //DesiredCapabilities chrome = new DesiredCapabilities().chrome();
        //ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.yandex.ru/");
        driver.findElement(By.id("text")).sendKeys("Selenium Webdriver");

        driver.findElement(By.cssSelector(".suggest2-form__button")).click();
        Thread.sleep(2000);
        driver.get(driver.getCurrentUrl());
        driver.findElement(By.className("needsclick")).click();


        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("screenshot.png"));


    }
    @After
    public void quitDriver() {

        driver.quit();
    }
}