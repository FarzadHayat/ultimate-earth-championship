package match;

import java.util.concurrent.ThreadLocalRandom;

import manager.GameManager;
import model.Champion;
import model.Configuration;
import model.Team;

/**
 * Superclass for dumb and live matches, Keeps hold of relevant data relating to
 * a match as well as some helper functions for combat
 */
public abstract class Match {

	Configuration config = Configuration.getInstance();
	GameManager gameManager = GameManager.getInstance();

	/**
	 * The 1st team competing in this match
	 */
	protected Team team1;

	/**
	 * The 2nd team competing in this match
	 */
	protected Team team2;

	/**
	 * The amount of money the winning team earns, the losing team earns a fraction
	 * of this amount
	 */
	protected float prizeMoney;

	/**
	 * The amount of score the winning team earns
	 */
	protected int prizeScore;

	/**
	 * Gets the score
	 *
	 * @return The score
	 */
	public int getScore() {
		return prizeScore;
	}

	/**
	 * Gets the 1st team in the match
	 *
	 * @return The 1st team
	 */
	public Team getTeam1() {
		return team1;
	}

	/**
	 * Gets the 2nd team competing in this match
	 *
	 * @return The 2nd team
	 */
	public Team getTeam2() {
		return team2;
	}

	/**
	 * Match constructor
	 *
	 * @param team1 the 1st team competing
	 * @param team2 the 2nd team competing
	 */
	Match(Team team1, Team team2) {
		this.team1 = team1;

		this.team2 = team2;

		if (!team1.isPlayerTeam()) {
			team1.randomlySelectPurchasables();
		}

		if (!team2.isPlayerTeam()) {
			team2.randomlySelectPurchasables();
		}

		prizeMoney = ((team1.getScore() + team2.getScore()) * config.PRIZE_MONEY_SCORE_SCALE_FACTOR)
				+ config.PRIZE_MONEY_BASE;

		int currentWeek = gameManager.getGameEnvironment().getCurrentWeek();

		prizeScore = config.PRIZE_SCORE_BASE + (currentWeek * config.PRIZE_SCORE_WEEKLY_MODIFIER);
	}

	/**
	 * get the results for the match. Handled individually by the match subclasses.
	 *
	 * @return a MatchResult object containing the match results
	 */
	public abstract MatchResult getMatchResult();

	/**
	 * Simulates combat between who champions
	 *
	 * @param attacker The champion attacking
	 * @param defender The champion defending
	 * @return The winning champion
	 */
	protected Champion combat(Champion attacker, Champion defender) {
		int attackRoll = attacker.getOffense() + d20();
		int defenseRoll = defender.getDefense() + d20();

		if (Configuration.DEBUG) {
			System.out.println("DEBUG: Attacker rolls " + attackRoll + ". Defender rolls " + defenseRoll);
		}

		if (attackRoll >= defenseRoll) {
			// Attacker wins
			return attacker;
		} else {
			// Defender wins
			return defender;
		}
	}

	/**
	 * Simulates a 20 sided dice
	 *
	 * @return A random int between 1 and 20
	 */
	private int d20() {
		return ThreadLocalRandom.current().nextInt(1, 20 + 1);
	}

	/**
	 * Generates a MatchResult for the end of the match, Also applied the effect of
	 * the match to the winner and loser
	 *
	 * @param winner The winning team of the match
	 * @param loser  The losing team of the match
	 * @return A MatchResult object detailing the outcome of the match to be
	 *         displayed by the View
	 */
	protected MatchResult matchOver(Team winner, Team loser) {
		// Apply match conditions:
		winner.addMoney(prizeMoney);
		winner.addScore(prizeScore);
		loser.addMoney(prizeMoney * config.PRIZE_MONEY_FOR_LOSER);

		// Reset chosen purchasables for the teams
		team1.unselectPurchasables();
		team2.unselectPurchasables();

		// Returns match results
		return new MatchResult(winner, prizeMoney, prizeScore, loser, prizeMoney * config.PRIZE_MONEY_FOR_LOSER);

	}

}
