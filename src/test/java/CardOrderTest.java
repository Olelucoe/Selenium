import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class CardOrderTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        driver.quit();

    }

    @Test
    void shouldFillingPersonalData() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Старк Тони");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79997775555");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldFillingInvalidName() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Tony Stark");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79997775555");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldFormIsEmpty() throws InterruptedException {
        driver.findElement(By.className("button")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldFillingInvalidPhone() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Александр Александров");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("8888888888");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldFieldPhoneIsEmpty() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Александр Александров");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldFieldNameIsEmpty() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79997775555");
        driver.findElement(By.className("button")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCheckBoxIsEmpty() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Олег Тишкин");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79997777777");
        driver.findElement(By.className("button")).click();
        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
        String actual = driver.findElement(By.cssSelector("[data-test-id='agreement'].input_invalid .checkbox__text")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }
}
