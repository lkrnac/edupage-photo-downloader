package net.lkrnac.edupagephotodownloader;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@AllArgsConstructor
public class PhotosPage {
    private final WebDriver driver;

    PhotosPage navigateTo() {
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        var timelineElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[data-module='timeline']")));
        timelineElement.click();
        var photosElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-id='noticeboard_photos']")));
        photosElement.click();

        return this;
    }
}
