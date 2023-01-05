import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
public class LoginSuite {
    @Test
    public void loginThroughHomeScreenButton() {
        ChromeDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://katalon-demo-cura.herokuapp.com/");
        WebElement menuButton=driver.findElement(By.id("btn-make-appointment"));
        menuButton.click();
        WebElement username=driver.findElement(By.id("txt-username"));
        WebElement password=driver.findElement(By.id("txt-password"));
        Assert.assertNotNull(username);
        Assert.assertNotNull(password);
    }
    @Test
    public void successfulLogin() {
        ChromeDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://katalon-demo-cura.herokuapp.com/");
        WebElement menuButton=driver.findElement(By.id("menu-toggle"));
        menuButton.click();
        WebElement loginMenu=driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[3]/a"));
        loginMenu.click();
        WebElement loginButton=driver.findElement(By.id("btn-login"));
        WebElement username=driver.findElement(By.id("txt-username"));
        WebElement password=driver.findElement(By.id("txt-password"));
        username.sendKeys("John Doe");
        password.sendKeys("ThisIsNotAPassword");
        loginButton.click();
        String actualUrl="https://katalon-demo-cura.herokuapp.com/#appointment";
        String expectedUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
    }
    @Test
    public void failedLogin() {
        ChromeDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://katalon-demo-cura.herokuapp.com/");
        WebElement menuButton=driver.findElement(By.id("menu-toggle"));
        menuButton.click();
        WebElement loginMenu=driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[3]/a"));
        loginMenu.click();
        WebElement loginButton=driver.findElement(By.id("btn-login"));
        WebElement username=driver.findElement(By.id("txt-username"));
        WebElement password=driver.findElement(By.id("txt-password"));
        username.sendKeys("x");
        password.sendKeys("x");
        loginButton.click();
        WebElement warningMessage = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/div[1]/p[2]"));
        String expectedWarningMessage= "Login failed! Please ensure the username and password are valid.";
        String actualWarningMessage=warningMessage.getText();
        Assert.assertEquals(expectedWarningMessage,actualWarningMessage);
    }
}
