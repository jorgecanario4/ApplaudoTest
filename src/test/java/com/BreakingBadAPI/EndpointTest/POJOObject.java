package com.BreakingBadAPI.EndpointTest;

public class POJOObject {
	private final String SEPARATOR = "::";
	
	String csvNames = "";
	String csvActors = "";
	private String[][] savedData;

	public void saveName(String input) {
		this.csvNames += input + SEPARATOR;
	}

	public void saveActor(String input) {
		this.csvActors += input + SEPARATOR;
	}
	
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

	public String[][] getSavedData() {
		return this.savedData;
	}
	
	public void consoleShowSavedData() {
		for (int i=0; i<this.savedData.length; i++)
			System.out.println(this.savedData[i][0]+SEPARATOR+this.savedData[i][1]);
	}
	
}
