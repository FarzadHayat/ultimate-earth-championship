package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import champions.CharlesDarwin;
import champions.Confucius;
import champions.QueenVictoria;
import champions.StephenHawking;
import champions.SunTzu;
import exception.FullTeamException;
import model.Champion;
import model.Team;

class TeamTest {

	private Team testTeam;
	
	@BeforeEach
	void setup()
	{
		// Reset test team
		
		ArrayList<Champion> champions = new ArrayList<Champion>();
		
		champions.add(new CharlesDarwin());
		champions.add(new SunTzu());
		champions.add(new QueenVictoria());
		champions.add(new StephenHawking());
		
		testTeam = new Team(false, "", champions);
	}
	
	@Test
	void testAddMoney() {
		
		assertEquals(testTeam.getMoney(), 100f);
		
		testTeam.addMoney(50f);
		
		assertEquals(testTeam.getMoney(), 150f);
	}

	@Test
	void testAddScore() {
		assertEquals(testTeam.getScore(), 0);
		
		testTeam.addScore(7);

		assertEquals(testTeam.getScore(), 7);
	}

	@Test
	void testGetChampions() {
		assertEquals(testTeam.getChampions().get(0).getName(), new CharlesDarwin().getName());
	}

	@Test
	void testGetChampions2() {
		assertEquals(testTeam.getChampions().size(), 4);
	}

	@Test
	void testHasMoney() {

		// Assumes starting with 100 money
		
		assertEquals(testTeam.hasMoney(10), true);
		
		assertEquals(testTeam.hasMoney(100), true);

		assertEquals(testTeam.hasMoney(10000), false);
	}

	@Test
	void testGetChampions3() {
		assertEquals(testTeam.getChampions().size(), 4);
		
		try {
			testTeam.addChampion(new Confucius());
		}
		catch (FullTeamException e) {
			e.printStackTrace();
		}
		
		assertEquals(testTeam.getChampions().size(), 5);
	}

	@Test
	void testAddChampion() {
		assertEquals(testTeam.getChampions().size(), 4);
		
		try {
			testTeam.addChampion(new Confucius());
		}
		catch (FullTeamException e) {
			e.printStackTrace();
		}
		
		assertEquals(testTeam.getChampions().size(), 5);
	}

}
