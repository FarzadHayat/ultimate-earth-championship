package tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import model.Configuration;
import model.LevelUpStat;

import java.util.ArrayList;
import java.util.Random;

import javax.print.attribute.standard.JobName;

import org.junit.jupiter.api.Test;

import champions.CharlesDarwin;
import champions.JohnDoe;
import champions.SunTzu;
import exception.FullTeamException;
import exception.IllegalPurchaseException;
import exception.InsufficientFundsException;
import manager.GameManager;
import model.Champion;
import model.Team;
import weapons.Fists;
import weapons.Katana;

/**
 * Tests the champion class
 */
class ChampionTest {

	/**
	 * Tests that the name can be retrieved
	 */
	@Test
	void testGetName() {

		Champion testChamp = new CharlesDarwin();

		assertEquals(testChamp.getName(), "Charles Darwin");
	}

	/**
	 * Tests that the name of a champion can be set
	 */
	@Test
	void testSetName() {
		Champion testChamp = new CharlesDarwin();

		testChamp.setName("Test");

		assertEquals(testChamp.getName(), "Test");
	}

	/**
	 * Tests that stamina can be retrieved
	 */
	@Test
	void testGetStamina() {

		Champion testChamp = new JohnDoe();

		assertEquals(testChamp.getStamina(), 80);
	}

	/**
	 * Tests that stamina can be set
	 */
	@Test
	void testSetStamina() {
		Champion testChamp = new JohnDoe();

		testChamp.setStamina(125);

		assertEquals(testChamp.getStamina(), 125);
	}

	/**
	 * Tests that stamina can be added to
	 */
	@Test
	void testChangeStamina() {
		Champion testChamp = new JohnDoe();

		testChamp.setStamina(50);
		testChamp.addStamina(10000);
		
		// Test that adding heaps of stamina maxes out at max stamina
		assertEquals(testChamp.getStamina(), testChamp.getMaxStamina());

		Champion testChamp2 = new JohnDoe();

		testChamp2.setStamina(1);
		testChamp2.addStamina(5);

		assertEquals(testChamp2.getStamina(), 6);
	}

	/**
	 * Tests that Max Stamina can be retrieved
	 */
	@Test
	void testGetMaxStamina() {

		Champion testChamp = new JohnDoe();

		assertEquals(testChamp.getMaxStamina(), 80);
	}

	/**
	 * Tests that max stamina can be changed
	 */
	@Test
	void testChangeMaxStamina() {

		Champion testChamp = new JohnDoe();

		float baseStamina = testChamp.getStamina();
		
		assertEquals(testChamp.getMaxStamina(), baseStamina);

		testChamp.addMaxStamina(13);

		assertEquals(testChamp.getMaxStamina(), baseStamina + 13);
	}

	/**
	 * Test that the offense stat can be retrieved
	 */
	@Test
	void testGetOffense() {

		Champion testChamp = new JohnDoe();

		assertEquals(testChamp.getOffense(), 1);
	}

	/**
	 * Tests that the offense stat can be modified
	 */
	@Test
	void testChangeOffense() {

		Champion testChamp = new JohnDoe();

		int oldOffense = testChamp.getOffense();
		
		testChamp.changeOffense(1);

		assertEquals(testChamp.getOffense(), oldOffense + 1);
	}

	/**
	 * Test that defense can be retirieved
	 */
	@Test
	void testGetDefense() {

		Champion testChamp = new JohnDoe();

		assertEquals(testChamp.getDefense(), 1);
	}

	/**
	 * Tests that defense can be changed
	 */
	@Test
	void testChangeDefense() {
		Champion testChamp = new JohnDoe();

		int oldDefense = testChamp.getDefense();
		
		testChamp.changeDefense(2);

		assertEquals(testChamp.getDefense(), oldDefense + 2);
	}

	/**
	 * Tests that level can be retrieved
	 */
	@Test
	void testGetLevel() {
		Champion testChamp = new JohnDoe();

		assertEquals(testChamp.getLevel(), 1);
	}

	/**
	 * Tests that price can be retrieved
	 */
	@Test
	void testGetPrice() {

		Champion testChamp = new JohnDoe();

		assertEquals(testChamp.getPrice(), 50f);
	}

	/**
	 * Tests that price can be set
	 */
	@Test
	void testSetPrice() {

		Champion testChamp = new JohnDoe();

		testChamp.setPrice(223f);

		assertEquals(testChamp.getPrice(), 223f);
	}

	/**
	 * Tests that price change weekly can be get
	 */
	@Test
	void testGetPriceChangeWeekly() {

		Champion testChamp = new JohnDoe();

		assertEquals(testChamp.getPriceChangeWeekly(), 1.1f);
	}

	/**
	 * Tests that price change weekly can be set
	 */
	@Test
	void testSetPriceChangeWeekly() {

		Champion testChamp = new JohnDoe();

		testChamp.setPriceChangeWeekly(1.45f);

		assertEquals(testChamp.getPriceChangeWeekly(), 1.45f);
	}

	/**
	 * Tests that the weekly price change works
	 */
	@Test
	void testApplyWeeklyPriceChange() {

		Champion testChamp = new JohnDoe();

		float currentPrice = testChamp.getPrice();
		float intendedPrice = currentPrice * testChamp.getPriceChangeWeekly();
		
		testChamp.setPriceChangeWeekly(1.1f);

		assertEquals(testChamp.getPrice(), currentPrice);

		testChamp.applyWeeklyPriceChange();

		assertEquals(testChamp.getPrice(), intendedPrice);
	}

	/**
	 * Tests getting weapons, and getting their damage
	 */
	@Test
	void testGetWeapon() {
		Fists fists = new Fists();
		JohnDoe johnDoe = new JohnDoe();
		
		assertEquals(johnDoe.getWeapon().getClass(), fists.getClass());
		
		// Its difficult to know what damage might be, so as long as its a non-negative number that should be good
		assertEquals((johnDoe.getDamage() > 0), true);
	}

	/**
	 * Tests removing a weapon
	 */
	@Test
	void testRemoveWeapon() {
		Katana katana = new Katana();
		JohnDoe johnDoe = new JohnDoe();
		Fists fists = new Fists();
		
		johnDoe.setWeapon(katana);
		
		assertEquals(johnDoe.getWeapon().getClass(), katana.getClass());
		
		// Removing a weapon should return to fists
		
		johnDoe.removeWeapon();
		
		assertEquals(johnDoe.getWeapon().getClass(), fists.getClass());
	}

	/**
	 * Tests adding a weapon
	 */
	@Test
	void testAddWeapon() {
		Katana katana = new Katana();
		JohnDoe johnDoe = new JohnDoe();
		
		johnDoe.setWeapon(katana);
		
		assertEquals(johnDoe.getWeapon().getClass(), katana.getClass());
	}

	/**
	 * Tests creating a new champion
	 */
	@Test
	void testChampion() {
		Champion testChampion = new JohnDoe();

		assertEquals(testChampion.getName(), "John Doe");
		assertEquals(testChampion.getLevel(), 1);
		assertEquals(testChampion.getOffense(), 1);
		assertEquals(testChampion.getDefense(), 1);
	}

	/**
	 * Tests levelup
	 */
	@Test
	void testLevelUp() {
		GameManager.getInstance().initialize();
		ArrayList<Champion> champs = new ArrayList<Champion>();
		champs.add(new SunTzu());
		GameManager.getInstance().setPlayerTeam(new Team(true, "test", champs));
		
		Champion testChampion = new JohnDoe();
		assertEquals(testChampion.getLevel(), 1);
		
		

		testChampion.giveXP(150f);

		assertEquals(testChampion.getLevel(), 2);
	}

	/**
	 * Test the damage taken this week
	 */
	@Test
	void testDamageTakenThisWeek()
	{
		JohnDoe johnDoe = new JohnDoe();
		
		assertEquals(johnDoe.getDamageTakenThisWeek(), 0);
		
		// Take damage
		johnDoe.addStamina(-21);
		
		assertEquals(johnDoe.getDamageTakenThisWeek(), 21);
		
		johnDoe.addStamina(-5);
		
		assertEquals(johnDoe.getDamageTakenThisWeek(), 26);
		
		// Rest the champion
		
		johnDoe.rest();
		
		assertEquals(johnDoe.getDamageTakenThisWeek(), 0);
	}
	
	/**
	 * Test the regen feature on champions
	 */
	@Test
	void testRegen()
	{
		JohnDoe johnDoe = new JohnDoe();
		
		float regenAmount = johnDoe.getRegen();
		
		johnDoe.changeRegen(7);
	
		assertEquals(johnDoe.getRegen(), regenAmount + 7);
	}
	
	/**
	 * Test the lane, position and flag carrier status of the champion
	 */
	@Test
	void testMatchInfo()
	{
		JohnDoe johnDoe = new JohnDoe();
		
		int testLane = 2;
		int testPosition = 3;
		
		johnDoe.setLane(testLane);
		johnDoe.setPosition(testPosition);
		
		assertEquals(johnDoe.getLane(), 2);
		assertEquals(johnDoe.getPosition(), 3);
		
		johnDoe.setFlagCarrier(true);
		
		assertEquals(johnDoe.isFlagCarrier(), true);
		
		johnDoe.setFlagCarrier(false);
		
		assertEquals(johnDoe.isFlagCarrier(), false);
	}
	
	/**
	 * Test that application of level up stat properly increases the stat
	 */
	@Test
	void testLevelUpEffects()
	{
		JohnDoe johnDoe = new JohnDoe();
		
		Configuration config = Configuration.getInstance();
		
		float staminaBefore = johnDoe.getMaxStamina();
		
		float expectedStamina = staminaBefore + config.LEVEL_UP_STAMINA_INCREASE;

		float regenBefore = johnDoe.getRegen();
		float expectedRegen = regenBefore + config.LEVEL_UP_REGEN_INCREASE;
		
		int offenseBefore = johnDoe.getOffense();
		int expectedOffense = offenseBefore + config.LEVEL_UP_OFFENSE_INCREASE;
		
		int defenseBefore = johnDoe.getDefense();
		int expectedDefense = defenseBefore + config.LEVEL_UP_DEFENSE_INCREASE;
		
		johnDoe.applyLevelUp(LevelUpStat.OFFENSE);
		assertEquals(johnDoe.getOffense(), expectedOffense);
		
		johnDoe.applyLevelUp(LevelUpStat.DEFENSE);
		assertEquals(johnDoe.getDefense(), expectedDefense);
		
		johnDoe.applyLevelUp(LevelUpStat.STAMINA);
		assertEquals(johnDoe.getMaxStamina(), expectedStamina);
		
		johnDoe.applyLevelUp(LevelUpStat.REGEN);
		assertEquals(johnDoe.getRegen(), expectedRegen);
	}
	
	/**
	 * Tests getting the image paths
	 */
	@Test
	void testImage()
	{
		JohnDoe johnDoe = new JohnDoe();
		
		assertNotEquals(johnDoe.getImage(), null);
		
		johnDoe.setImage(null);
		
		assertEquals(johnDoe.getImage(), null);
	}
	
	/**
	 * Tests the toString method
	 */
	@Test
	void testToString()
	{
		JohnDoe johnDoe = new JohnDoe();
		// Somewhat difficult to test without hard coding values, so its just
		// going to test for a non-null string
	
		assertNotEquals(johnDoe.toString(), null);
	}
	
	/**
	 * Tests the buying and selling of a champion from teams
	 */
	@Test
	void testBuySell()
	{
		Team testTeam = new Team(false, "Test team", new ArrayList<Champion>());
		JohnDoe johnDoe = new JohnDoe();
		
		if (testTeam.getMoney() > johnDoe.getPrice())
		{
			// Set money to 0
			testTeam.addMoney(-testTeam.getMoney());
		}
		
		// Insufficient funds exception testing:
		
		InsufficientFundsException insufficientFundsException = null;
		
		try {
			johnDoe.buy(testTeam);
			fail("Funds exception not thrown!");
		} catch (Exception e) {
			insufficientFundsException = (InsufficientFundsException) e;
		}
		
		assertNotEquals(insufficientFundsException, null);
		
		// Fullteam exception:
		
		ArrayList<Champion> nine_champions = new ArrayList<Champion>();
		
		// Nine times:
		for (int i = 0; i < 9; i++) {
			nine_champions.add(new JohnDoe());
		}
		
		Team testFullTeam = new Team(false, "full team", nine_champions);
		
		FullTeamException fullTeamException = null;
		
		try {
			johnDoe.buy(testFullTeam);
			fail("Full team exception not thrown!");
		} catch (Exception e) {
			fullTeamException = (FullTeamException) e;
		}
		
		assertNotEquals(fullTeamException, null);
		
		// Working as intended:
		testTeam.addMoney(johnDoe.getPrice());
		
		try {
			johnDoe.buy(testTeam);
		} catch (Exception e) {
			// Exceptions should not occur
			fail(e.getStackTrace().toString());
		}
		
		assertEquals(testTeam.getChampions().size(), 1);
		assertEquals(testTeam.getMoney(), 0); // Check money has been subtraced properly
		
		// Illegal purchase exception test:
		// This happens if the *same* team purchases more than 1 champion
		
		JohnDoe johnDoe2 = new JohnDoe();
		
		IllegalPurchaseException illegalPurchaseException = null;
		
		testTeam.addMoney(johnDoe.getPrice());
		float moneyBefore = testTeam.getMoney();
		
		try {
			johnDoe.buy(testTeam);
			fail("Illegal buy exception not thrown!");
		} catch (Exception e) {
			illegalPurchaseException = (IllegalPurchaseException) e;
		}
		
		assertNotEquals(illegalPurchaseException, null);
		assertEquals(testTeam.getMoney(), moneyBefore);
		
		// Test selling:
		moneyBefore = testTeam.getMoney();
		
		// Sell champion:
		johnDoe.sell(testTeam);
		
		// Test champion has been removed
		assertEquals(testTeam.getChampions().size(), 0);
		
		// Test money has been added
		assertEquals(testTeam.getMoney(), moneyBefore + johnDoe.getPrice());
	}
	
	/**
	 * Test that champions can be cloned and remain with the same stats
	 */
	@Test
	void testClone()
	{
		Random random = new Random();
		
		float randomStamina = random.nextFloat(100);
		float randomRegen = random.nextFloat(100);
		int randomOffense = random.nextInt(100);
		int randomDefense = random.nextInt(100);
		
		JohnDoe johnDoeOriginal = new JohnDoe();
		
		johnDoeOriginal.addMaxStamina(randomStamina);
		johnDoeOriginal.changeRegen(randomRegen);
		johnDoeOriginal.changeOffense(randomOffense);
		johnDoeOriginal.changeDefense(randomDefense);
		
		Champion johnDoeClone = johnDoeOriginal.clone();
		
		assertEquals(johnDoeOriginal.getName(), johnDoeClone.getName());
		assertEquals(johnDoeOriginal.getStamina(), johnDoeClone.getStamina());
		assertEquals(johnDoeOriginal.getRegen(), johnDoeClone.getRegen());
		assertEquals(johnDoeOriginal.getOffense(), johnDoeClone.getOffense());
		assertEquals(johnDoeOriginal.getDefense(), johnDoeClone.getDefense());
	}
}
