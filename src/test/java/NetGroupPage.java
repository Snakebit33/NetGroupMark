import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.Assert;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;

public class NetGroupPage {


    public NetGroupPage open(){
        Selenide.open(Locators.website);
        return this;
    }

    public NetGroupPage Navbar(String trigger){

        if(trigger == "services"){
            $(By.xpath(Locators.Services)).waitUntil(Condition.visible, 5000).click();
            Assert.assertTrue(Selenide.$(By.xpath("//h1[contains(.,'We maximise your value  by digitizing your business')]")).waitUntil(Condition.visible, 5000).exists());
        } else if (trigger == "cs" ){
            $(By.xpath(Locators.cs)).waitUntil(Condition.visible, 5000).click();
            Assert.assertTrue(Selenide.$(By.xpath("//h1[contains(.,'Want to make the best app in the world?')]")).waitUntil(Condition.visible, 5000).exists());
        } else if (trigger == "aboutus" ){
            $(By.xpath(Locators.aboutus)).waitUntil(Condition.visible, 5000).click();
            Assert.assertTrue(Selenide.$(By.xpath("//h1[contains(.,'Yourdigitaltransformation partner')]")).waitUntil(Condition.visible, 5000).exists());
        } else if (trigger == "blog"){
            $(By.xpath(Locators.blog)).waitUntil(Condition.visible, 5000).click();
            Assert.assertTrue(Selenide.$(By.xpath("//h1[contains(.,'Blog')]")).waitUntil(Condition.visible, 5000).exists());
        } else if (trigger == "contacts"){
            $(By.xpath(Locators.contacts)).waitUntil(Condition.visible, 5000).click();
            Assert.assertTrue(Selenide.$(By.xpath("//strong[contains(.,'We are just one')]")).waitUntil(Condition.visible, 5000).exists());
        } else {
          throw new IllegalArgumentException("No such Navbar option");
        }
        return this;
    }

    public NetGroupPage SNavbar(String trigger){
        if(trigger == "digital business"){
            $(By.id(Locators.DigitalB)).click();
        } else if (trigger =="ecommerce"){
            $(By.id(Locators.Ecommerce)).click();
        } else if (trigger =="digital governance"){
            $(By.id(Locators.DigitalG)).click();
        } else if (trigger =="fintech"){
            $(By.id(Locators.Fintech)).click();
        } else if (trigger =="utilities"){
            $(By.id(Locators.Utilities)).click();
        } else {
            throw new IllegalArgumentException("No such Services Navbar option");
        }
        return this;
    }

}

