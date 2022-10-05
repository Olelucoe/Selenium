import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    @Test void test1(){
        driver.get("http://localhost:9999");
    }

}
