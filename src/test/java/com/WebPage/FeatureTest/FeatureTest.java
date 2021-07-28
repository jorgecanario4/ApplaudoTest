package com.WebPage.FeatureTest;

import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;

public abstract class FeatureTest {
	final String API_BASE_URI = "http://automationpractice.com/index.php";
	WebDriver driver;
	
  @BeforeSuite
  public void beforeSuite() {
	  System.setProperty("webdriver.chrome.driver", "./src/test/resources/BrowserDriver/chromedriver");           
	  driver = new ChromeDriver(); 
	  driver.manage().window().maximize();
	  driver.get(API_BASE_URI);    
	  
  }

  @AfterSuite
  public void afterSuite() {
	  driver.quit();  
  }

}
