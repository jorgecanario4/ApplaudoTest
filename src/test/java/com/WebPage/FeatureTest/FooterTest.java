package com.WebPage.FeatureTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
/**
 * This class should take the responsibility to test informational changes in the page.
 * For sake of this technical exam is it just testing the correct data is contained in the footer's store information
 * 
 * Test extends from FeatureTest class to make its manipulation more easy
 * @author Jorge Canario
 *
 */

public class FooterTest extends FeatureTest{
	/**
	 * @value footerInformationLotation			Contains the locator to get to the footer's store infomation section. This location could change if developer change the ID of the element in the HTML
	 * @value expectedFooterInformations		Contains a List with all the information we want to make sure is contained in the footer
	 */
	private By footerInformationLotation = By.id("block_contact_infos"); 
	private ArrayList<String> expectedFooterInformations = new ArrayList<>(Arrays.asList("Store information\nSelenium Framework, Research Triangle Park, North Carolina, USA", 
              "Call us now: (347) 466-7432",
              "Email: support@seleniumframework.com" ));
	
	/**
	 * This test scrolls to the bottom of the page independent from any element in the page and
	 * confirms all store information is the expected
	 * 
	 * It compares and only if one of the expected information differs that would be enough to trigger an Assertion Exception. Also, if the store information is not found or if it didn't load correctly test will fail
	 * @author Jorge Canario
	 */
	
  @Test
  public void validateFooterInformation() {
	  Boolean result=true;
	  jsExecutor.executeScript( "window.scrollTo(0, document.body.scrollHeight)");
	  
	  try {
		  WebElement footerInformation = driver.findElement(footerInformationLotation);
		  String footerInformationText = footerInformation.getText();
		  Reporter.log("[INFORMATION ON FOOTER:] "+ footerInformationText+"\n\n");
		  
		  for(String expectedValue: expectedFooterInformations ) {
			  Reporter.log("[EXPECTED INFORMATION VALUES:] "+ expectedValue+"\n\n");
			  if(result)
				  result = footerInformationText.contains(expectedValue); 
			  else
				  break;
			  Reporter.log("[COMPARISON RESULT:] "+ result+"\n\n");
		  }
		} 
	  catch(NoSuchElementException e) {
			Reporter.log("[ERROR:] Page has no footer information");
			result=false;
		}
	  catch (TimeoutException e) {
		  	Reporter.log("[ERROR:] Page took too long to respond");
		  	result=false;
		}
	  
	  AssertJUnit.assertTrue("Footer information is not matching expected Information", result);	  
  }
  


}
