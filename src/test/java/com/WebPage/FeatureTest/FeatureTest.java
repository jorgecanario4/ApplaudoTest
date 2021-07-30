package com.WebPage.FeatureTest;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

/**
 * This is an abstract class that defines all necessary elements to work on a feature test.
 * When referring to a feature test, we are referring to an implementation inside a web page,
 * we do know that a web page could have different functionalities; to name each functionality
 * we've called it "features".
 * 
 * Since we are referring to an abstract class, it cannot be instantiated and has no meaning on its own.
 * a feature can only be thought in terms of any child class. Thus, all test in this package should inherit from 
 * this class.
 * 
 * Class is still open for some improvement, like getting constant variables (final) and locators (By) from a metadata file. This would all any modification in the HTML
 * to require a code change.
 * @author Jorge Canario
 *
 */
public abstract class FeatureTest {
	/**
	 * @value API_BASE_URI	This value represents the web page URI for the test and is supposed to not be changed by any means
	 * @value driver 		This is the variable that controls the browser driver. Based on the architecture is expected that only one instance of the driver should be running, unless defined inside a child class
	 * @value action 		All child class must perform some actions with the elements inside the page. This variable offers the possibility to perform actions. Variable is also static as it should be performing actions inside the only driver available.
	 * @value jsExecutor 	Child class will need to perform some actions that are independent from the elements of the page. Thus, this variable offers the possibility to do so
	 * @value wait			Is a variable that allows child classes to wait explicitly or fluently
	 */
	protected final String API_BASE_URI = "http://automationpractice.com/index.php";
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected static Actions action;
	protected static JavascriptExecutor jsExecutor;
	
	/**
	 * Since every feature will need a WebDriver, we have defined a unique driver
	 * so it can be accessed by any child class. 
	 * 
	 * This offers benefits like not having to open another browser
	 * to perform similar test but there are some drawbacks too. When having just a 
	 * unique instance of a driver parallel testing for class can have an unexpected behavior.
	 * 
	 * As requested, driver ran maximized so all steps are visible to tester.
	 * 
	 * Also, for the moment test is only supported for Google Chrome Browser
	 * 
	 * @author Jorge Canario
	 */
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

  /**
   * After all test is done, the instance of the browser should be closed.
   * @author Jorge Canario
   */
  @AfterTest
  public void afterSuite() {
	  driver.quit();  
  }

}
