package net.lkrnac.edupagephotodownloader;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

@SuppressWarnings({"java:S106"})
public class PhotosPage {
    private static final Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}");
    private static final String EMOJI_PATTERN = "[\\p{InEmoticons}\\p{InMiscellaneousSymbolsAndPictographs}\\p{InTransportAndMapSymbols}" +
            "\\p{InSupplementalSymbolsAndPictographs}\\p{InMiscellaneousSymbols}" +
            "\\p{InDingbats}\\p{InGeometricShapes}]" +
            "|\\p{So}|\\uFE0F|\\u200D|\\uD83C[\\uDFFB-\\uDFFF]";
    public static final String DATA_DATE = "data-date";

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;
    private final String photosLocation;
    private final String tmpDownloadDir;

    public PhotosPage(WebDriver driver, String photosLocation, String tmpDownloadDir) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        this.photosLocation = photosLocation;
        this.tmpDownloadDir = tmpDownloadDir;
    }

    PhotosPage navigateTo() {
        var timelineElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[data-module='timeline']")));
        timelineElement.click();
        var photosElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-id='noticeboard_photos']")));
        photosElement.click();

        return this;
    }

    void downloadAllNew() {
        File directory = new File(photosLocation);
        String lastSavedDate = findLastSavedDate(directory);
        WebElement ulElement = wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("ul.photos"))));
        scrollToLastSaved(ulElement, lastSavedDate);

        List<WebElement> liElements = ulElement.findElements(By.cssSelector(".noticeboardItem"));
        //noinspection DataFlowIssue
        liElements.stream()
                .sorted(Comparator.comparing(li -> li.getAttribute(DATA_DATE)))
                .filter(li -> li.getAttribute(DATA_DATE).compareTo(lastSavedDate) > 0)
                .forEach(this::downloadPhotosGroup);
    }

    @SneakyThrows
    private void downloadPhotosGroup(WebElement li) {
        js.executeScript("arguments[0].scrollIntoView(true);", li);
        li.click();
        new PhotosPreviewPage(driver)
                .downloadAllPhotos();

        String newDirectoryName = li.getAttribute(DATA_DATE) + " " + li.findElement(By.cssSelector(".textContent")).getText();
        String nameWithoutEmojis = newDirectoryName.replaceAll(EMOJI_PATTERN, "").trim();
        String targetDirectoryPath = photosLocation + File.separator + nameWithoutEmojis;
        Files.move(Paths.get(tmpDownloadDir), Paths.get(targetDirectoryPath), StandardCopyOption.REPLACE_EXISTING);
    }

    @SneakyThrows
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
                    .filter(li -> lastSavedDate.equals(li.getAttribute(DATA_DATE)))
                    .findFirst()
                    .orElse(null);
            lasSavedIsOnPage = foundElement != null;
            if (!lasSavedIsOnPage) {
                var loadOrderButton = wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(".loadOlderBtn"))));
                loadOrderButton.click();
                Thread.sleep(500);
            }
        }
    }

    private static String findLastSavedDate(File directory) {
        File[] directories = directory.listFiles(File::isDirectory);

        if (directories == null || directories.length == 0) {
            return "1980-01-01";
        }

        return Arrays.stream(directories)
                .filter(subdirectory -> datePattern.matcher(subdirectory.getName()).find())
                .max(Comparator.comparing(File::getName))
                .map(subdirectory -> subdirectory.getName().substring(0, 10))
                .orElse("1980-01-01");
    }
}
