package net.lkrnac.edupagephotodownloader;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PhotosPreviewPage {
    public static final String PHOTOS_PHOTO_THUMB = ".photos-photoThumb";
    private final WebDriver driver;
    private final WebDriverWait wait;

    public PhotosPreviewPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @SneakyThrows
    void downloadAllPhotos() {
        WebElement downloadButton = wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(".photos-button-viewImage"))));

        wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(PHOTOS_PHOTO_THUMB))));
        List<WebElement> thumbElements = driver.findElements(By.cssSelector(PHOTOS_PHOTO_THUMB));
        for (int idx = 0; idx < thumbElements.size(); idx++) {
            thumbElements = driver.findElements(By.cssSelector(PHOTOS_PHOTO_THUMB));
            var thumbElement = thumbElements.get(idx);

            thumbElement.click();
//            wait.until((ExpectedCondition<Boolean>) driver -> thumbElement.getAttribute("class").contains("selected"));
            Thread.sleep(100);
            downloadButton.click();
            Thread.sleep(100);
        }

        WebElement backdButton = wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(".photos-albumTitle i"))));
        backdButton.click();
    }
}
