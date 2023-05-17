package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import events.*;
import manager.GameManager;
import model.Champion;
import model.Team;
import champions.*;

class RandomEventTest {

	public Team testTeam;
	
	/**
	 * Sets up a team for us to test
	 */
	@BeforeEach
	void setup()
	{
		ArrayList<Champion> testChampions = new ArrayList<Champion>();
		
		testChampions.add(new JoeRogan());
		testChampions.add(new JohnFKennedy());
		testChampions.add(new SunTzu());
		testChampions.add(new JosefStalin());
		
		testTeam = new Team(false, "test team", testChampions);
	}
	
	/**
	 * Tests that the tv show event is working
	 */
	@Test
	void testTVShowEvent() {
		TVShowEvent tvShowEvent = new TVShowEvent();
		
		float moneyBefore = testTeam.getMoney();
		
		tvShowEvent.runEvent(testTeam);
		
		// Check money has increased by 25
		assertEquals(testTeam.getMoney(), moneyBefore + 25);
		
	}

	/**
	 * Tests that the thief event is working
	 */
	@Test
	void testThiefEvent()
	{
		ThiefEvent thiefEvent = new ThiefEvent();
		
		float moneyBefore = testTeam.getMoney();
		
		thiefEvent.runEvent(testTeam);
		
		// Check money has increased by 25
		assertEquals(testTeam.getMoney(), moneyBefore - 25);
		
	}

	/**
	 * Tests that the rampaging animal event is working
	 */
	@Test
	void testRampagingAnimalEvent()
	{
		RampagingAnimalEvent animalEvent = new RampagingAnimalEvent();
		
		// Find all the stamina before
		float teamTotalStaminaBefore = 0;
		
		for(Champion champ : testTeam.getChampions())
		{
			teamTotalStaminaBefore += champ.getMaxStamina();
		}
		
		// Run the event
		animalEvent.runEvent(testTeam);
		
		// Find the stamina after
		float teamTotalStaminaAfter = 0;
		
		for(Champion champ : testTeam.getChampions())
		{
			teamTotalStaminaAfter += champ.getMaxStamina();
		}
		
		// Check that 10 has been taken from it
		assertEquals(teamTotalStaminaBefore - 10f, teamTotalStaminaAfter);
	}
	
	/**
	 * Test that the radiation event is working
	 */
	@Test
	void testRadiationPoisioningEvent()
	{
		RadiationPoisoningEvent radiationEvent = new RadiationPoisoningEvent();
		
		ArrayList<Champion> champions = new ArrayList<Champion>();
		JohnDoe johnDoe = new JohnDoe();
		champions.add(johnDoe);
		
		float staminaBefore = johnDoe.getMaxStamina();
		float offenseBefore = johnDoe.getOffense();
		float defenseBefore = johnDoe.getDefense();
		
		Team oneManTeam = new Team(false, "one man team", champions);
		
		// Since we give a team consisting only of John Doe, only he should be selected for the event
		radiationEvent.runEvent(oneManTeam);
		
		assertEquals(staminaBefore - 25f, johnDoe.getMaxStamina());
		assertEquals(offenseBefore + 3f, johnDoe.getOffense());
		assertEquals(defenseBefore + 2f, johnDoe.getDefense());
	}

	/**
	 * Tests the free weapon event to ensure that it is working correctly
	 */
	@Test
	void testFreeWeaponEvent()
	{
		FreeWeaponEvent freeWeaponEvent = new FreeWeaponEvent();
		
		int weaponCountBefore = testTeam.getWeapons().size();
		
		freeWeaponEvent.runEvent(testTeam);
	
		int weaponCountAfter = testTeam.getWeapons().size();
		
		assertEquals(weaponCountBefore + 1,weaponCountAfter);
	}
	
	/**
	 * Tests the donation event to see that it is working correctly
	 */
	@Test
	void testDonationEvent()
	{
		DonationEvent donationEvent = new DonationEvent();
		
		float moneyBefore = testTeam.getMoney();
		
		donationEvent.runEvent(testTeam);
		
		float moneyAfter = testTeam.getMoney();
		
		assertEquals(moneyBefore + 15, moneyAfter);
	}
	
	/**
	 * Tests that the champion quits event is working
	 */
	@Test
	void testChampionQuitsEvent()
	{
		Champion championToQuit = testTeam.getChampions().get(0);
		
		ChampionQuits championQuits = new ChampionQuits(championToQuit);
		
		int championSizeBefore = testTeam.getChampions().size();
		float moneyBefore = testTeam.getMoney();
		
		championQuits.runEvent(testTeam);
		
		int championSizeAfter = testTeam.getChampions().size();
		float moneyAfter = testTeam.getMoney();
		
		assertEquals(championSizeBefore - 1, championSizeAfter);
		assertEquals(moneyBefore + 50, moneyAfter);
	}
	
	/**
	 * Tests that the champion joins event is working
	 */
	@Test
	void testChampionJoinsEvent()
	{
		ChampionJoins championJoins = new ChampionJoins();
		
		int championSizeBefore = testTeam.getChampions().size();
		
		GameManager.getInstance().initialize();
		
		championJoins.runEvent(testTeam);
		
		int championSizeAfter = testTeam.getChampions().size();
	
		assertEquals(championSizeBefore + 1, championSizeAfter);
	}
}
