import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class SimpleGoogleTest {

    private WebDriver drv;
    private WebDriverWait wait;
    private String baseURL = "http://localhost/litecart";
    private String username = "admin";
    private String password = "admin";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        drv = new ChromeDriver();
        drv.manage().window().maximize();
        wait = new WebDriverWait(drv, 4);
        performLogin();
        // drv.get(http://localhost/litecart/admin/);

        // wait.until(ExpectedConditions.presenceOfElementLocated(sidebar));


    }
    @After
    public void shutDown(){
        drv.quit();
    }
    private void performLogin(){
        By sidebar = By.id("sidebar");

        drv.navigate().to(baseURL + "/admin");

        if (isElementPresent(sidebar)) return;
        drv.findElement(By.name("username")).sendKeys(username);
        drv.findElement(By.name("password")).sendKeys(password);
        drv.findElement(By.name("login")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(sidebar));

    }

    private boolean isElementPresent(By element) {return drv.findElements(element).size() > 0; }

    @Test
    public void adminPanelMenuTest (){

        By menuBlock = By.cssSelector("#sidebar ul#box-apps-menu");

      /*  List<WebElement> menuItems=drv.findElement(menuBlock).findElements(By.xpath("./li"));

        for (WebElement menuItem: menuItems) {
            System.out.println("item:" + menuItem.getText());
            menuItem.click();
        }*/
      By selectedItem = By.cssSelector("li.selected");

      int menuSize = drv.findElement(menuBlock).findElements(By.xpath("./li")).size();

      for (int menuItem = 1; menuItem <= menuSize; menuItem ++) {
          drv.findElement(menuBlock).findElement(By.xpath("./li["+menuItem+"]")).click();
          Assert.assertTrue("Page Title (h1 element) not found", isElementPresent(By.cssSelector("h1")));

          int subMenuSize = drv.findElement(menuBlock).findElement(selectedItem).findElements(By.cssSelector("li")).size();
          if (subMenuSize > 0) {

              for (int subMenuItem = 1; subMenuItem <= subMenuSize; subMenuItem ++) {
                  drv.findElement(selectedItem).findElement(By.cssSelector("li:nth-of-type(" + subMenuItem + ")")).click();
                  Assert.assertTrue("Page Title (h1 element) not found", isElementPresent(By.cssSelector("h1")));
              }

          }

      }

    }

}
