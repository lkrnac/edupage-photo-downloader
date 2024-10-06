package net.lkrnac.edupagephotodownloader;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@AllArgsConstructor
public class LoginPage {
    private final WebDriver driver;

    public void login() {
        var edupageUsername = System.getProperty("edupage.username");
        var edupagePassword = System.getProperty("edupage.password");

        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        var usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameTxt")));
        usernameField.sendKeys(edupageUsername);
        var passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("password")));
        passwordField.sendKeys(edupagePassword);
        var loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("skinButton")));
        loginButton.click();
    }
}
