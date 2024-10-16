package net.lkrnac.edupagephotodownloader;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PhotosPreviewPage {
    public static final String PHOTOS_PHOTO_THUMB = ".photos-photoThumb";
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    public PhotosPreviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    @SneakyThrows
    void downloadAllPhotos() {
        WebElement downloadButton = wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(".photos-button-viewImage"))));

        wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(PHOTOS_PHOTO_THUMB))));
        List<WebElement> thumbElements = driver.findElements(By.cssSelector(PHOTOS_PHOTO_THUMB));
        thumbElements.get(0).click();
        wait.until((ExpectedCondition<Boolean>) webDriver -> thumbElements.get(0).getAttribute("class").contains("selected"));

        for (int idx = 0; idx < thumbElements.size(); idx++) {
            var thumbElement = thumbElements.get(idx);
            //noinspection DataFlowIssue
            wait.until((ExpectedCondition<Boolean>) webDriver -> thumbElement.getAttribute("class").contains("selected"));
            downloadButton.click();
            //noinspection BusyWait
            Thread.sleep(100);
            if (idx < thumbElements.size() - 1) {
                actions.sendKeys(Keys.ARROW_RIGHT).perform();
            }
        }

        WebElement backdButton = wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(".photos-albumTitle i"))));
        backdButton.click();
    }
}
