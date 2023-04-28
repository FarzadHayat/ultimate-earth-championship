package story;

import java.util.ArrayList;

public abstract class Cutscene {

	/**
	 * List of all Cutscene Slides which make up the content for this cutscene
	 */
	private ArrayList<CutsceneSlide> content;
	
	/**
	 * The num slide that this cutscene is currently on, reset to 0 once all
	 * slides have been used. Utilized in the nextSlide() function.
	 */
	private int iteration;
	
	/**
	 * Returns the content in list form
	 * @return List of all cutscene slides
	 */
	public ArrayList<CutsceneSlide> getContent()
	{
		return content;
	}
	
	/**
	 * Iterates forward to the next slide.
	 */
	public void nextSlide()
	{
		iteration++;
	}
	
	/**
	 * Returns the next slide in this cutscene or null if current slide is the last slide.
	 * @return The next slide in this cutscene or null
	 */
	public CutsceneSlide checkNextSlide()
	{
		if (iteration+1 == content.size())
		{
			return null;
		}
		
		return content.get(iteration+1);
	}
	
	public CutsceneSlide getCurrentSlide() {
		return content.get(iteration);
	}
	
	/**
	 * Constructor class
	 * @param c ArrayList of CutsceneSlides to make up this cutscene.
	 */
	Cutscene(ArrayList<CutsceneSlide> c)
	{
		content = c;
		iteration = 0;
	}
	
}
