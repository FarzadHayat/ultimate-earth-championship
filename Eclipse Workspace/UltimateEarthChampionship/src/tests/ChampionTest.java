package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import champions.CharlesDarwin;
import champions.JohnDoe;
import model.Champion;

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
	void testGetStamina() {

		Champion testChamp = new JohnDoe();

		assertEquals(testChamp.getStamina(), 100);
	}

	@Test
	void testSetStamina() {
		Champion testChamp = new JohnDoe();

		testChamp.setStamina(125);

		assertEquals(testChamp.getStamina(), 125);
	}

	@Test
	void testChangeStamina() {
		Champion testChamp = new JohnDoe();

		testChamp.setStamina(100);
		testChamp.addStamina(76);

		assertEquals(testChamp.getStamina(), 100);

		Champion testChamp2 = new JohnDoe();

		testChamp2.setStamina(1);
		testChamp2.addStamina(5);

		assertEquals(testChamp2.getStamina(), 6);
	}

	@Test
	void testGetMaxStamina() {

		Champion testChamp = new JohnDoe();

		assertEquals(testChamp.getMaxStamina(), 100);
	}

	@Test
	void testChangeMaxStamina() {

		Champion testChamp = new JohnDoe();

		assertEquals(testChamp.getMaxStamina(), 100);

		testChamp.addMaxStamina(13);

		assertEquals(testChamp.getMaxStamina(), 113);
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
	void testLevelUp() {
		Champion testChampion = new JohnDoe();
		assertEquals(testChampion.getLevel(), 1);

		testChampion.giveXP(150f);

		assertEquals(testChampion.getLevel(), 2);
	}

}
