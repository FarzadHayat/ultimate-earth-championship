package tests;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import champions.AdamSmith;
import champions.CharlesDarwin;
import champions.JohnBrowning;
import champions.JohnDoe;
import champions.JohnFKennedy;
import champions.KingGeorge;
import champions.QueenVictoria;
import champions.StephenHawking;
import manager.GameManager;
import manager.GraphicalGameManager;
import model.Champion;
import model.Team;

class AIGenerationTest {

	@Test
	void testGenerateAITeams() {

		int testNum = 10;

		int i = 0;

		while (i < testNum) {
			System.out.println("Starting test...");
			GameManager manager = new GraphicalGameManager();

			manager.initialize();

			ArrayList<Champion> playerChampions = new ArrayList<>();

			playerChampions.add(new CharlesDarwin());
			playerChampions.add(new KingGeorge());
			playerChampions.add(new StephenHawking());
			playerChampions.add(new QueenVictoria());

			manager.setupPlayerTeam("Test", 5, new ArrayList<Champion>(
					List.of(new AdamSmith(), new JohnDoe(), new JohnBrowning(), new JohnFKennedy())), 1.0F);

			manager.getTeams().addAll(manager.generateAITeams());

			ArrayList<Champion> uniqueChampions = new ArrayList<>();

			ArrayList<Team> AITeams = new ArrayList<Team>(manager.getAITeams());

			for (Team team : AITeams) {
				for (Champion champion : team.getChampions()) {
					if (uniqueChampions.contains(champion)) {
						fail("Duplicate champion found!");
					}

					uniqueChampions.add(champion);
				}
			}

			i++;
		}
	}

}
