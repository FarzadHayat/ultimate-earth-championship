package match;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import manager.GameManager;
import model.Champion;
import model.Configuration;
import model.Team;

public abstract class Match {
	
	Configuration config = Configuration.getInstance();
	
	/**
	 * The 1st team competing in this match
	 */
	protected Team team1;
	
	/**
	 * The 2nd team competing in this match
	 */
	protected Team team2;

	/**
	 * The amount of money the winningJ team earns, the losing team earns a fraction of this amount
	 */
	protected float prizeMoney;
	
	/**
	 * The amount of score the winning team earns
	 */
	protected int prizeScore;
	
	/**
	 * Match constructor
	 * @param team1 the 1st team competing
	 * @param team2 the 2nd team competing
	 */
	Match(Team team1, Team team2)
	{
		this.team1 = team1;
		
		this.team2 = team2;
		
		prizeMoney = ((team1.getScore() + team2.getScore()) * config.PRIZE_MONEY_SCORE_SCALE_FACTOR) + config.PRIZE_MONEY_BASE;
		
		int currentWeek = GameManager.getInstance().getGameEnvironment().getCurrentWeek();
		
		prizeScore = config.PRIZE_SCORE_BASE + (currentWeek * config.PRIZE_SCORE_WEEKLY_MODIFIER);
	}
	
	public abstract MatchResult runMatch();
	
	
	/**
	 * Simulates combat between who champions
	 * @param attacker The champion attacking
	 * @param defender The champion defending
	 * @return The winning champion
	 */
	protected Champion combat(Champion attacker, Champion defender)
	{
		int attackRoll = attacker.getOffense() + d20();
		int defenseRoll = defender.getDefense() + d20();
		
		System.out.println("DEBUG: Attacker rolls " + attackRoll + ". Defender rolls " + defenseRoll);
		
		if (attackRoll >= defenseRoll)
		{
			// Attacker wins
			return attacker;
		}
		else
		{
			// Defender wins
			return defender;
		}
	}
	
	/**
	 * Simulates a 20 sided dice
	 * @return A random int between 1 and 20
	 */
	private int d20()
	{
		return ThreadLocalRandom.current().nextInt(1, 20 + 1);
	}
	
	protected MatchResult matchOver(Team winner, Team loser)
	{
		// Apply match conditions:
		winner.addMoney(prizeMoney);
		winner.addScore(prizeScore);
		loser.addMoney(prizeMoney * config.PRIZE_MONEY_FOR_LOSER);
		
		// Returns match results
		return new MatchResult(winner, prizeMoney, prizeScore, loser, prizeMoney * config.PRIZE_MONEY_FOR_LOSER);
		
	}


}
