package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import champions.Confucius;
import champions.HarryTruman;
import champions.JohnBrowning;
import champions.JohnDoe;
import champions.JohnFKennedy;
import champions.NikitaKrustchev;
import champions.SunTzu;
import champions.TedKaczynski;
import manager.GameManager;
import match.LiveMatch;
import match.MatchResult;
import model.Champion;
import model.Configuration;
import model.Team;
import views.ChampionCard;
import views.ChampionMatchCard;

/**
 * Tests the live match to the best of my ability
 * 
 */
class LiveMatchTest {

	// A match instance to test
	private static LiveMatch testMatch;
	
	// A player team to test with
	private static Team testPlayerTeam;
	
	// A enemy team to test with
	private static Team testEnemyTeam;
	
	
	/**
	 * Sets up some base data
	 */
	@BeforeAll
	static void setup()
	{
		ArrayList<Champion> champs1 = new ArrayList<>();
		champs1.add(new JohnBrowning());
		champs1.add(new JohnDoe());
		champs1.add(new JohnFKennedy());
		champs1.add(new SunTzu());
		
		ArrayList<Champion> champs2 = new ArrayList<>();
		champs2.add(new Confucius());
		champs2.add(new NikitaKrustchev());
		champs2.add(new HarryTruman());
		champs2.add(new TedKaczynski());
		
		testPlayerTeam = new Team(true, "testPlayer", champs1);
		testPlayerTeam.setChosenChampions(champs1);
		
		testEnemyTeam = new Team(false, "testEnemy", champs2);
		testEnemyTeam.setChosenChampions(champs2);
		
		GameManager.getInstance().initialize();
		
		testMatch = new LiveMatch(testPlayerTeam, testEnemyTeam);
		
		// Set new cards:
		ArrayList<ArrayList<ChampionMatchCard>> cards = new ArrayList<>();

		int row = 0;
		while (row < Configuration.NUM_CHOSEN_CHAMPIONS) {
			ArrayList<ChampionMatchCard> cardsToAdd = new ArrayList<>();

			int column = 0;

			while (column < Configuration.NUM_MATCH_COLUMNS) {
				ChampionMatchCard matchCard = new ChampionMatchCard(null, row, column);

				matchCard.updateCard();

				cardsToAdd.add(matchCard);

				column++;
			}

			cards.add(cardsToAdd);
			row++;
		}
		
		testMatch.setCards(cards);
		testMatch.assignChampions();
		
	}
	
	/**
	 * Tests the assignment of champions to certain positions on the board
	 */
	@Test
	void testAssignChampions() {
		testMatch.assignChampions();
		
		testGetCardChampion();
		
	}
	
	
	/**
	 * Tests the getCard method with a champion
	 */
	@Test
	void testGetCardChampion() {
		
		// Check that champions are assigned:
		for (Champion champ : testEnemyTeam.getChampions())
		{
			assertNotEquals(testMatch.getCard(champ), null);
		}
		for (Champion champ : testPlayerTeam.getChampions())
		{
			assertNotEquals(testMatch.getCard(champ), null);
		}
	}

	/**
	 * Tests the getCard method with an integer
	 */
	@Test
	void testGetCardInt() {
		// WARNING:
		// THis test assumes that a champion will have spawned
		// in position 0, 2 and not one in position 3, 3
				
		assertNotEquals(testMatch.getCard(0, 2).getChampion(), null);
		
		assertEquals(testMatch.getCard(3, 3).getChampion(), null);
	}

//	@Test
//	void testNextTurn() {
//		// Due to the high coupling with LiveMatch and MatchView,
//		// it is infeasible to conduct unit tests upon the NextTurn function
//		// A re-factor of this code is due.
//	}

	/**
	 * Tests the getMatchResults function
	 */
	@Test
	void testGetMatchResult() {
		MatchResult results = testMatch.getMatchResult(testPlayerTeam, testEnemyTeam);
		
		assertEquals(results.winningTeam, testPlayerTeam);
		assertEquals(results.losingTeam, testEnemyTeam);
		
		// Check money is being given
		assertNotEquals(results.losingTeamMoney, 0);
		assertNotEquals(results.winningTeamMoney, 0);
		
		// Check score is being given
		assertNotEquals(results.winningTeamMoney, 0);
		
		// Check loser gets no score
		assertEquals(results.losingTeamScore, 0); 
		
		// Undo the effects of ending the match
		setup();
	}

	/**
	 * Tests the setChampionCard function to ensure it is working as intended
	 */
	@Test
	void testSetChampionCard() {
		Champion champ = testPlayerTeam.getChampions().get(0);
		
		ChampionMatchCard championCard = testMatch.getCard(champ);
		
		assertNotEquals(championCard.getChampion(), null);
		
		testMatch.setChampionCard(champ);
		
		assertNotEquals(championCard.getChampion(), null);
	}

	/**
	 * Tests if the isChampionOnPlayerTeam function is working correctly and
	 * returing expected results
	 */
	@Test
	void testChampionIsOnPlayerTeam() {

		Champion playerChamp = testPlayerTeam.getChampions().get(1);

		Champion enemyChamp = testEnemyTeam.getChampions().get(1);
		
		assertEquals(testMatch.championIsOnPlayerTeam(playerChamp), true);

		assertEquals(testMatch.championIsOnPlayerTeam(enemyChamp), false);
		
		assertEquals(testMatch.championIsOnPlayerTeam(null), false);
	}

	
//	@Test
//	void testChampionRetreat() {
//		fail("Not yet implemented");
//	}

//	@Test
//	void testChampionAttack() {
//	
//	}

//	@Test
//	void testChampionWait() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testChampionAttackUp() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testChampionAttackDown() {
//		fail("Not yet implemented");
//	}

}
