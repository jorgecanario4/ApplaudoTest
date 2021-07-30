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


public class FooterTest extends FeatureTest{
	protected By footerInformationLotation = By.id("block_contact_infos"); 
	protected ArrayList<String> expectedFooterInformations = new ArrayList<>(Arrays.asList("Store information\nSelenium Framework, Research Triangle Park, North Carolina, USA", 
              "Call us now: (347) 466-7432",
              "Email: support@seleniumframework.com" ));
	
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
