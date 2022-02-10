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

public class Choose_A_Tablet {

    private static final String A1 = "https://www.a1.bg/bg";
    private WebDriver driver;
    public static final String brand = "Apple";
    public static final String name = "iPad Pro 12,9'' (5th Gen)";



    @BeforeClass
    public static void Chrome (){

        ChromeDriverManager.chromedriver().setup();
    }


    @Before
    public void TestInit(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(A1);
        Assert.assertTrue("Page was navigated. Actual url: " + driver.getCurrentUrl()+ ". Expected Url" + "https://www.a1.bg/bg)", driver.getCurrentUrl().contains("https://www.a1.bg/bg"));

    }

    @Test
    public void ChooseATablet (){

        WebElement accept = driver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]"));
        accept.click();
        WebElement devices = driver.findElement(By.xpath("//a[@href='/ustroystva']//figure//img[@alt='A1 One Unlimited']"));
        devices.click();
        WebElement tablets = driver.findElement(By.xpath("//a[@id='device_type_name_4']"));
        tablets.click();
        WebElement down = driver.findElement(By.tagName("HTML"));
        Wait();
        down.sendKeys(Keys.DOWN);
        WebElement iPad = driver.findElement(By.xpath("//img[@alt=\"Apple iPad Pro 12,9'' (5th Gen)\"]"));
        iPad.click();
        WebElement text_brand = driver.findElement(By.xpath("//span[contains(text(),'Apple')]"));
        Assert.assertEquals(brand,text_brand.getText());
        WebElement text_name = driver.findElement(By.xpath("//span[contains(text(),\"iPad Pro 12,9'' (5th Gen)\")]"));
        Assert.assertEquals(name, text_name.getText());
        driver.quit();



    }

    private void Wait() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }




    }}


