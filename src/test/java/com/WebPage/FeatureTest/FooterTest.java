package com.WebPage.FeatureTest;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


public class FooterTest extends FeatureTest{
	final String expectedFooterInformation = "";
	By footerInformationLotation = By.xpath(""); //page is not responding let's wait until it is solved
	
	
  @Test
  public void validateFooterInformation() {
	  JavascriptExecutor action = (JavascriptExecutor) driver;
	  action.executeScript("window.scrollBy(0,2100)"); 
	  
	  WebElement footerInformation = driver.findElement(footerInformationLotation);
	  
	  Boolean result = footerInformation.getText().equals(""); //When page becomes responsive I'll put the text
	  
	  assertTrue(result, "Footer information is not matching expected Information");
	  
  }
  


}
