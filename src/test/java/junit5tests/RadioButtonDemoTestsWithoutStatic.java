package junit5tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RadioButtonDemoTestsWithoutStatic {

    WebDriver driver;

    @BeforeAll
    public void beforeAll (){
        System.out.println("Execute One Time Before All Test Methods\n");
    }

    @BeforeEach
    public void beforeEach (){
        System.out.println("Execute Before Each Test Method \n");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/selenium-playground/radiobutton-demo");
    }

    @Test
    public void testSelectedRadioButtonMessage(){
        System.out.println("1st Test Method \n");
        driver.findElement(By.cssSelector("input[value='Female']")).click();
        driver.findElement(By.id("buttoncheck")).click();
        String actualLabel = driver.findElement(By.cssSelector("#buttoncheck + p")).getText();
        String expectedLabel = "Radio button 'Female' is checked";
        Assertions.assertEquals(expectedLabel, actualLabel,"Actual & Expected Label Do Not Match");
    }

    @Test
    public void testDisabledRadioButton(){
        System.out.println("2nd Test Method \n");
        WebElement disabledButton = driver.findElement(By.cssSelector("input[value='RadioButton3']"));
        boolean isDisabled = !disabledButton.isEnabled();
        Assertions.assertTrue(isDisabled, "Button Is Not Disabled");
    }

    @AfterEach
    public void afterEach (){
        System.out.println("Execute After Each Test Method \n");
        driver.quit();
    }

    @AfterAll
    public void afterAll (){
        System.out.println("Execute One Time After All Test Methods");
    }
}
