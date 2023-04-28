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


class SetupManagerTest { 

	private InputException inputException;
	
	@Test
	void testPromptForTeamName() {
		
		ArrayList<String> inputsToTest = new ArrayList<String>();
		
		inputsToTest.add(""); // Too small
		inputsToTest.add("abc"); // Too small edge

		inputsToTest.add("aaaaabbbbbcccccd"); // Too big edge
		inputsToTest.add("aaaaabbbbbcccccdasdasdasdasda"); // Too big
		
		inputsToTest.add("@#@#@#@#@"); // Special Characters
		inputsToTest.add("abcde!"); // Special Characters continued
		
		// Test all these inputs and ensure that these throw an exception
		for (String input : inputsToTest)
		{
			assertThrows(InputException.class, () -> {
				SetupManager.PromptForTeamName(input);
			});
		}
		
		// Do a regular input
		try 
		{
			assertEquals("BlueTeam", SetupManager.PromptForTeamName("BlueTeam"));
		} 
		catch (InputException e) 
		{
			fail( e.getMessage() );
		}
	}

	@Test
	void testPromptForNumWeeks() {
		
		ArrayList<String> inputsToTest = new ArrayList<String>();
		
		inputsToTest.add("0"); // Too small
		inputsToTest.add("4"); // Too small edge

		inputsToTest.add("16"); // Too big edge
		inputsToTest.add("250"); // Too big
		
		inputsToTest.add("unexpected input"); // Unexpected input
		
		// Test all these inputs and ensure that these throw an exception
		for (String input : inputsToTest)
		{
			assertThrows(InputException.class, () -> {
				SetupManager.PromptForNumWeeks(input);
			});
		}
		
		// Do a regular input
		try 
		{
			assertEquals(12, SetupManager.PromptForNumWeeks("12"));
		} 
		catch (InputException e) 
		{
			fail( e.getMessage() );
		}
	}

	@Test
	void testChooseChampionFrom() {
		ArrayList<Champion> testChampions = new ArrayList<Champion>();
		
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

	@Test
	void testPromptForDifficulty() {
		ArrayList<String> inputsToTest = new ArrayList<String>();
		
		inputsToTest.add("-12"); // Too small
		inputsToTest.add("0.49"); // Too small edge

		inputsToTest.add("2.1"); // Too big edge
		inputsToTest.add("200"); // Too big
		
		inputsToTest.add("unexpected input"); // Unexpected input
		
		// Test all these inputs and ensure that these throw an exception
		for (String input : inputsToTest)
		{
			assertThrows(InputException.class, () -> {
				SetupManager.PromptForDifficulty(input);
			});
		}
		
		// Do a regular input
		try 
		{
			assertEquals(1.2f, SetupManager.PromptForDifficulty("1.2"));
		} 
		catch (InputException e) 
		{
			fail( e.getMessage() );
		}
	}

	@Test
	void testPromptForTeamChampions() {
		ArrayList<Champion> champions = new ArrayList<Champion>();
		
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

}
