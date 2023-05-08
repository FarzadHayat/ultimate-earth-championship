package story;

import java.util.ArrayList;

import model.Configuration;

public class OpeningCutscene extends Cutscene {

	public OpeningCutscene() {
		super(generateContent());
	}
	
	private static ArrayList<CutsceneSlide> generateContent()
	{
		ArrayList<CutsceneSlide> c = new ArrayList<CutsceneSlide>();
		
		c.add(new CutsceneSlide(
				"When the Thoran Intergalactic Federation first arrived on Earth, the world stood still.", 
				Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "story1.jpg"));
		
		c.add(new CutsceneSlide(
				"Despite our greatest fears, the extraterrestrial visitors came in peace, inviting us "
				+ "to join their federation.", 
				Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "story2.jpg"));
		
		c.add(new CutsceneSlide(
				"To represent us in the grand galactic senate, four of Earth's greatest champions are needed.", 
				Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "story3.jpg"));
		
		c.add(new CutsceneSlide(
				"To find them, the aliens began resurrecting great Humans of old and devising a grand tournament "
				+ "so that the most \"badass, jacked, cool-as earthbros\" could be found.", 
				Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "story4.jpg"));
		
		c.add(new CutsceneSlide(
				"This alien sport is one of combat, a test of will, strength and strategy.", 
				Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "story5.jpg"));
		
		c.add(new CutsceneSlide(
				"While the sport's true Thoran name is unpronounceable by the Human tongue, those on earth "
				+ "would come to know it as the...", 
				Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "story6.jpg"));
		
		c.add(new CutsceneSlide(
				"Ultimate Earth Championship.", 
				Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "story7.jpg"));
		
		return c;
	}
}
