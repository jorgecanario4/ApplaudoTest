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


public class CharacterTest {
	final String API_BASE_URI = "https://www.breakingbadapi.com/api";
	final String CHARACTERS_BY_NAME = "/characters?name=";
	final String ALL_CHARACTERS = "/characters?name=";
	
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
	  
	  String testJsonfields =json.getList("").get(0).toString();
//	  -------------------------------------------------------------------
	  Iterator<String> jsonExpectedField = new ArrayList<>(Arrays.asList("char_id", "name", "birthday", "occupation",
              "img", "status", "appearance", "nickname", "portrayed", 
              "better_call_saul_appearance" )).iterator();
	  
	  while(jsonExpectedField.hasNext()) {
		  String field = jsonExpectedField.next();
		  AssertJUnit.assertTrue("JSON doesn't contain expected key (field):" + field, testJsonfields.contains(field));  
	  }

//	  -------------------------------------------------------------------
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
		  			+ actor +"\n"+lineSeparator);
	  }

	  data.commitSave();   //Data saved to POJO as requested
	  
//	  ------------------------------------------------------------------- 
	 // data.consoleShowSavedData();  //To see savedData content printed in the console  
	  
  }
  

}
