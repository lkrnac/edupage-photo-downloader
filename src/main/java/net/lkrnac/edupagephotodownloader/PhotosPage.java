package net.lkrnac.edupagephotodownloader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class PhotosPage {
    private static final Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}");

    private final WebDriverWait wait;

    public PhotosPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    PhotosPage navigateTo() {
        var timelineElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[data-module='timeline']")));
        timelineElement.click();
        var photosElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-id='noticeboard_photos']")));
        photosElement.click();

        return this;
    }

    PhotosPage downloadAllNew() {
        var photosLocation = System.getProperty("photos.location");

        File directory = new File(photosLocation);
        String lastSavedDate = findLastSavedDate(directory);

        WebElement ulElement = wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("ul.photos"))));

        scrollToLastSaved(ulElement, lastSavedDate);
        return this;
    }

    private void scrollToLastSaved(WebElement ulElement, String lastSavedDate) {
        boolean lasSavedIsOnPage = false;
        WebElement lastLi = null;
        while (!lasSavedIsOnPage) {
            List<WebElement> liElements = ulElement.findElements(By.tagName("li"));
            if (lastLi != null && lastLi.equals(ulElement.findElements(By.tagName("li")).getLast())) {
                break;
            }
            lastLi = ulElement.findElements(By.tagName("li")).getLast();
            WebElement foundElement = liElements.stream()
                    .filter(li -> lastSavedDate.equals(li.getAttribute("data-date")))
                    .findFirst()
                    .orElse(null);
            lasSavedIsOnPage = foundElement != null;
            if (!lasSavedIsOnPage) {
                var loadOrderButton = wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(".loadOlderBtn"))));
                loadOrderButton.click();
            }
        }
    }

    private static String findLastSavedDate(File directory) {
        File[] directories = directory.listFiles(File::isDirectory);

        return directories == null || directories.length == 0
                ? "1980-01-01"
                : (
                        Arrays.stream(directories)
                                .filter(subdirectory -> datePattern.matcher(subdirectory.getName()).find())
                                .max(Comparator.comparing(File::getName))
                                .map(subdirectory -> subdirectory.getName().substring(0, 10))
                                .orElse(null)
                );
    }
}
