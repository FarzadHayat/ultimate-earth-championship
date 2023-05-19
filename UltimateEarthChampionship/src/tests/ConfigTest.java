package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import model.Configuration;;

/**
 * Tests Configuration test to ensure that difficulty setting is working properly
 */
class ConfigTest {

	@Test
	void testGetDifficulty() {
		Configuration config = Configuration.getInstance();
		
		config.setDifficulty(1.23f);
		
		assertEquals(config.getDifficulty(), 1.23f);
	}

	@Test
	void testSetDifficulty() {
		// Check that setting the difficulty changes the AI_DAMAGE_MULTIPLIER & AI_AGRESSION_BOOST
		Configuration config = Configuration.getInstance();

		float old_damage_multiplier = config.AI_DAMAGE_MULTIPLIER;
		float old_agression_boost = config.AI_AGRESSION_BOOST;
		
		config.setDifficulty(0.83f);
		
		assertNotEquals(config.AI_DAMAGE_MULTIPLIER, old_damage_multiplier);
		assertNotEquals(config.AI_AGRESSION_BOOST, old_agression_boost);
	}

}
