package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import champions.JoeRogan;
import champions.JohnDoe;
import champions.KingGeorge;
import champions.RudyardKipling;
import champions.ShokoAsahara;
import exception.InputException;
import model.Champion;
import model.SetupManager;
import model.Weapon;
import weapons.Axe;
import weapons.Katana;
import weapons.Mace;
import weapons.Sword;

/**
 * Tests the setup manager
 * Ensures that incorrect inputs are not allowed
 */
class SetupManagerTest {

	/**
	 * Tests that the PromptForTeamName function works as intended.
	 * Testing attempts a variety of inputs.
	 */
	@Test
	void testPromptForTeamName() {

		ArrayList<String> inputsToTest = new ArrayList<>();

		inputsToTest.add(""); // Too small
		inputsToTest.add("ab"); // Too small edge

		inputsToTest.add("aaaaabbbbbcccccd"); // Too big edge
		inputsToTest.add("aaaaabbbbbcccccdasdasdasdasda"); // Too big

		inputsToTest.add("@#@#@#@#@"); // Special Characters
		inputsToTest.add("abcde!"); // Special Characters continued

		// Test all these inputs and ensure that these throw an exception
		for (String input : inputsToTest) {
			assertThrows(InputException.class, () -> {
				SetupManager.PromptForTeamName(input);
			});
		}

		// Do a regular input
		try {
			assertEquals("Blue Team", SetupManager.PromptForTeamName("Blue Team"));
		} catch (InputException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests that the PromptForNumWeeks function is working properly
	 * and only allowing legal inputs
	 */
	@Test
	void testPromptForNumWeeks() {

		ArrayList<String> inputsToTest = new ArrayList<>();

		inputsToTest.add("0"); // Too small
		inputsToTest.add("4"); // Too small edge

		inputsToTest.add("16"); // Too big edge
		inputsToTest.add("250"); // Too big

		inputsToTest.add("unexpected input"); // Unexpected input

		// Test all these inputs and ensure that these throw an exception
		for (String input : inputsToTest) {
			assertThrows(InputException.class, () -> {
				SetupManager.PromptForNumWeeks(input);
			});
		}

		// Do a regular input
		try {
			assertEquals(12, SetupManager.PromptForNumWeeks("12"));
		} catch (InputException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests the ChooseChampionFrom function and ensures it is working
	 */
	@Test
	void testChooseChampionFrom() {
		ArrayList<Champion> testChampions = new ArrayList<>();

		testChampions.add(new JoeRogan());
		testChampions.add(new RudyardKipling());
		testChampions.add(new KingGeorge());
		testChampions.add(new ShokoAsahara());

		// Unexpected input
		assertThrows(InputException.class, () -> {
			SetupManager.ChooseChampionFrom(testChampions, "asdasdasd");
		});

		// Too high
		assertThrows(InputException.class, () -> {
			SetupManager.ChooseChampionFrom(testChampions, "12");
		});

		// Correct input
		try {
			assertEquals(SetupManager.ChooseChampionFrom(testChampions, "1").getName(), "Joe Rogan");
		} catch (InputException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests the PromptForDifficulty function and ensures it works as intended
	 */
	@Test
	void testPromptForDifficulty() {
		ArrayList<String> inputsToTest = new ArrayList<>();

		inputsToTest.add("-12"); // Too small
		inputsToTest.add("0.49"); // Too small edge

		inputsToTest.add("2.1"); // Too big edge
		inputsToTest.add("200"); // Too big

		inputsToTest.add("unexpected input"); // Unexpected input

		// Test all these inputs and ensure that these throw an exception
		for (String input : inputsToTest) {
			assertThrows(InputException.class, () -> {
				SetupManager.PromptForDifficulty(input);
			});
		}

		// Do a regular input
		try {
			assertEquals(1.2f, SetupManager.PromptForDifficulty("1.2"));
		} catch (InputException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests the PromptForTeamChampions function and ensures it works as intended
	 */
	@Test
	void testPromptForTeamChampions() {
		ArrayList<Champion> champions = new ArrayList<>();

		champions.add(new JohnDoe());

		// Too small
		assertThrows(InputException.class, () -> {
			SetupManager.PromptForTeamChampions(champions);
		});

		champions.add(new JohnDoe());
		champions.add(new JohnDoe());
		champions.add(new JohnDoe());
		champions.add(new JohnDoe());

		// Too big
		assertThrows(InputException.class, () -> {
			SetupManager.PromptForTeamChampions(champions);
		});

		champions.remove(0);

		// Correct size of 4
		try {
			SetupManager.PromptForTeamChampions(champions);
		} catch (InputException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests the chooseWeaponFrom function and ensures it works as intended
	 */
	@Test
	void testChooseWeaponFrom()
	{
		ArrayList<Weapon> testWeapons = new ArrayList<Weapon>();

		testWeapons.add(new Katana());
		testWeapons.add(new Sword());
		testWeapons.add(new Mace());
		testWeapons.add(new Axe());

		// Unexpected input
		assertThrows(InputException.class, () -> {
			SetupManager.ChooseWeaponFrom(testWeapons, "adasdadas");
		});

		// Too high
		assertThrows(InputException.class, () -> {
			SetupManager.ChooseWeaponFrom(testWeapons, "12");
		});

		// Correct input
		try {
			assertEquals(SetupManager.ChooseWeaponFrom(testWeapons, "1").getClass(), Katana.class);
		} catch (InputException e) {
			fail(e.getMessage());
		}
	}
}
