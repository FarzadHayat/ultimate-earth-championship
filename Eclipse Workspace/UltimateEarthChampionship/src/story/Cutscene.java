package story;

import java.util.ArrayList;

public abstract class Cutscene {

	/**
	 * List of all Cutscene Slides which make up the content for this cutscene
	 */
	private ArrayList<CutsceneSlide> content;

	/**
	 * The num slide that this cutscene is currently on, reset to 0 once all slides
	 * have been used. Utilized in the nextSlide() function.
	 */
	private int iteration;

	/**
	 * Returns the content in list form
	 *
	 * @return List of all cutscene slides
	 */
	public ArrayList<CutsceneSlide> getContent() {
		return content;
	}

	/**
	 * Returns the next slide in this cutscene, returns null once all slides have
	 * been shown.
	 *
	 * @return The next slide in this cutscene.
	 */
	public CutsceneSlide nextSlide() {
		if (iteration == content.size()) {
			iteration = 0;
			return null;
		}

		iteration++;
		return content.get(iteration - 1);
	}

	/**
	 * Returns the next slide in this cutscene without iterating through it, useful
	 * for checking if the next slide is going to null for instance. Will return
	 * null once reached the end of slides
	 *
	 * @return The next slide in this cutscene (no iteration)
	 */
	public CutsceneSlide checkNextSlide() {
		if (iteration == content.size()) {
			return null;
		}

		return content.get(iteration);
	}

	/**
	 * Constructor class
	 *
	 * @param c ArrayList of CutsceneSlides to make up this cutscene.
	 */
	Cutscene(ArrayList<CutsceneSlide> c) {
		content = c;
		iteration = 0;
	}

}
