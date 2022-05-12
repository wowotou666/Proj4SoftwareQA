// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class D3Test {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "Chrome/chromedriver-mac-intel");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void tESTLINKS() {
    // Test name: TEST-LINKS
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://cs1632.appspot.com/");
    // 2 | setWindowSize | 1200x800 | 
    driver.manage().window().setSize(new Dimension(1200, 800));
    // 3 | verifyElementPresent | linkText=Reset | 30000
    {
      List<WebElement> elements = driver.findElements(By.linkText("Reset"));
      assert(elements.size() > 0);
    }
    // 4 | storeAttribute | linkText=Reset@href | link
    {
      WebElement element = driver.findElement(By.linkText("Reset"));
      String attribute = element.getAttribute("href");
      vars.put("link", attribute);
    }
    // 5 | assert | link | /reset
    assertEquals(vars.get("link").toString(), "https://cs1632.appspot.com/reset");
  }
  @Test
  public void tESTCATALOG() {
    // Test name: TEST-CATALOG
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://cs1632.appspot.com/");
    // 2 | setWindowSize | 1200x800 | 
    driver.manage().window().setSize(new Dimension(1200, 800));
    // 3 | storeAttribute | xpath=//img[@alt='Old Deuteronomy']@src | url
    {
      WebElement element = driver.findElement(By.xpath("//img[@alt=\'Old Deuteronomy\']"));
      String attribute = element.getAttribute("src");
      vars.put("url", attribute);
    }
    // 4 | assert | url | /images/cat2.jpg
    assertEquals(vars.get("url").toString(), "https://cs1632.appspot.com/images/cat2.jpg");
  }
  @Test
  public void tESTFEEDACAT() {
    // Test name: TEST-FEED-A-CAT
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://cs1632.appspot.com/");
    // 2 | setWindowSize | 1200x800 | 
    driver.manage().window().setSize(new Dimension(1200, 800));
    // 3 | click | linkText=Feed-A-Cat | 
    driver.findElement(By.linkText("Feed-A-Cat")).click();
    // 4 | assertElementPresent | xpath=//button[@onclick='feedSubmit()'] | 
    {
      List<WebElement> elements = driver.findElements(By.xpath("//button[@onclick=\'feedSubmit()\']"));
      assert(elements.size() > 0);
    }
  }
  @Test
  public void tESTGREETACAT() {
    // Test name: TEST-GREET-A-CAT
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://cs1632.appspot.com/");
    // 2 | setWindowSize | 1200x800 | 
    driver.manage().window().setSize(new Dimension(1200, 800));
    // 3 | click | linkText=Greet-A-Cat | 
    driver.findElement(By.linkText("Greet-A-Cat")).click();
    // 4 | assertText | xpath=//div[@id='greeting']/h4 | Meow!Meow!Meow!
    assertThat(driver.findElement(By.xpath("//div[@id=\'greeting\']/h4")).getText(), is("Meow!Meow!Meow!"));
  }
  @Test
  public void tESTGREETACATWITHNAME() {
    // Test name: TEST-GREET-A-CAT-WITH-NAME
    // Step # | name | target | value
    // 1 | open | /greet-a-cat/Jennyanydots | 
    driver.get("https://cs1632.appspot.com/greet-a-cat/Jennyanydots");
    // 2 | setWindowSize | 1200x800 | 
    driver.manage().window().setSize(new Dimension(1200, 800));
    // 3 | assertText | xpath=//div[@id='greeting']/h4 | Meow! from Jennyanydots.
    assertThat(driver.findElement(By.xpath("//div[@id=\'greeting\']/h4")).getText(), is("Meow! from Jennyanydots."));
  }
  @Test
  public void tESTLISTING() {
    // Test name: TEST-LISTING
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://cs1632.appspot.com/");
    // 2 | setWindowSize | 1200x800 | 
    driver.manage().window().setSize(new Dimension(1200, 800));
    // 3 | storeXpathCount | xpath=//div[@id='listing']/ul/li | count
    vars.put("count", driver.findElements(By.xpath("//div[@id=\'listing\']/ul/li")).size());
    // 4 | assert | count | 3
    assertEquals(vars.get("count").toString(), "3");
    // 5 | assertText | xpath=//div[@id='listing']/ul/li[3] | ID 3. Mistoffelees
    assertThat(driver.findElement(By.xpath("//div[@id=\'listing\']/ul/li[3]")).getText(), is("ID 3. Mistoffelees"));
  }
  @Test
  public void tESTRENT() {
    // Test name: TEST-RENT
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://cs1632.appspot.com/");
    // 2 | setWindowSize | 1200x800 | 
    driver.manage().window().setSize(new Dimension(1200, 800));
    // 3 | click | linkText=Rent-A-Cat | 
    driver.findElement(By.linkText("Rent-A-Cat")).click();
    // 4 | sendKeys | id=rentID | 2
    driver.findElement(By.id("rentID")).sendKeys("2");
    // 5 | click | xpath=//button[@onclick='rentSubmit()'] | 
    driver.findElement(By.xpath("//button[@onclick=\'rentSubmit()\']")).click();
    // 6 | assertText | xpath=//div/ul/li[2] | Rented out
    assertThat(driver.findElement(By.xpath("//div/ul/li[2]")).getText(), is("Rented out"));
    // 7 | assertElementPresent | xpath=//div[@id='rentResult'] | 
    {
      List<WebElement> elements = driver.findElements(By.xpath("//div[@id=\'rentResult\']"));
      assert(elements.size() > 0);
    }
    // 8 | assertText | xpath=//div[@id='rentResult'] | Success!
    assertThat(driver.findElement(By.xpath("//div[@id=\'rentResult\']")).getText(), is("Success!"));
  }
  @Test
  public void tESTRENTACAT() {
    // Test name: TEST-RENT-A-CAT
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://cs1632.appspot.com/");
    // 2 | setWindowSize | 1200x800 | 
    driver.manage().window().setSize(new Dimension(1200, 800));
    // 3 | click | linkText=Rent-A-Cat | 
    driver.findElement(By.linkText("Rent-A-Cat")).click();
    // 4 | assertElementPresent | xpath=//button[@onclick='rentSubmit()'] | 
    {
      List<WebElement> elements = driver.findElements(By.xpath("//button[@onclick=\'rentSubmit()\']"));
      assert(elements.size() > 0);
    }
    // 5 | assertElementPresent | xpath=//button[@onclick='returnSubmit()'] | 
    {
      List<WebElement> elements = driver.findElements(By.xpath("//button[@onclick=\'returnSubmit()\']"));
      assert(elements.size() > 0);
    }
  }
  @Test
  public void tESTRESET() {
    // Test name: TEST-RESET
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://cs1632.appspot.com/");
    // 2 | setWindowSize | 1200x800 | 
    driver.manage().window().setSize(new Dimension(1200, 800));
    // 3 | click | linkText=Rent-A-Cat | 
    driver.findElement(By.linkText("Rent-A-Cat")).click();
    // 4 | sendKeys | xpath=//input[@id='rentID'] | 2
    driver.findElement(By.xpath("//input[@id=\'rentID\']")).sendKeys("2");
    // 5 | click | xpath=//button[@onclick='rentSubmit()'] | 
    driver.findElement(By.xpath("//button[@onclick=\'rentSubmit()\']")).click();
    // 6 | click | linkText=Reset | 
    driver.findElement(By.linkText("Reset")).click();
    // 7 | assertText | xpath=//div[@id='listing']/ul/li[1] | ID 1. Jennyanydots
    assertThat(driver.findElement(By.xpath("//div[@id=\'listing\']/ul/li[1]")).getText(), is("ID 1. Jennyanydots"));
    // 8 | assertText | xpath=//div[@id='listing']/ul/li[2] | ID 2. Old Deuteronomy
    assertThat(driver.findElement(By.xpath("//div[@id=\'listing\']/ul/li[2]")).getText(), is("ID 2. Old Deuteronomy"));
    // 9 | assertText | xpath=//div[@id='listing']/ul/li[3] | ID 3. Mistoffelees
    assertThat(driver.findElement(By.xpath("//div[@id=\'listing\']/ul/li[3]")).getText(), is("ID 3. Mistoffelees"));
  }
  @Test
  public void tESTRETURN() {
    // Test name: TEST-RETURN
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://cs1632.appspot.com/");
    // 2 | setWindowSize | 1200x800 | 
    driver.manage().window().setSize(new Dimension(1200, 800));
    // 3 | click | linkText=Rent-A-Cat | 
    driver.findElement(By.linkText("Rent-A-Cat")).click();
    // 4 | sendKeys | xpath=//input[@id='rentID'] | 2
    driver.findElement(By.xpath("//input[@id=\'rentID\']")).sendKeys("2");
    // 5 | click | xpath=//button[@onclick='rentSubmit()'] | 
    driver.findElement(By.xpath("//button[@onclick=\'rentSubmit()\']")).click();
    // 6 | sendKeys | xpath=//input[@id='returnID'] | 2
    driver.findElement(By.xpath("//input[@id=\'returnID\']")).sendKeys("2");
    // 7 | click | xpath=//button[@onclick='returnSubmit()'] | 
    driver.findElement(By.xpath("//button[@onclick=\'returnSubmit()\']")).click();
    // 8 | assertText | xpath=//div[@id='listing']/ul/li[2] | ID 2. Old Deuteronomy
    assertThat(driver.findElement(By.xpath("//div[@id=\'listing\']/ul/li[2]")).getText(), is("ID 2. Old Deuteronomy"));
    // 9 | assertElementPresent | xpath=//div[@id='returnResult'] | 
    {
      List<WebElement> elements = driver.findElements(By.xpath("//div[@id=\'returnResult\']"));
      assert(elements.size() > 0);
    }
    // 10 | assertText | xpath=//div[@id='returnResult'] | Success!
    assertThat(driver.findElement(By.xpath("//div[@id=\'returnResult\']")).getText(), is("Success!"));
  }
  @Test
  public void dEFECT1FUNGREETACATWITHNAME() {
    // Test name: DEFECT1-FUN-GREET-A-CAT
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://cs1632.appspot.com/");
    // 2 | setWindowSize | 1200x800 | 
    driver.manage().window().setSize(new Dimension(1200, 800));
    // 3 | click | linkText=Rent-A-Cat | 
    driver.findElement(By.linkText("Rent-A-Cat")).click();
    // 4 | sendKeys | xpath=//input[@id='rentID'] | 1
    driver.findElement(By.xpath("//input[@id=\'rentID\']")).sendKeys("1");
    // 5 | click | xpath=//button[@onclick='rentSubmit()'] | 
    driver.findElement(By.xpath("//button[@onclick=\'rentSubmit()\']")).click();
    // 6 | open | /greet-a-cat/Jennyanydots | 
    driver.get("https://cs1632.appspot.com/greet-a-cat/Jennyanydots");
    // 7 | assertText | xpath=//div[@id='greeting']/h4 | Jennyanydots is not here.
    assertThat(driver.findElement(By.xpath("//div[@id=\'greeting\']/h4")).getText(), is("Jennyanydots is not here."));
  }
  @Test
  public void dEFECT2FUNGREETACAT() {
    // Test name: DEFECT2-FUN-GREET-A-CAT
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://cs1632.appspot.com/");
    // 2 | setWindowSize | 1200x800 | 
    driver.manage().window().setSize(new Dimension(1200, 800));
    // 3 | click | linkText=Rent-A-Cat | 
    driver.findElement(By.linkText("Rent-A-Cat")).click();
    // 4 | sendKeys | xpath=//input[@id='rentID'] | 1
    driver.findElement(By.xpath("//input[@id=\'rentID\']")).sendKeys("1");
    // 5 | click | xpath=//button[@onclick='rentSubmit()'] | 
    driver.findElement(By.xpath("//button[@onclick=\'rentSubmit()\']")).click();
    // 6 | click | linkText=Greet-A-Cat | 
    driver.findElement(By.linkText("Greet-A-Cat")).click();
    // 7 | assertText | xpath=//div[@id='greeting']/h4 | Meow!Meow!
    assertThat(driver.findElement(By.xpath("//div[@id=\'greeting\']/h4")).getText(), is("Meow!Meow!"));
  }
  @Test
  public void dEFECT3FUNFEED() {
    // Test name: DEFECT3-FUN-FEED
    // Step # | name | target | value
    // 1 | open | /feed-a-cat | 
    driver.get("https://cs1632.appspot.com/feed-a-cat");
    // 2 | sendKeys | xpath=//input[@id='catnips'] | 0
    driver.findElement(By.xpath("//input[@id=\'catnips\']")).sendKeys("0");
    // 3 | click | xpath=//button[@onclick='feedSubmit()'] | 
    driver.findElement(By.xpath("//button[@onclick=\'feedSubmit()\']")).click();
    // 4 | assertText | xpath=//div[@id='feedResult'] | Cat fight!
    assertThat(driver.findElement(By.xpath("//div[@id=\'feedResult\']")).getText(), is("Cat fight!"));
  }
  @Test
  public void tESTFEED() {
    // Test name: TEST-FEED
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://cs1632.appspot.com/");
    // 2 | setWindowSize | 1200x800 | 
    driver.manage().window().setSize(new Dimension(1200, 800));
    // 3 | click | linkText=Feed-A-Cat | 
    driver.findElement(By.linkText("Feed-A-Cat")).click();
    // 4 | sendKeys | xpath=//input[@id='catnips'] | 6
    driver.findElement(By.xpath("//input[@id=\'catnips\']")).sendKeys("6");
    // 5 | click | xpath=//button[@onclick='feedSubmit()'] | 
    driver.findElement(By.xpath("//button[@onclick=\'feedSubmit()\']")).click();
    // 6 | assertText | xpath=//div[@id='feedResult'] | Nom, nom, nom.
    assertThat(driver.findElement(By.xpath("//div[@id=\'feedResult\']")).getText(), is("Nom, nom, nom."));
  }
}
