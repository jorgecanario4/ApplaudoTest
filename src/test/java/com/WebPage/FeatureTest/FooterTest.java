package com.WebPage.FeatureTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;


public class FooterTest extends FeatureTest{
	By footerInformationLotation = By.id("block_contact_infos"); 
	Iterator<String> expectedFooterInformations = new ArrayList<>(Arrays.asList("Selenium Framework, Research Triangle Park, North Carolina, USA", 
              "better_call_saul_appearance", "Call us now: (347) 466-7432",
              "Email: support@seleniumframework.com" )).iterator();
	
  @Test
  public void validateFooterInformation() {
	  Boolean result=false;
	  jsExecutor.executeScript( "window.scrollTo(0, document.body.scrollHeight)");
	  
	  try {
		  WebElement footerInformation = driver.findElement(footerInformationLotation);
		  String footerInformationText = footerInformation.getText();
		  while(expectedFooterInformations.hasNext()) {
			  result = footerInformationText.contains(expectedFooterInformations.next()); 
		  }
		} 
	  catch(NoSuchElementException e) {
			System.out.println("Page has no footer information");
		}
	  catch (TimeoutException e) {
			System.out.print("Page took too long to respond");
		}
	  
	  AssertJUnit.assertTrue("Footer information is not matching expected Information", result);	  
  }
  


}
