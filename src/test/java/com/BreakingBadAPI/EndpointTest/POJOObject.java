package com.BreakingBadAPI.EndpointTest;

/**
 * This is a POJO class its main goal is to store information on an instance. Thus, all its methods serve the purpose
 * @author Jorge Canario
 *
 */
public class POJOObject {
	/** Is a variable used to split the data from a String. It's mainly an auxiliary variable. */
	private final String SEPARATOR = "::";
	/** Is an auxiliary variable. It serves as some sort of queue that will processed when committed. It stores all the names saved without format */
	private String csvNames = "";
	/** Is an auxiliary variable. It serves as some sort of queue that will processed when committed. It stores all the actors saved without format */
	private String csvActors = "";
	/** It is the bidimensional array of String that will hold the name and the actor formatted correctly*/
	private String[][] savedData;
	
	/**
	 * Saves the name inside the object but still data won't be accessible until committed
	 * @author Jorge Canario
	 * @param input the name value that want to be saved
	 */
	public void saveName(String input) {
		this.csvNames += input + SEPARATOR;
	}
	/**
	 * Saves the actor name inside the object but still data won't be accessible until committed
	 * @author Jorge Canario
	 * @param input the actorname value that want to be saved
	 */
	public void saveActor(String input) {
		this.csvActors += input + SEPARATOR;
	}
	
	/**
	 * makes the data entered to the object accessible so that anyone can now call getters methods
	 * @author Jorge Canario
	 * @return a bidimensional String with all the names and actors name saved inside the object
	 */
	public String[][] commitSave() {
		String[] names = csvNames.split(SEPARATOR);
		String[] actors = csvActors.split(SEPARATOR);
		int amountOfElement = names.length;
		this.savedData = new String[amountOfElement][2];
		
		for(int i=0; i < amountOfElement; i++) {
			this.savedData[i][0] = names[i];
			this.savedData[i][1] = actors[i];
		}
		
		return this.savedData;
		
	}

	/**
	 * Is the get method of the class
	 * @author Jorge Canario
	 * @return	a bidimensional String with all the names and actors name saved inside the object
	 */
	public String[][] getSavedData() {
		return this.savedData;
	}
	
	/**
	 * Shows in console all the information contained in this instance of the class
	 * @author Jorge Canario
	 */
	public void consoleShowSavedData() {
		for (int i=0; i<this.savedData.length; i++)
			System.out.println(this.savedData[i][0]+SEPARATOR+this.savedData[i][1]);
	}
	
}
