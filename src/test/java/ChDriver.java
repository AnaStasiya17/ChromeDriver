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

    ChromeDriver driver;
    // почему ссылка на DesiredCapabilities называется chrome?
    DesiredCapabilities chrome;

    @Before
    public void createDriver() {

        driver = new ChromeDriver();
        // Для чего здесь вообще нужны эти desiredCapabilities?
        chrome = new DesiredCapabilities().chrome();


    }

    @Test
    public void chromeWebTest() throws Exception {

        // Почему это нельзя было сделать в Before?
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        driver.get("https://www.yandex.ru/");
        driver.findElement(By.id("text")).sendKeys("Selenium Webdriver");

        driver.findElement(By.cssSelector(".suggest2-form__button")).click();
        Thread.sleep(2000);
        driver.get(driver.getCurrentUrl());
        driver.findElement(By.className("needsclick")).click();


        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshot.png"));



    }

    @After
    public void quitDriver() throws InterruptedException {

        driver.quit();
    }
}
