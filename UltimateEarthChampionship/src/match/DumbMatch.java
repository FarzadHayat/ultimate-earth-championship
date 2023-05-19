package match;

import java.util.ArrayList;
import java.util.Random;

import model.Champion;
import model.Configuration;
import model.Team;

/**
 * Class which represents a 'dumb' match, which is a match with no visuals occuring.
 * Dumb matches are auto-resolved by the computer and simply return a result
 */
public class DumbMatch extends Match {

	/**
	 * Creates a dumb match between two teams
	 * @param team1 The first team to fight
	 * @param team2 The second team to fight
	 */
	public DumbMatch(Team team1, Team team2) {
		super(team1, team2);
	}

	
	/**
	 * Runs the dumb match as a best of three fight between the two teams assigned to this match
	 * @return The result of the match as a MatchResult object
	 */
	public MatchResult getMatchResult() {

		ArrayList<Champion> team1Champs = team1.getChampions();
		ArrayList<Champion> team2Champs = team2.getChampions();

		// Prepare a best of 3 match...
		int team1Score = 0;
		int team2Score = 0;

		int roundNumber = -1;

		boolean matchRunning = true;

		while (matchRunning) {
			roundNumber += 1;

			if (team1Score == 2) {
				// team 1 wins match
				return matchOver(team1, team2);
			}

			if (team2Score == 2) {
				// team2 wins match
				return matchOver(team2, team1);
			}

			Champion team1Champ = team1Champs.get(roundNumber);
			Champion team2Champ = team2Champs.get(roundNumber);

			Champion winner = fight(team1Champ, team2Champ);

			if (winner == team1Champ) {
				// team 1 wins round
				team1Score += 1;
			} else {
				// team 2 wins round
				team2Score += 1;
			}
		}

		return null;
	}

	/**
	 * Gets two champions to fight eachother, returning the winning champion
	 * @param champ1 The first champion fighting
	 * @param champ2 The second champion fighting
	 * @return The winning champion
	 */
	private Champion fight(Champion champ1, Champion champ2) {
		boolean fighting = true;

		Random rand = new Random();
		boolean team1Attacker = rand.nextBoolean(); // Get random boolean

		while (fighting) {
			Champion attacker = null;
			Champion defender = null;

			if (team1Attacker) {
				team1Attacker = false;
				// team 1 champion attacks
				attacker = champ1;
				defender = champ2;
			} else {
				team1Attacker = true;
				// team 2 champion attacks
				attacker = champ2;
				defender = champ1;
			}

			// Find possible damage if the attacker wins
			float damage = attacker.getDamage();

			// Find winner in the fight
			Champion winner = combat(attacker, defender);

			if (winner == defender) {
				if (Configuration.DEBUG) {
					System.out.println("DEBUG: " + defender.getName() + " succesfully dodges " + attacker.getName()
							+ "'s attack!");
				}
				// Defender dodges!
				defender.giveXP(damage);
			} else {
				if (Configuration.DEBUG) {
					System.out.println("DEBUG: " + attacker.getName() + " succesfully hits " + defender.getName()
							+ " for " + damage + " damage!");
				}
				attacker.giveXP(damage);
				defender.addStamina(-damage);

				// Check for stamina
				if (defender.getStamina() <= 0) {
					if (Configuration.DEBUG) {
						System.out.println("DEBUG" + defender.getName() + " has been knocked out!");
					}
					return attacker;
				}
			}

		}

		return null;
	}

}
