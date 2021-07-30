package com.WebPage.FeatureTest;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public abstract class FeatureTest {
	//Still pending some improvement to access locators by metadata on a file instead of hard coded
	//Still pending javadoc and maven site documentation 
	protected final String API_BASE_URI = "http://automationpractice.com/index.php";
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected static Actions action;
	protected static JavascriptExecutor jsExecutor;
	
  @BeforeTest
  public void beforeSuite() {
	  System.setProperty("webdriver.chrome.driver", "./src/test/resources/BrowserDriver/chromedriver");           
	  driver = new ChromeDriver(); 
	  driver.manage().window().maximize();
	  driver.get(API_BASE_URI); 
	  
	  action= new Actions(driver);
	  wait = new WebDriverWait(driver, 7);
	  jsExecutor = (JavascriptExecutor) driver;
  }

  @AfterTest
  public void afterSuite() {
	  driver.quit();  
  }

}
