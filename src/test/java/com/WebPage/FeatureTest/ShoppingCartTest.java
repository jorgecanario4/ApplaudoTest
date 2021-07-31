package com.WebPage.FeatureTest;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

/**
 * This class is suppose to test all the functionalities related to shopping cart in the web page.
 * 
 * @author Jorge Canario
 *
 */

public class ShoppingCartTest extends FeatureTest{
	// Still pending some DRY principles improvement in this class
	
	/** Since the ID in the HTML is something that not necessarily represent the ID of the element at the moment of purchase. The best way to identify the element is with its name and price. This variable contains the name of the element we've selected for testing purposes */
	private String testItemProductName;
	/** Since the ID in the HTML is something that not necessarily represent the ID of the element at the moment of purchase. The best way to identify the element is with its name and price. This variable contains the price of the element we've selected for testing purposes */
	private String testItemPrice;
	
	
	
	/**
	 * This method allow anyone navigate from index page to shopping cart page
	 * @author Jorge Canario
	 */
	private void navigateToCart() {
		By shoppingCartLocation = By.cssSelector("a[href][title*=\"View my shopping cart\"]");
		action.moveToElement(driver.findElement(shoppingCartLocation)).click().build().perform();

	}
	
	/**
	 * This method allow anyone to get all the items in the cart, if and only if it is already in the shopping cart page
	 * 
	 * @return all the items in the cart
	 * @author Jorge Canario
	 */
	
	private List <WebElement> getCartItems(){
		By cartLocation = By.xpath("//*[@id=\"cart_summary\"]/tbody");
		jsExecutor.executeScript( "window.scrollTo(0, 500)");
		wait.until(ExpectedConditions.presenceOfElementLocated(cartLocation));
		WebElement cart = driver.findElement(cartLocation);
		  
		return cart.findElements(By.tagName("tr"));
	}
	
	/**
	 * This method ensures that the notification of the successful addition to cart contains the right name, quantity and price of the element clicked (the test element)
	 * Also, this method confirms inside the shopping cart that the element clicked (the test element) made it to the shopping cart
	 * 
	 * @author Jorge Canario
	 */
  @Test
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
		  
		  AssertJUnit.assertEquals("Product's name in the pop-up notification doesn't match product's name advertised", testItemProductName, cartPopUpWindow.findElement(By.id("layer_cart_product_title")).getText() );
		  AssertJUnit.assertEquals("Product advertised should have been added to cart once, but quantity doesn't show 1 in pop-up notification", "1",cartPopUpWindow.findElement(By.id("layer_cart_product_quantity")).getText());
		  AssertJUnit.assertEquals("Product's price in the pop-up notification doesn't match product's price advertised", testItemPrice, cartPopUpWindow.findElement(By.id("layer_cart_product_price")).getText());
	  
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
		  AssertJUnit.assertTrue("Cart doesn't contain the item", isTestItemInCart);
	  }
	  catch (TimeoutException e) {
			Reporter.log("[ERROR:] Element didn't load correctly");
	  }
	  catch(NoSuchElementException e) {
		  	Reporter.log("[ERROR:] Element doesn't exist");
	  }

  }
  
  /**
   * This test must be ran after estAddItemToShoppingCart().
   * It takes the advantage of already being inside the shopping cart page, creates a list with all items delete the test item and then re-checks to see if it is still in the shopping cart
   * 
   * @author Jorge Canario
   */
  @Test
  public void testRemoveItemFromShoppingCart() {  
	  Boolean isTestItemInCart = true;
	  try {
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
	  }
	  catch (TimeoutException e) {
			Reporter.log("[ERROR:] Element didn't load correctly");
	  }
	  catch(NoSuchElementException e) {
		  	Reporter.log("[ERROR:] Element doesn't exist");
	  }
	 
	  
	  AssertJUnit.assertFalse("Item wasn't deleted when clicked the delete button", isTestItemInCart);
  }

}
