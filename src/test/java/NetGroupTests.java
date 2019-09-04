import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import java.util.LinkedList;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@RunWith(Parallelized.class)
public class NetGroupTests {
    private String platform;
    private String browserName;
    private String browserVersion;


    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<String[]>();
        //Here you specify the browsers to run. Comment out the browsers that you don't have installed or don't wish to run
        env.add(new String[]{System.getProperty("os.name"), "chrome", "latest"});
        env.add(new String[]{System.getProperty("os.name"), "firefox", "latest"});
        env.add(new String[]{System.getProperty("os.name"), "ie", "latest"});


        return env;
    }

    public NetGroupTests(String platform, String browserName, String browserVersion) {
        this.platform = platform;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }

    @BeforeClass
    public static void BeforeClass(){
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();

    }

    @Before
    public void Before(){
        if (browserName == "chrome"){
            Configuration.browser = "chrome";
        } else if (browserName == "firefox"){
            Configuration.browser = "firefox";
        } else {
            Configuration.browser = "edge";
            Selenide.sleep(5000);
        }
    }

    public void CheckContactBox(){
        Assert.assertTrue(Selenide.$(By.className(Locators.contactusbox)).waitUntil(Condition.visible, 5000).shouldHave(Condition.attribute("placeholder","contact us")).exists());
        Assert.assertTrue(Selenide.$(By.name(Locators.yourname)).waitUntil(Condition.visible, 5000).shouldHave(Condition.attribute("placeholder","Your name")).exists());
        Assert.assertTrue(Selenide.$(By.name(Locators.email)).waitUntil(Condition.visible, 5000).shouldHave(Condition.attribute("placeholder","E-mail")).exists());
        Assert.assertTrue(Selenide.$(By.xpath(Locators.submit)).waitUntil(Condition.visible, 5000).exists());
    }

    @org.junit.Test
    public void Test1_NavigationBar(){
        NetGroupPage page =  new NetGroupPage();
        page.open();
        page.Navbar("services");
        Assert.assertTrue(Selenide.$(By.id(Locators.Ecommerce)).waitUntil(Condition.visible, 5000).exists());
        Assert.assertTrue(Selenide.$(By.id(Locators.DigitalG)).waitUntil(Condition.visible, 5000).exists());
        Assert.assertTrue(Selenide.$(By.id(Locators.Fintech)).waitUntil(Condition.visible, 5000).exists());
        Assert.assertTrue(Selenide.$(By.id(Locators.Utilities)).waitUntil(Condition.visible, 5000).exists());
        Assert.assertTrue(Selenide.$(By.id(Locators.DigitalB)).waitUntil(Condition.visible, 5000).exists());
        page.Navbar("cs");
        Assert.assertTrue(Selenide.$(By.id(Locators.Ecommerce2)).waitUntil(Condition.visible, 5000).exists());
        Assert.assertTrue(Selenide.$(By.id(Locators.DigitalG2)).waitUntil(Condition.visible, 5000).exists());
        Assert.assertTrue(Selenide.$(By.id(Locators.Fintech2)).waitUntil(Condition.visible, 5000).exists());
        Assert.assertTrue(Selenide.$(By.id(Locators.Utilities2)).waitUntil(Condition.visible, 5000).exists());
        Assert.assertTrue(Selenide.$(By.id(Locators.DigitalB2)).waitUntil(Condition.visible, 5000).exists());
        page.Navbar("aboutus");
        Assert.assertTrue(Selenide.$(By.id(Locators.careers)).waitUntil(Condition.visible, 5000).exists());
        page.Navbar("blog");
        page.Navbar("contacts");
        Assert.assertTrue(Selenide.$(By.id(Locators.Tallinn)).waitUntil(Condition.visible, 5000).exists());
        Assert.assertTrue(Selenide.$(By.id(Locators.Net_Group_nordic)).waitUntil(Condition.visible, 5000).exists());
    }

    @org.junit.Test
    public void Test2_ServicesContact(){
        NetGroupPage page = new NetGroupPage();
        page.open();
        page.Navbar("services");
        CheckContactBox();
        page.SNavbar("ecommerce");
        CheckContactBox();
        page.SNavbar("digital governance");
        CheckContactBox();
        page.SNavbar("fintech");
        CheckContactBox();
        page.SNavbar("utilities");
        CheckContactBox();
        page.SNavbar("digital business");
        CheckContactBox();
    }

    @org.junit.Test
    public void Test3_ContactDetails(){
        NetGroupPage page = new NetGroupPage();
        page.open();
        page.Navbar("contacts");
        Assert.assertTrue(Selenide.$(By.partialLinkText("Lelle 22")).waitUntil(Condition.visible, 5000).exists());
        Assert.assertTrue(Selenide.$(By.partialLinkText("11318")).waitUntil(Condition.visible, 5000).exists());
        Assert.assertTrue(Selenide.$(By.partialLinkText("Estonia")).waitUntil(Condition.visible, 5000).exists());
        Assert.assertTrue(Selenide.$(By.partialLinkText("372 664 4355")).waitUntil(Condition.visible, 5000).exists());
        Assert.assertTrue(Selenide.$(By.partialLinkText("info@netgroup.ee")).waitUntil(Condition.visible, 5000).exists());
        Selenide.$(By.id(Locators.Net_Group_nordic)).click();
        Assert.assertTrue(Selenide.$(By.className(Locators.vcard)).waitUntil(Condition.visible, 5000).getText().contains("HELI SIPONEN"));
        Assert.assertTrue(Selenide.$(By.className(Locators.vcard)).waitUntil(Condition.visible, 5000).getText().contains("40 53 69 800"));
        Assert.assertTrue(Selenide.$(By.className(Locators.vcard)).waitUntil(Condition.visible, 5000).getText().contains("heli.siponen@netgroup.com"));
    }

    @After
    public void After(){
        getWebDriver().quit();
    }
}
