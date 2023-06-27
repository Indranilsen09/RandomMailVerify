package uipage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import uiLocators.instaLocators;
import uiLocators.mailinatorLocators;

public class InstagramSignUp
{
	static WebDriver driver;
	static String emailAddress = pageObject.generateMail();
	
	@BeforeTest
	static void driverSetup() 
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.instagram.com/accounts/emailsignup/");
		driver.manage().window().maximize();
		
	}
	
	@Test
	static void SignUp() throws InterruptedException
	{
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(instaLocators.txt_instaLogo));
		
		WebElement email =driver.findElement(instaLocators.txt_email);
		email.click();
		email.sendKeys(emailAddress);
		
		WebElement fullname = driver.findElement(instaLocators.txt_fullName);
		fullname.click();
		fullname.sendKeys(emailAddress.substring(0,8));
		Thread.sleep(3000);
		WebElement username = driver.findElement(instaLocators.userName);
		username.click();
		username.sendKeys(emailAddress.substring(0,8));
		Thread.sleep(3000);
		
		WebElement password = driver.findElement(instaLocators.password);
		password.click();
		password.sendKeys("Anything@123");

		WebElement signup = driver.findElement(instaLocators.btn_SignUp);
		signup.click();
		Thread.sleep(10000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(instaLocators.txt_addBday));
		
		Select slt_month = new Select(driver.findElement(By.xpath("//Select[@title='Month:']")));
		slt_month.selectByVisibleText("September");
		Thread.sleep(2000);
		Select slt_day = new Select(driver.findElement(By.xpath("//Select[@title='Day:']")));
		slt_day.selectByValue("29");
		Thread.sleep(2000);
		
		Select slt_year = new Select(driver.findElement(By.xpath("//select[@title='Year:']")));
		slt_year.selectByValue("1999");
		Thread.sleep(2000);
		driver.findElement(instaLocators.btn_next).click();
		Thread.sleep(10000);
		
		//verification
		VerifyMail();
	}
	
	static void VerifyMail() 
	{
		
		driver.switchTo().newWindow(WindowType.WINDOW);
		driver.get("https://www.mailinator.com/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		WebElement searchBox = 		driver.findElement(mailinatorLocators.txt_searchBox);
		searchBox.click();
		searchBox.sendKeys(emailAddress);
		driver.findElement(mailinatorLocators.btn_Go).click();
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.urlContains("public/inboxes"));
		driver.findElement(mailinatorLocators.btn_Go1).click();
		
	}

}
