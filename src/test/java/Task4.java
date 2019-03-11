import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task4 {

    private WebDriver drv;
    private WebDriverWait wait;
    private String baseURL = "http://localhost/litecart/en/";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        drv = new ChromeDriver();
        drv.manage().window().maximize();
        wait = new WebDriverWait(drv, 4);
        drv.navigate().to(baseURL);

    }
    @After
    public void shutDown(){
        drv.quit();
    }

    @Test
    public void testMainPage (){
        WebElement elementMainPage = drv.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]"));
        //By campaignBlock = By.id("box-campaigns");
        //WebElement elementMainPage = drv.findElement(campaignBlock).findElement(By.xpath(("./li[1]")));
        String titleMainPage = elementMainPage.getAttribute("title");
        String priceMainPage = drv.findElement(By.className("regular-price")).getAttribute("textContent");
        String priceMainPageCampaign = drv.findElement(By.className("campaign-price")).getAttribute("textContent");
        String valueMainPagePriceColor = drv.findElement(By.className("regular-price")).getCssValue("color");
        String valueMainPageCampaignPriceColor = drv.findElement(By.className("campaign-price")).getCssValue("color");
        String valueMainPagePriceFont = drv.findElement(By.className("campaign-price")).getCssValue("font-weight");
        String valueMainPagePriceStrike = drv.findElement(By.className("regular-price")).getCssValue("text-decoration-line");
        System.out.println(titleMainPage);

/*        System.out.println(titleMainPage);
        System.out.println(priceMainPage);
        System.out.println(priceMainPageCampaign);
        System.out.println(valueMainPagePriceColor);
        System.out.println(valueMainPageCampaignPriceColor);
        System.out.println(valueMainPagePriceFont);
        System.out.println(valueMainPagePriceStrike);*/

        drv.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li")).click();

        WebElement elementItemPage = drv.findElement(By.tagName("h1"));

        String titleItemPage = elementItemPage.getAttribute("textContent");
        String priceItemPage = drv.findElement(By.className("regular-price")).getAttribute("textContent");
        String priceItemPageCampaign = drv.findElement(By.className("campaign-price")).getAttribute("textContent");
        String valueItemPagePriceColor = drv.findElement(By.className("regular-price")).getCssValue("color");
        String valueItemPageCampaignPriceColor = drv.findElement(By.className("campaign-price")).getCssValue("color");
        String valueItemPagePriceFont = drv.findElement(By.className("campaign-price")).getCssValue("font-weight");
        String valueItemPagePriceStrike = drv.findElement(By.className("regular-price")).getCssValue("text-decoration-line");

/*        System.out.println(titleItemPage);
        System.out.println(priceItemPage);
        System.out.println(priceItemPageCampaign);
        System.out.println(valueItemPagePriceColor);
        System.out.println(valueItemPageCampaignPriceColor);
        System.out.println(valueItemPagePriceFont);
        System.out.println(valueItemPagePriceStrike);*/

        Assert.assertEquals(titleItemPage, titleMainPage);
        Assert.assertEquals(priceMainPage, priceItemPage);
        Assert.assertEquals(priceMainPageCampaign, priceItemPageCampaign);
        Assert.assertEquals(valueMainPagePriceColor, "rgba(119, 119, 119, 1)");
        Assert.assertEquals(valueItemPagePriceColor, "rgba(204, 0, 0, 1)");
        Assert.assertEquals(valueMainPageCampaignPriceColor, "rgba(119, 119, 119, 1)");
        Assert.assertEquals(valueItemPageCampaignPriceColor, "rgba(204, 0, 0, 1)");
        Assert.assertEquals(valueMainPagePriceFont, valueItemPagePriceFont);
        Assert.assertEquals(valueMainPagePriceStrike, valueItemPagePriceStrike);
}
}
