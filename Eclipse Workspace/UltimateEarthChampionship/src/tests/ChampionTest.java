package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import champion.Champion;
import champion.champions.CharlesDarwin;
import champion.champions.JohnDoe;

class ChampionTest {

	@Test
	void testGetName() {
		
		Champion testChamp = new CharlesDarwin();
		
		assertEquals(testChamp.getName(), "Charles Darwin");
	}

	@Test
	void testSetName() {
		Champion testChamp = new CharlesDarwin();
		
		testChamp.setName("Test");
		
		assertEquals(testChamp.getName(), "Test");
	}

	@Test
	void testGetHealth() {
		
		Champion testChamp = new JohnDoe();
		
		assertEquals(testChamp.getHealth(), 100);
	}

	@Test
	void testSetHealth() {
		Champion testChamp = new JohnDoe();
		
		testChamp.setHealth(125);
		
		assertEquals(testChamp.getHealth(), 125);
	}

	@Test
	void testChangeHealth() {
		Champion testChamp = new JohnDoe();
		
		testChamp.setHealth(100);
		testChamp.addHealth(76);
		
		assertEquals(testChamp.getHealth(), 100);
		
		Champion testChamp2 = new JohnDoe();
		
		testChamp2.setHealth(1);
		testChamp2.addHealth(5);
		
		assertEquals(testChamp2.getHealth(), 6);
	}

	@Test
	void testGetMaxHealth() {
		
		Champion testChamp = new JohnDoe();
		
		assertEquals(testChamp.getMaxHealth(), 100);
	}

	@Test
	void testChangeMaxHealth() {

		Champion testChamp = new JohnDoe();
		
		assertEquals(testChamp.getMaxHealth(), 100);
		
		testChamp.addMaxHealth(13);
		
		assertEquals(testChamp.getMaxHealth(), 113);
	}

	@Test
	void testGetStamina() {

		Champion testChamp = new JohnDoe();
		
		assertEquals(testChamp.getStamina(), 30);
	}
	
	
	@Test
	void testChangeStamina() {

		Champion testChamp = new JohnDoe();
		
		assertEquals(testChamp.getStamina(), 30);
		
		testChamp.addStamina(-8);
		
		assertEquals(testChamp.getStamina(), 22);
		
		testChamp.addStamina(12);
		
		assertEquals(testChamp.getStamina(), 30); // Check that adding more stamina than max keeps it below the maximum value
	}

	@Test
	void testGetMaxStamina() {

		Champion testChamp = new JohnDoe();
		
		assertEquals(testChamp.getMaxStamina(), 30);
	}

	@Test
	void testChangeMaxStamina() {
		Champion testChamp = new JohnDoe();
		
		testChamp.addMaxStamina(-2);
		
		assertEquals(testChamp.getMaxStamina(), 28);
	}

	@Test
	void testGetOffense() {

		Champion testChamp = new JohnDoe();
		
		assertEquals(testChamp.getOffense(), 1);
	}

	@Test
	void testChangeOffense() {

		Champion testChamp = new JohnDoe();
		
		testChamp.changeOffense(1);
		
		assertEquals(testChamp.getOffense(), 2);
	}

	@Test
	void testGetDefense() {

		Champion testChamp = new JohnDoe();
		
		assertEquals(testChamp.getDefense(), 1);
	}

	@Test
	void testChangeDefense() {
		Champion testChamp = new JohnDoe();
		
		testChamp.changeDefense(2);
		
		assertEquals(testChamp.getDefense(), 3);
	}

	@Test
	void testGetLevel() {
		Champion testChamp = new JohnDoe();
		
		assertEquals(testChamp.getLevel(), 1);
	}

	@Test
	void testGetPrice() {

		Champion testChamp = new JohnDoe();
		
		assertEquals(testChamp.getPrice(), 50f);
	}

	@Test
	void testChangePrice() {

		Champion testChamp = new JohnDoe();
		
		testChamp.changePrice(223f);
		
		assertEquals(testChamp.getPrice(), 223f);
	}

	@Test
	void testGetPriceChangeWeekly() {

		Champion testChamp = new JohnDoe();
		
		assertEquals(testChamp.getPriceChangeWeekly(), 1.1f);
	}

	@Test
	void testSetPriceChangeWeekly() {

		Champion testChamp = new JohnDoe();
		
		testChamp.setPriceChangeWeekly(1.45f);
		
		assertEquals(testChamp.getPriceChangeWeekly(), 1.45f);
	}

	@Test
	void testApplyWeeklyPriceChange() {
		
		Champion testChamp = new JohnDoe();
		
		testChamp.setPriceChangeWeekly(1.1f);
		
		assertEquals(testChamp.getPrice(), 50f);
		
		testChamp.applyWeeklyPriceChange();
		
		assertEquals(testChamp.getPrice(), 55f);
	}

	@Test
	void testGetWeapon() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveWeapon() {
		fail("Not yet implemented");
	}

	@Test
	void testAddWeapon() {
		fail("Not yet implemented");
	}

	@Test
	void testChampion() {
		Champion testChampion = new JohnDoe();
		
		assertEquals(testChampion.getName(), "John Doe");
		assertEquals(testChampion.getLevel(), 1);
		assertEquals(testChampion.getOffense(), 1);
		assertEquals(testChampion.getDefense(), 1);
	}
	
	@Test
	void testLevelUp()
	{
		Champion testChampion = new JohnDoe();
		assertEquals(testChampion.getLevel(), 1);
		
		testChampion.giveXP(150f);
		
		assertEquals(testChampion.getLevel(), 2);
	}

}
