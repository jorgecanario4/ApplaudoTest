package com.WebPage.FeatureTest;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


public class FooterTest extends FeatureTest{
	final String expectedFooterInformation = "Selenium Framework, Research Triangle Park, North Carolina, USA";
	By footerInformationLotation = By.id("block_contact_infos"); 
	
	
  @Test
  public void validateFooterInformation() {
	  Boolean result=false;
	  jsExecutor.executeScript( "window.scrollTo(0, document.body.scrollHeight)");
	  
	  try {
		  WebElement footerInformation = driver.findElement(footerInformationLotation);
		  result = footerInformation.getText().contains(expectedFooterInformation); 
		} 
	  catch(NoSuchElementException e) {
			System.out.println("Page has no footer information");
		}
	  catch (TimeoutException e) {
			System.out.print("Page took too long to respond");
		}
	  
	  assertTrue(result, "Footer information is not matching expected Information");	  
  }
  


}
