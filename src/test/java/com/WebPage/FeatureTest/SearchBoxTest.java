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


/**
 * This class validate that search box is able to find elements offered by the web page.
 * @author Jorge Canario
 *
 */

public class SearchBoxTest extends FeatureTest{
	/** Is the locator for the search box in the HTML. It can change only if DEVs change this element ID in the HTML */
	private By searchBoxLocation = By.id("search_query_top");
	/** Is the locator for the for the product count in the result page in the HTML. Basically, if no result's found there will be nothing to count and this can be a way for us to know that there were no results. It can change only if DEVs change this element ID in the HTML*/
	private By resultLocation = By.cssSelector("div.product-count");
	/** Is the locator for the banner that informs that "there were no result" in the HTML. It can change only if DEVs change this element ID in the HTML */
	private By noResultWarningLocation = By.cssSelector("p.alert.alert-warning");
	
	/**
	 * Just to make sure we are located on a page that is in fact supposed to show the search box, before doing the test we navigate to the index page
	 * 
	 * @author Jorge Canario
	 */

	@BeforeClass
	public void beforeClass() {
	  driver.get(API_BASE_URI);
	}
	
	/**
	 * This functions just check if the input generate or not a result
	 * @param input		Is the text that going to be submitted to the search box
	 * @return true		Is input did generate a result in the search box, false otherwise
	 * @author Jorge Canario
	 */
	
	private boolean isThereResults(String input) {
		Boolean output = true;
		
		try {
			WebElement searchBox = driver.findElement(searchBoxLocation);
			searchBox.clear();
			searchBox.sendKeys(input);
			searchBox.submit();
		}
		catch(NoSuchElementException e) {
			Reporter.log("[ERROR:] Search box element doesn't exist");
			output= false;
		}
		catch (TimeoutException e) {
			Reporter.log("[ERROR:] Page didn't load correctly");
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
				Reporter.log("[ERROR:] Page \"No Result\" banner didn't show up");
				output= false;
			}
			Reporter.log("[ERROR:] Page confirmed there was no result found");
			output= false;
		}
		
		return output;
	}
	
	/**
	 * This is the formal test to know if the search box is returning values when it should 
	 * 
	 * This test considers 2 scenarios. One in which the item is supposed to return something and one in which it shouldn't
	 * @author Jorge Canario
	 */
	
  @Test
  public void testSearchBox() {
	  String negativeCase = "@#$%^^$@";
	  String positiveCase = "printed";
	  AssertJUnit.assertTrue("No result found for an item that should be available: "+ positiveCase, isThereResults(positiveCase));
	  AssertJUnit.assertFalse("There was a result for an item that shouldn't be available: " + negativeCase, isThereResults(negativeCase));
	
  }
  

}
