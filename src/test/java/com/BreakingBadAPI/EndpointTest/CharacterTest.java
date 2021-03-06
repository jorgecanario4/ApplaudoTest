package com.BreakingBadAPI.EndpointTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.http.HttpStatus;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * This class is in charge of the testing of the API that involves the characters endpoint.
 * 
 * @author Jorge Canario
 *
 */
public class CharacterTest {
	/** Specifies the main URL from where all other endpoint will operate */
	private final String API_BASE_URI = "https://www.breakingbadapi.com/api";
	/** Specifies the characters endpoint using a filter by name function */
	private final String CHARACTERS_BY_NAME = "/characters?name=";
	/** Specifies the characters endpoint using no filter at all */
	private final String ALL_CHARACTERS = "/characters";
	
	/**
	 * 
	 * This test request the information of character "Walter White", ensures the response from the request was successful and prints the birthday of character "Walter White" in the console
	 * @author Jorge Canario
	 */
  @Test
  public void testGetCharacterByName() {
	  baseURI = API_BASE_URI;
	  String testCharacterName = "Walter White";
	  
	  
	  String birthdayString =		
	  given().
	  	get(CHARACTERS_BY_NAME + testCharacterName).
	  then().
	  	assertThat().
	  	statusCode(HttpStatus.SC_OK).
	  	body("name", hasItem(testCharacterName)).  
	  extract().
	  	response().jsonPath().getString("birthday");
//	  -------------------------------------------------------------------
	  	
	  System.out.println(birthdayString);  //Printing birthday, as requested
	 
  }
  
  /**
   * This test request all characters and ensures that response is valid.
   * verifies all the expected fields are coming for the elements in the response. (it takes the first element as reference).
   * Also, saves the name of the character and the actor who depicted it inside a POJOObject and prints in console with a requested format.
   * @author Jorge Canario
   */
  @Test
  public void testGetAllCharacters() {
	  baseURI = API_BASE_URI;
	  
	  JsonPath json = 	  
	  given().
	  	get(ALL_CHARACTERS).
	  then().
	  	assertThat().
	  	statusCode(HttpStatus.SC_OK).
	  	body("", not (emptyArray())).
	  extract().
	  	response().jsonPath();
	  
//	  -------------------------------------------------------------------
	  //Validate existence of all expected fields in response JSON
	  String testJsonfields =json.getList("").get(0).toString();

	  Iterator<String> jsonExpectedField = new ArrayList<>(Arrays.asList("char_id", "name", "birthday", "occupation",
              "img", "status", "appearance", "nickname", "portrayed", 
              "better_call_saul_appearance" )).iterator();
	  
	  while(jsonExpectedField.hasNext()) {
		  String field = jsonExpectedField.next();
		  AssertJUnit.assertTrue("JSON doesn't contain expected key (field):" + field, testJsonfields.contains(field));  
	  }

//	  -------------------------------------------------------------------
	  //Saving into POJO and printing out as requested
	  POJOObject data = new POJOObject();
	  
	  Iterator<Object> characterNames = json.getList("name").iterator();
	  Iterator<Object> characterActors = json.getList("portrayed").iterator();
	  
	  String lineSeparator ="------------------------------------------------------";
	  while(characterNames.hasNext()) {
		  String name = characterNames.next().toString() ;
		  String actor = characterActors.next().toString();
		  
		  data.saveName(name);
		  data.saveActor(actor);
		  
		  System.out.println("Character name: " + name + "\nPortrayed: "
		  			+ actor +"\n"+lineSeparator);   //Printing out with requested format
	  }

	  data.commitSave();   //Data saved to POJO as requested
	  
//	  ------------------------------------------------------------------- 
	 // data.consoleShowSavedData();  //To see savedData content printed in the console  
	  
  }
  

}
