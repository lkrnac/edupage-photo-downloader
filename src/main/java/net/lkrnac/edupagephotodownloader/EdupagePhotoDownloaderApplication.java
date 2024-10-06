package net.lkrnac.edupagephotodownloader;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EdupagePhotoDownloaderApplication {
    public static void main(String[] args) throws InterruptedException {
        var driver = getWebDriver();
        var edupageUrl = "https://" + System.getProperty("edupage.url");
        var edupageUsername = System.getProperty("edupage.username");
        var edupagePassword = System.getProperty("edupage.password");


        driver.get(edupageUrl);
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        var usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameTxt")));
        usernameField.sendKeys(edupageUsername);
        var passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("uniform-input password")));
        passwordField.sendKeys(edupagePassword);

        Thread.sleep(2000000);
        driver.close();
    }

    private static WebDriver getWebDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().setSize(new Dimension(1280, 1024));
        return driver;
    }
}
