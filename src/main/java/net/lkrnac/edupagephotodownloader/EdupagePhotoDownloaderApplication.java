package net.lkrnac.edupagephotodownloader;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class EdupagePhotoDownloaderApplication {
    public static void main(String[] args) {
        var driver = getWebDriver();

        driver.get("https://" + System.getProperty("edupage.url"));
        new LoginPage(driver).login();

        new PhotosPage(driver).navigateTo()
                        .downloadAllNew();

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
