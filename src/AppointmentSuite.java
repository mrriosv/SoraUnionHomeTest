import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AppointmentSuite {
     @Test
    public void areRadioButtonsVisible() {
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
    WebElement medicareRadioBTN= driver.findElement(By.id("radio_program_medicare"));
    WebElement medicaidRadioBTN= driver.findElement(By.id("radio_program_medicaid"));
    WebElement noneRadioBTN= driver.findElement(By.id("radio_program_none"));
    Assert.assertNotNull(medicareRadioBTN);
    Assert.assertNotNull(medicaidRadioBTN);
    Assert.assertNotNull(noneRadioBTN);
    }
    @Test
    public void areDropdownFacilitiesVisible() {
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
        Select facilityOptions = new Select(driver.findElement(By.id("combo_facility")));
        List<WebElement> options = facilityOptions.getOptions();
        String option1="Tokyo CURA Healthcare Center";
        String option2="Hongkong CURA Healthcare Center";
        String option3="Seoul CURA Healthcare Center";
        Assert.assertEquals(options.get(0).getText(),option1);
        Assert.assertEquals(options.get(1).getText(),option2);
        Assert.assertEquals(options.get(2).getText(),option3);
        Assert.assertEquals(options.size(),3);
    }
    @Test
    public void isAppointmentConfirmed() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String str = formatter.format(date);
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
        WebElement datePicker = driver.findElement(By.id("txt_visit_date"));
        datePicker.sendKeys("12/02/2024");
        WebElement commentBox = driver.findElement(By.id("txt_comment"));
        commentBox.sendKeys("for testing " +str);
        WebElement bookButton = driver.findElement(By.id("btn-book-appointment"));
        bookButton.click();
        String expectedConfirmationMessage = "Please be informed that your appointment has been booked as following:";
        String expectedComment = "for testing " +str;
        String expectedDate = "12/02/2024";
        WebElement confirmationMessage = driver.findElement(By.xpath("//*[@id=\"summary\"]/div/div/div[1]/p"));
        WebElement bookedDate = driver.findElement(By.id("visit_date"));
        WebElement bookedComment = driver.findElement(By.id("comment"));

        Assert.assertEquals(expectedConfirmationMessage,confirmationMessage.getText());
        Assert.assertEquals(expectedComment,bookedComment.getText());
        Assert.assertEquals(expectedDate, bookedDate.getText());
    }
}