package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Team;
import champion.*;
import champion.champions.CharlesDarwin;
import champion.champions.Confucius;
import champion.champions.QueenVictoria;
import champion.champions.StevenHawking;
import champion.champions.SunTzu;

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
		champions.add(new StevenHawking());
		
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
		
		testTeam.addChampion(new Confucius());
		
		assertEquals(testTeam.getAllChampions().size(), 5);
	}

	@Test
	void testAddChampion() {
		assertEquals(testTeam.getReserveChampions().size(), 0);
		
		testTeam.addChampion(new Confucius());
		
		assertEquals(testTeam.getReserveChampions().size(), 1);
	}

	@Test
	void testSwapChampion() {
		
		testTeam.addChampion(new Confucius());
	
		Champion confucius = testTeam.getReserveChampions().get(0);
		
		Champion darwin = testTeam.getChosenChampions().get(0);
		
		testTeam.swapChampion(confucius, darwin);
		
		assertEquals(testTeam.getChosenChampions().get(0).getName(), "Confucius");
	}

}
