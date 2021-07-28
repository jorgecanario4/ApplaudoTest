package com.WebPage.FeatureTest;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class SearchBoxTest extends FeatureTest{
	
	By searchBoxLocation = By.id("search_query_top");
	By resultLocation = By.cssSelector("div.product-count");
	
	
	private boolean isThereResults(String input) {
		WebElement searchBox = driver.findElement(searchBoxLocation);
		searchBox.clear();
		searchBox.sendKeys(input);
		searchBox.submit();
		  
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.presenceOfElementLocated(resultLocation));
		
		return driver.findElement(resultLocation).isDisplayed();
	}
  @Test
  public void testSearchBox() {
	  String negativeCase = "@#$%^^$@";
	  String positiveCase = "printed";
	  assertTrue(isThereResults(positiveCase), "No Result found for an item that should be available: "+ positiveCase);
	  assertFalse(isThereResults(negativeCase), "There was a result for an item that shouldn't be available: " + negativeCase);
	
  }
  

}
