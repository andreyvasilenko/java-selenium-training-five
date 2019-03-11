import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task1 {
    private WebDriver drv;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        drv = new ChromeDriver();
        drv.manage().window().maximize();
    }
    @Test
    public void task1() {
        drv.navigate().to("https://www.seleniumhq.org/");
    }
    @After
        public void shutDown() {
        drv.quit();
    }

}