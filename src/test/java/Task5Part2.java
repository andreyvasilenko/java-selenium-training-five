/*
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task5Part2 {
    private WebDriver drv;
    private WebDriverWait wait;
    private String baseURL = "http://localhost/litecart/en/";

    @Before
    public void setUp () {
        WebDriverManager.chromedriver().setup();
        drv = new ChromeDriver();
        drv.manage().window().maximize();
        wait = new WebDriverWait(drv, 4);
        drv.navigate().to(baseURL);
    }
    @After
    public void shutDown () {
        drv.quit();
    }
    @Test
    public void addRemoveFromCard () {
        By mostPopularBlock = By.cssSelector("#box-most-popular");
        int menuSize = drv.findElement(mostPopularBlock).findElements(By.xpath("./li")).size();
        drv.findElement(mostPopularBlock).findElement(By.xpath("./li["+1"]")).click();
        drv.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]/div[5]/form/table/tbody/tr/td/button")).click();


    }
}
*/
