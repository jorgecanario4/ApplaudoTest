package com.WebPage.FeatureTest;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import java.util.List;


public class ShoppingCartTest extends FeatureTest{
	// Still pending some DRY principles improvement in this class
	private String testItemProductName;
	private String testItemPrice;
	
	
	private void navigateToCart() {
		By shoppingCartLocation = By.cssSelector("a[href][title*=\"View my shopping cart\"]");
		action.moveToElement(driver.findElement(shoppingCartLocation)).click().build().perform();

	}
	
	private List <WebElement> getCartItems(){
		By cartLocation = By.xpath("//*[@id=\"cart_summary\"]/tbody");
		jsExecutor.executeScript( "window.scrollTo(0, 500)");
		wait.until(ExpectedConditions.presenceOfElementLocated(cartLocation));
		WebElement cart = driver.findElement(cartLocation);
		  
		return cart.findElements(By.tagName("tr"));
	}
	
  @Test(enabled=false)
  public void testAddItemToShoppingCart() {  
	  
	  By cartPopUpWindowLocation = By.cssSelector("div#layer_cart[style*=\"display: block\"]");  //It will only be visible when it pops up

	  
	  try {
		  WebElement testItem = driver.findElement(By.cssSelector("li.ajax_block_product.col-xs-12.col-sm-4.col-md-3.first-in-line.first-item-of-tablet-line.first-item-of-mobile-line"));
		  WebElement addToCartButton = testItem.findElement(By.cssSelector("a.button.ajax_add_to_cart_button.btn.btn-default[title*=Add][href]"));
		   
		  action.moveToElement(testItem).perform();
		  
		  testItemProductName = testItem.findElement(By.cssSelector("a.product-name")).getText();
		  testItemPrice = testItem.findElement(By.cssSelector("span.price.product-price")).getText();
			  
		  action.moveToElement(addToCartButton).click().build().perform();
		  
		  //"Wait" should come first as element is only shown after the click. Thus, before it loads element doesn't exist
		  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartPopUpWindowLocation));  
		  WebElement cartPopUpWindow = driver.findElement(cartPopUpWindowLocation);
		  
		  assertEquals(cartPopUpWindow.findElement(By.id("layer_cart_product_title")).getText(), testItemProductName, "Product's name in the pop-up notification doesn't match product's name advertised");
		  assertEquals(cartPopUpWindow.findElement(By.id("layer_cart_product_quantity")).getText(), "1", "Product advertised should have been added to cart once, but quantity doesn't show 1 in pop-up notification");
		  assertEquals(cartPopUpWindow.findElement(By.id("layer_cart_product_price")).getText(), testItemPrice, "Product's price in the pop-up notification doesn't match product's price advertised");
	  
		  action.moveToElement(cartPopUpWindow.findElement(By.cssSelector("span.continue.btn.btn-default.button.exclusive-medium"))).click().build().perform();
		  
		  navigateToCart();
		  List <WebElement> cartItems = getCartItems();
		  
		  Boolean isTestItemInCart = false;
		  for(WebElement item : cartItems) {
			  if(item.findElement(By.cssSelector("td.cart_description")).getText().contains(testItemProductName) 
					  && item.findElement(By.cssSelector("td.cart_unit")).getText().contains(testItemPrice)) {
				  isTestItemInCart=true;
			  }
		  }
		  assertTrue(isTestItemInCart, "Cart doesn't contain the item");
	  }
	  catch (TimeoutException e) {
			System.out.println("Element didn't load correctly");
	  }
	  catch(NoSuchElementException e) {
			System.out.println("Element doesn't exist");
	  }

  }
  
  @Test(enabled=false)
  public void testRemoveItemFromShoppingCart() {  
	  //testAddItemToShoppingCart() need to be run prior the execution of this case
	  Boolean isTestItemInCart = true;
	  List <WebElement> cartItems = getCartItems();
	  
	  for(WebElement item : cartItems) {
		  if(item.findElement(By.cssSelector("td.cart_description")).getText().contains(testItemProductName) 
				  && item.findElement(By.cssSelector("td.cart_unit")).getText().contains(testItemPrice)) {
			  action.moveToElement(item.findElement(By.cssSelector("a.cart_quantity_delete[title=\"Delete\""))).click().build().perform();
		  }
	  }
	  
	  cartItems = getCartItems();
	  
	  for(WebElement item : cartItems) {
		  if(item.findElement(By.cssSelector("td.cart_description")).getText().contains(testItemProductName) 
				  && item.findElement(By.cssSelector("td.cart_unit")).getText().contains(testItemPrice)) {
			  isTestItemInCart =false;
		  }
	  }
	  
	  assertFalse(isTestItemInCart, "Item wasn't deleted when clicked the delete button");
  }

}
