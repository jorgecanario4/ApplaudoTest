package com.WebPage.FeatureTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;




public class SearchBoxTest extends FeatureTest{
	
	By searchBoxLocation = By.id("search_query_top");
	By resultLocation = By.cssSelector("div.product-count");
	By noResultWarningLocation = By.cssSelector("p.alert.alert-warning");

	  @BeforeClass
	  public void beforeSuite() {
		  driver.get(API_BASE_URI);
	  }
	
	private boolean isThereResults(String input) {
		Boolean output = true;
		
		try {
			WebElement searchBox = driver.findElement(searchBoxLocation);
			searchBox.clear();
			searchBox.sendKeys(input);
			searchBox.submit();
		}
		catch(NoSuchElementException e) {
			Reporter.log("Search box element doesn't exist");
			output= false;
		}
		catch (TimeoutException e) {
			Reporter.log("Page didn't load correctly");
			output = false;
		}
		
		
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(resultLocation));
		} 
		catch (TimeoutException e) {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(noResultWarningLocation));
			}
			catch(TimeoutException ex) {
				Reporter.log("Page \"No Result\" banner didn't show up");
				output= false;
			}
			Reporter.log("Page confirmed there was no result found");
			output= false;
		}
		
		return output;
	}
	
  @Test
  public void testSearchBox() {
	  String negativeCase = "@#$%^^$@";
	  String positiveCase = "printed";
	  AssertJUnit.assertTrue("No result found for an item that should be available: "+ positiveCase, isThereResults(positiveCase));
	  AssertJUnit.assertFalse("There was a result for an item that shouldn't be available: " + negativeCase, isThereResults(negativeCase));
	
  }
  

}
