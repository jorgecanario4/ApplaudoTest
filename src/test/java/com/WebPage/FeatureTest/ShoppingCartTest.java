package com.WebPage.FeatureTest;

import org.openqa.selenium.By;

import org.testng.annotations.Test;


public class ShoppingCartTest extends FeatureTest{
	
	By testItemAddToCartButtonLocation = By.xpath("//*[@id=\"homefeatured\"]/li[4]/div/div[2]/div[2]/a[1]/span");
	By testItemImg = By.xpath("//*[@id=\"homefeatured\"]/li[4]/div/div[1]/div/a[1]/img");
  @Test
  public void testAddItemToShoppingCart() {
//	  WebElement testItemAddToCartButton = driver.findElement(testItemAddToCartButtonLocation);
//	  
//	  JavascriptExecutor action = (JavascriptExecutor) driver;
//	  action.executeScript("window.scrollBy(0,1100)"); 
//	  
//	  WebDriverWait wait = new WebDriverWait(driver, 7);
//	  wait.until(ExpectedConditions.presenceOfElementLocated(testItemAddToCartButtonLocation));
//	
////	  Actions action = new Actions(driver);
////	  action.moveToElement(testItemImg).perform();
////	  driver.click(testItemAddToCartButton);
////	  
//	
//	  testItemAddToCartButton.click();
//	  
//	  
//	  //To see the button we need to hover, let's wait until it becomes responsive again
//	  System.out.print(testItemAddToCartButton.getText());

  }


}
