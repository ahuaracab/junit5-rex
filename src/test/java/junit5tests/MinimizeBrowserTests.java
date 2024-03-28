package junit5tests;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MinimizeBrowserTests {

    WebDriver driver;

    private void hardWait(){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            System.out.println("Sleep Statement");
        }
    }

    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/bootstrap-alert-messages-demo");

    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void minimizeUsingBuiltInFeature(){
        hardWait();
        driver.manage().window().minimize();
        hardWait();
        driver.findElement(By.xpath("//button[text()='Normal Success Message']")).click();
        String actualMessage = driver.findElement(By.xpath("//div[text()='Normal success message. To close use the close button.']")).getText();

        Assertions.assertTrue(actualMessage.contains("Normal success"));
        System.out.println(driver.getTitle());
    }

    @Test
    public void minimizeUsingSetPosition(){
        hardWait();
        driver.manage().window().setPosition(new Point(1000,300));
        hardWait();
        driver.findElement(By.xpath("//button[text()='Normal Success Message']")).click();
        String actualMessage = driver.findElement(By.xpath("//div[text()='Normal success message. To close use the close button.']")).getText();

        Assertions.assertTrue(actualMessage.contains("Normal success"));
        System.out.println(driver.getTitle());
    }

    @Test
    public void minimizeUsingRobotClass() throws AWTException {
        hardWait();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_WINDOWS);
        robot.keyPress(KeyEvent.VK_D);
        hardWait();
        driver.findElement(By.xpath("//button[text()='Normal Success Message']")).click();
        String actualMessage = driver.findElement(By.xpath("//div[text()='Normal success message. To close use the close button.']")).getText();

        Assertions.assertTrue(actualMessage.contains("Normal success"));
        System.out.println(driver.getTitle());
    }
}
