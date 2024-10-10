package net.lkrnac.edupagephotodownloader;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

public class EdupagePhotoDownloaderApplication {
    public static void main(String[] args) {
        var photosLocation = System.getProperty("photos.location");
        var tmpDownloadDir = photosLocation + File.separator + "tmp";
        new File(tmpDownloadDir).mkdir();
        var driver = getWebDriver(tmpDownloadDir);

        driver.get("https://" + System.getProperty("edupage.url"));
        new LoginPage(driver).login();

        new PhotosPage(driver, photosLocation, tmpDownloadDir).navigateTo()
                        .downloadAllNew();

        driver.close();
    }

    private static WebDriver getWebDriver(String tmpDownloadDir) {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.download.dir", tmpDownloadDir);
        firefoxProfile.setPreference("browser.download.folderList", 2);
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/jpg");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(firefoxProfile);
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().setSize(new Dimension(1280, 1024));
        return driver;
    }
}
