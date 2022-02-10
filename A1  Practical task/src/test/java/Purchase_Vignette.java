import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class Purchase_Vignette {

    private static final String A1 = "https://www.a1.bg/bg";
    private WebDriver driver;

    @BeforeClass
    public static void Browser() {
        ChromeDriverManager.chromedriver().setup();
    }

    @Before
    public void TestInit() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(A1);
        Assert.assertTrue("Page was navigated. Actual url: " + driver.getCurrentUrl()+ ". Expected Url" + "https://www.a1.bg/bg)", driver.getCurrentUrl().contains("https://www.a1.bg/bg"));

    }

    @Test
    public void PurchaseVignette () {
        WebElement accept = driver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]"));
        accept.click();
        Wait();
        WebElement vignette = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[6]/a[1]/figure[1]/img[1]"));
        vignette.click();
        WebElement buy_here = driver.findElement(By.xpath("//*[@id=\"cntOpt1\"]/div[1]/div/div/div/div/div/div/div[3]/a"));
        buy_here.click();
        Wait();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        WebElement category = driver.findElement(By.id("ico1"));
        category.click();
        WebElement price_tag = driver.findElement(By.id("priceTag-4"));
        price_tag.click();
        WebElement plate = driver.findElement(By.id("plate"));
        plate.sendKeys("CA1234BТ");
        WebElement down = driver.findElement(By.tagName("HTML"));
        down.sendKeys(Keys.ARROW_DOWN);
        down.sendKeys(Keys.DOWN);
        Wait();
        WebElement calendar = driver.findElement(By.xpath("//input[@id='startFrom2']"));
        calendar.click();
        Wait();
        WebElement date = driver.findElement(By.xpath("//a[normalize-space()='11']"));
        date.click();
        down.sendKeys(Keys.PAGE_DOWN);
        Wait();
        WebElement next = driver.findElement(By.xpath("//button[contains(text(),'Продължи')]"));
        next.click();
        WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
        email.sendKeys("test_vignette@a1.bg");
        WebElement page_down = driver.findElement(By.tagName("HTML"));
        page_down.sendKeys(Keys.PAGE_DOWN);
        Wait();
        WebElement checkbox = driver.findElement(By.xpath("//body[1]/div[2]/div[2]/main[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[6]/label[1]/var[1]"));
        checkbox.click();
        Wait();
        WebElement next_button = driver.findElement(By.xpath("//button[contains(text(),'Продължи')]"));
        next_button.click();
        Wait();
        String plateString = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/main[1]/div[1]/div[1]/div[1]/div[4]/div[2]/div[1]/div[2]/div[2]/table[1]/tbody[1]/tr[1]/td[2]")).getText();
        assertTrue(plateString.contains("CA1234BТ"));
        Wait();
        String vehicleString = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/main[1]/div[1]/div[1]/div[1]/div[4]/div[2]/div[1]/div[2]/div[2]/table[1]/tbody[1]/tr[4]/td[2]")).getText();
        assertTrue(vehicleString.contentEquals("Лек автомобил до 3,5 тона"));
        WebElement confirm = driver.findElement(By.xpath("//button[contains(text(),'Потвърди')]"));
        confirm.click();
        Wait();
        String ActualTitle = driver.getTitle();
        String ExpectedTitle = "Borica E-Payment";
        Assert.assertEquals(ExpectedTitle, ActualTitle);
        String purchaseString = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[4]/div[3]/div[3]")).getText();
        Assert.assertTrue(purchaseString.contains("Покупка на винетка за CA1234BТ"));

        System.out.println("Page title is : " + driver.getTitle() + "; ");
        System.out.println(purchaseString);
        driver.quit();


    }



    private void Wait() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
}}