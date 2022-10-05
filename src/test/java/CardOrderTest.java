import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;

public class CardOrderTest {
   private WebDriver driver;

   @BeforeAll
   static void setUpAll(){
       System.setProperty(
               "webdriver.chrome.driver", "C:\\Users\\karls\\IdeaProjects\\SeleniumHomework\\Driver\\chromedriver.exe");
   }
    @BeforeEach
    void setUp(){driver = new ChromeDriver();}

    @AfterEach
    void tearDown(){
       driver.quit();
       driver = null;
    }
    @Test void shouldFillingPersonalData() throws InterruptedException {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Старк Тони");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79997775555");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);
        Thread.sleep(4000);
    }
    @Test void shouldFillingInvalidName() throws InterruptedException {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Tony Stark");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79997775555");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText();

        Assertions.assertEquals(expected, actual);
        Thread.sleep(4000);
    }
}
