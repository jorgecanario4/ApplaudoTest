package com.WebPage.FeatureTest;

import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;

public abstract class FeatureTest {
	//Still pending some improvement to access locators by metadata on a file instead of hard coded
	//Still pending javadoc and documentation 
	final String API_BASE_URI = "http://automationpractice.com/index.php";
	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	JavascriptExecutor jsExecutor;
	
  @BeforeSuite
  public void beforeSuite() {
	  System.setProperty("webdriver.chrome.driver", "./src/test/resources/BrowserDriver/chromedriver");           
	  driver = new ChromeDriver(); 
	  driver.manage().window().maximize();
	  driver.get(API_BASE_URI); 
	  
	  action= new Actions(driver);
	  wait = new WebDriverWait(driver, 7);
	  jsExecutor = (JavascriptExecutor) driver;
  }

  @AfterSuite
  public void afterSuite() {
	  driver.quit();  
  }

}
