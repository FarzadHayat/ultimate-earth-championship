package main;

public class GameInitializer {

	public static final DisplayType TYPE = DisplayType.CLI;
	
	public static void main(String[] args) {
		GameManager.getInstance().play();
	}

}
