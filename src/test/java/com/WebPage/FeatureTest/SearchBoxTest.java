package com.WebPage.FeatureTest;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;


public class SearchBoxTest extends FeatureTest{
	
	By searchBoxLocation = By.id("search_query_top");
	By resultLocation = By.cssSelector("div.product-count");
	By noResultWarningLocation = By.cssSelector("p.alert.alert-warning");
	
	
	private boolean isThereResults(String input) {
		Boolean output = true;
		
		try {
			WebElement searchBox = driver.findElement(searchBoxLocation);
			searchBox.clear();
			searchBox.sendKeys(input);
			searchBox.submit();
		}
		catch(NoSuchElementException e) {
			System.out.print("Search box element doesn't exist");
			output= false;
		}
		catch (TimeoutException e) {
			System.out.print("Page didn't load correctly");
			output = false;
		}
		
		
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(resultLocation));
		} 
		catch(NoSuchElementException e) {
			wait.until(ExpectedConditions.presenceOfElementLocated(noResultWarningLocation));
			System.out.println("Page confirmed there was no result found");
			output= false;
		}
		catch (TimeoutException e) {
			System.out.print("Page took too long to respond");
			output = false;
		}
		
		return output;
	}
	
  @Test(enabled=false)
  public void testSearchBox() {
	  String negativeCase = "@#$%^^$@";
	  String positiveCase = "printed";
	  assertTrue(isThereResults(positiveCase), "No result found for an item that should be available: "+ positiveCase);
	  assertFalse(isThereResults(negativeCase), "There was a result for an item that shouldn't be available: " + negativeCase);
	
  }
  

}
