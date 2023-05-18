package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import story.CutsceneSlide;
import story.OpeningCutscene;

/**
 * Tests the Cutscene, Opening cutscene and Cutsceneslide classes
 */
class CutsceneTest {

	/**
	 * Tests to ensure that the opening cutscene returns 7 non null slides
	 * and then a final null slide.
	 */
	@Test
	void testOpeningCutscene() {
		OpeningCutscene cutscene = new OpeningCutscene();
		
		ArrayList<CutsceneSlide> slides = new ArrayList<CutsceneSlide>();
		
		// Perform a checkNextSlide, this shouldn't iterate forward through the cutscene
		assertNotEquals(cutscene.checkNextSlide(), null);
		
		// Check that getContent returns a list
		assertEquals(cutscene.getContent().getClass(), new ArrayList<CutsceneSlide>().getClass());
		
		for (int i = 0; i < 7; i++) {
			CutsceneSlide slide = cutscene.nextSlide();
			
			assertNotEquals(slide, null); // Check that all 7 slides are being returned
			assertNotEquals(slide.getText(), null); // Check that some text is being returned
			assertNotEquals(slide.getImagePath(), null); // Check that an image path is returned
			
			
			slides.add(slide);
		}
		
		// Perform a checkNextSlide, this shouldn't iterate forward through the cutscene
		assertEquals(cutscene.checkNextSlide(), null);
		
		// Check the final slide is null
		assertEquals(cutscene.nextSlide(), null);
	}

}
