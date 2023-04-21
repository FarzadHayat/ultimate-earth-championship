package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import champion.Champion;
import champion.champions.CharlesDarwin;
import champion.champions.Confucius;
import champion.champions.QueenVictoria;
import champion.champions.StephenHawking;
import champion.champions.SunTzu;
import exception.FullTeamException;
import main.Team;

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
		
		testTeam = new Team(false, champions);
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
	void testGetChosenChampions() {
		assertEquals(testTeam.getChosenChampions().get(0).getName(), new CharlesDarwin().getName() );
	}

	@Test
	void testGetReserveChampions() {
		assertEquals(testTeam.getReserveChampions().size(), 0);
	}

	@Test
	void testHasMoney() {

		// Assumes starting with 100 money
		
		assertEquals(testTeam.hasMoney(10), true);
		
		assertEquals(testTeam.hasMoney(100), true);

		assertEquals(testTeam.hasMoney(10000), false);
	}

	@Test
	void testGetAllChampions() {
		assertEquals(testTeam.getAllChampions().size(), 4);
		
		try {
			testTeam.addChampion(new Confucius());
		}
		catch (FullTeamException e) {
			e.printStackTrace();
		}
		
		assertEquals(testTeam.getAllChampions().size(), 5);
	}

	@Test
	void testAddChampion() {
		assertEquals(testTeam.getReserveChampions().size(), 0);
		
		try {
			testTeam.addChampion(new Confucius());
		}
		catch (FullTeamException e) {
			e.printStackTrace();
		}
		
		assertEquals(testTeam.getReserveChampions().size(), 1);
	}

	@Test
	void testSwapChampion() {
		
		try {
			testTeam.addChampion(new Confucius());
		}
		catch (FullTeamException e) {
			e.printStackTrace();
		}
	
		Champion confucius = testTeam.getReserveChampions().get(0);
		
		Champion darwin = testTeam.getChosenChampions().get(0);
		
		testTeam.swapChampion(confucius, darwin);
		
		assertEquals(testTeam.getChosenChampions().get(0).getName(), "Confucius");
	}

}
