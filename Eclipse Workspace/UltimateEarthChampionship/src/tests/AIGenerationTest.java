package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manager.GameManager;
import manager.GraphicalGameManager;
import model.Champion;
import model.Team;
import champions.*;

class AIGenerationTest {

	@Test
	void testGenerateAITeams() {
		
		
		int testNum = 10;
		
		int i = 0;
		
		while (i < testNum)
		{
			System.out.println("Starting test...");
			GameManager manager = new GraphicalGameManager();
			
			manager.initialize();
			
			ArrayList<Champion> playerChampions = new ArrayList<Champion>();
			
			playerChampions.add(new CharlesDarwin());
			playerChampions.add(new KingGeorge());
			playerChampions.add(new StephenHawking());
			playerChampions.add(new QueenVictoria());
			
			manager.setPlayerTeam(new Team(true, "playerTeam", playerChampions)); 
			
			ArrayList<Team> AITeams = manager.generateAITeams();
			
			ArrayList<Champion> uniqueChampions = new ArrayList();
			
			for (Team team : AITeams)
			{
				for (Champion champion : team.getChampions())
				{
					if (uniqueChampions.contains(champion))
					{
						fail("Duplicate champion found!");
					}
					
					uniqueChampions.add(champion);
				}
			}
			
			i++;
		}
	}

}