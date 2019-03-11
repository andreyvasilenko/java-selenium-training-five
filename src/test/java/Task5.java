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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Task5 {
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

    }

    @After
    public void shutDown() {
        drv.quit();
    }

    private void performLogin() {
        By sidebar = By.id("sidebar");

        drv.navigate().to(baseURL + "/admin");

        if (isElementPresent(sidebar)) return;
        drv.findElement(By.name("username")).sendKeys(username);
        drv.findElement(By.name("password")).sendKeys(password);
        drv.findElement(By.name("login")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(sidebar));

    }

    private boolean isElementPresent(By element) {
        return drv.findElements(element).size() > 0;
    }

    @Test
    public void testMainPage() {
        WebElement catalogTab = drv.findElement(By.xpath(".//li[2]"));
        catalogTab.click();

        WebElement addNewProduct = drv.findElement(By.xpath("//*[@id=\"content\"]/div[1]/a[2]"));
        addNewProduct.click();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        WebElement productEnable = drv.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[1]/td/label[1]/input"));
        productEnable.click();

        WebElement nameNewProduct = drv.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[2]/td/span/input"));
        String newName = "New unique product " + date;
        //nameNewProduct.sendKeys("New unique product " + date);
        nameNewProduct.sendKeys(""+ newName);

        Random r = new Random();

        String alphabet = "0123456789";
        String code = "";
        for (int i = 0; i < 5; i++) {
            code += (alphabet.charAt(r.nextInt(alphabet.length())));
        } // prints 50 random characters from alphabet
        System.out.println(code);

        WebElement codeNewProduct = drv.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[3]/td/input"));
        codeNewProduct.sendKeys(code);

        WebElement categoriesNewProduct = drv.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[4]/td/div/table/tbody/tr[2]/td[1]/input"));
        categoriesNewProduct.click();

        WebElement quantityNewProduct = drv.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[8]/td/table/tbody/tr/td[1]/input"));
        //quantityNewProduct.sendKeys(Keys.CONTROL + "a");
        quantityNewProduct.sendKeys("3");

        WebElement soldOutStatus = drv.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[8]/td/table/tbody/tr/td[4]/select"));
        soldOutStatus.click();

        WebElement soldStatusTemporary = drv.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[8]/td/table/tbody/tr/td[4]/select/option[3]"));
        soldStatusTemporary.click();

        drv.findElement(By.cssSelector("input[type='file']")).sendKeys("C:\\Users\\AndreyVasilenko\\IdeaProjects\\session4HW\\src\\test\\resources/index.jpg");

        WebElement pricesTab = drv.findElement(By.xpath("//*[@id=\"content\"]/form/div/ul/li[4]/a"));
        pricesTab.click();

        WebElement purchasePrice = drv.findElement(By.xpath("//*[@id=\"tab-prices\"]/table[1]/tbody/tr/td/input"));
        purchasePrice.sendKeys("10");

        WebElement purchasePriceIncludingTax = drv.findElement(By.xpath("//*[@id=\"tab-prices\"]/table[3]/tbody/tr[2]/td[2]/input"));
        purchasePriceIncludingTax.sendKeys("20");

        //*[@id="content"]/form/p/span/button[1]
        WebElement saveButton = drv.findElement(By.xpath("//*[@id=\"content\"]/form/p/span/button[1]"));
        saveButton.click();


        //drv.findElement(By.xpath("//a[contains(text()," +  newName + ")]")).click();
        //drv.findElement(By.xpath("//a[contains(text(),'"+newName+"')]")).click();
        //drv.findElement(By.xpath("//a[contains(text(),'New unique product Thu Mar 07 14:55:36 EET 2019')]")).click();

        String nameFromAdminPage = drv.findElement(By.xpath("//a[contains(text(),'"+newName+"')]")).getAttribute("text");
        System.out.println(nameFromAdminPage);
        Assert.assertEquals(newName, nameFromAdminPage);
    }
}
