import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class CardOrderTest {
   private WebDriver driver;

   @BeforeAll
   static void setUpAll(){
       WebDriverManager.chromedriver().setup();

   }
    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown(){
       driver.quit();

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
