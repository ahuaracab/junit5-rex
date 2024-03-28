package junit5tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DynamicWaitTests {
    WebDriver driver;

    @BeforeEach
    public void setUpTest(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/selenium-playground/bootstrap-download-progress-demo");
    }

    @AfterEach
    public void tearDownTest(){
        driver.quit();
    }

    @Test
    public void explicitWaitTest(){
        driver.findElement(By.id("dwnBtn")).click();
//        By complete100Percent = By.xpath("//div[@id='__next']//p[text()='100%']");
        By complete100Percent = By.cssSelector(".counter");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean completeDownload = false;
        try {
            completeDownload = wait.until(ExpectedConditions.textToBe(complete100Percent, "100%"));
        } catch (TimeoutException e) {
            System.out.println("The condition was not met within the specified time");
        }

//        String expectedMessage = "100%";
//        String actualMessage = driver.findElement(complete100Percent).getText();
//        Assertions.assertEquals(expectedMessage,actualMessage,"Expected & Actual Messages Do Not Match");

        Assertions.assertTrue(completeDownload, "The download did not complete");
    }

    @Test
    public void fluentWaitTest(){
        driver.findElement(By.id("dwnBtn")).click();
        By complete100Percent = By.cssSelector(".counter");

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(TimeoutException.class);

        boolean completeDownload = (boolean) wait.until(ExpectedConditions.textToBe(complete100Percent, "100%"));

        Assertions.assertTrue(completeDownload, "The download did not complete");
    }
}
