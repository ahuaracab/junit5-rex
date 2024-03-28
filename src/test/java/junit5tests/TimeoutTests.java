package junit5tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

//@Timeout(value = 10000, unit = TimeUnit.MILLISECONDS)
public class TimeoutTests {

    WebDriver driver;

    @BeforeEach
    public void setUpTest(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/selenium-playground/jquery-download-progress-bar-demo");
    }

    @AfterEach
    public void tearDownTest(){
        driver.quit();
    }

//    @Timeout(1)
    @Test
    public void testDownloadProgress_1(){
        System.out.println(Thread.currentThread().threadId());
        System.out.println(Thread.currentThread().getName());
        driver.findElement(By.id("downloadButton")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='dialog']/div[text()='Complete!']")));
        String actualMessage = element.getText();
        String expectedMessage = "Complete!";
        Assertions.assertEquals(expectedMessage, actualMessage, "Messages Do Not Match");
        System.out.println("Test passed!");
    }


//    @Timeout(value = 2000, unit = TimeUnit.MILLISECONDS) //tiempo total de prueba, no para encontrar un elemento
    @Test
    public void testDownloadProgress_2(){
        System.out.println(Thread.currentThread().threadId());
        System.out.println(Thread.currentThread().getName());
        driver.findElement(By.id("downloadButton")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='dialog']/div[text()='Complete!']")));
        String actualMessage = element.getText();
        String expectedMessage = "Complete!";
        Assertions.assertEquals(expectedMessage, actualMessage, "Messages Do Not Match");
        System.out.println("Test passed!");
    }

    @Test
    @Timeout(value = 1,
            unit = TimeUnit.MINUTES,
            threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    public void testTimeoutThreadMode(){
        System.out.println(Thread.currentThread().threadId());
        System.out.println(Thread.currentThread().getName());
        driver.findElement(By.id("downloadButton")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By complete = By.cssSelector("#dialog");
//        By complete = By.xpath("//div[@id='dialog']/div[text()='Complete!']");
//        wait.until(ExpectedConditions.textToBe(complete, "Complete!"));

//        String actualMessage = driver.findElement(complete).getText();
//        String expectedMessage = "Complete!";
//        Assertions.assertEquals(expectedMessage, actualMessage, "Messages Do Not Match");
//        System.out.println("Test passed!");

        boolean completeProgress = false;
        try {
            completeProgress = wait.until(ExpectedConditions.textToBe(complete, "Complete!"));
        } catch (TimeoutException e) {
            System.out.println("The condition was not met within the specified time");
        }

        Assertions.assertTrue(completeProgress, "The progress did not complete");
    }
}
