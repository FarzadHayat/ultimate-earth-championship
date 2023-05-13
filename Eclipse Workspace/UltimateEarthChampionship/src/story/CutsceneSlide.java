package story;

/**
 * Cutscene Class Made to be played in a certain order, showing an image and
 * text to the player. This class just contains the information in each 'slide'
 * of the cutscene, which is strung together to form a cohesive cutscene.
 *
 * @author Oliver Coates
 *
 */
public class CutsceneSlide {

	/*
	 * The text contained in each slide
	 */
	private String text;

	/*
	 * The image path for the slide
	 */
	private String imagePath;

	/**
	 * Gets the text
	 *
	 * @return the text of this cutscene slide
	 */
	public String getText() {
		return text;
	}

	/**
	 * Gets the image path
	 *
	 * @return the image path of this cutscene slide
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Constructor class
	 *
	 * @param text     The text to be shown
	 * @param imageURL The path to be shown
	 */
	public CutsceneSlide(String text, String imagePath) {
		this.text = text;
		this.imagePath = imagePath;
	}
}
