package match;

import model.Team;

public class MatchResult {
	
	/**
	 * The team that has won
	 */
	public Team winningTeam;
	
	/**
	 * The team that has lost
	 */
	public Team losingTeam;
	
	/**
	 * The money given to the winning team
	 */
	public float winningTeamMoney;
	
	/**
	 * The score given to the winning team
	 */
	public float winningTeamScore;
	
	/**
	 * The money given to the losing team
	 */
	public float losingTeamMoney;
	
	/**
	 * The score given to the losing team
	 */
	public float losingTeamScore;
	
	/**
	 * Match result constructor
	 * @param winningTeam The winning team
	 * @param winningTeamMoney The money for the winning team
	 * @param winningTeamScore The score for the winning team
	 * @param losingTeam The losing team
	 * @param losingTeamMoney The money for the losing team
	 */
	public MatchResult(Team winningTeam, float winningTeamMoney, float winningTeamScore, Team losingTeam, float losingTeamMoney)
	{
		this.winningTeam = winningTeam;
		this.winningTeamMoney = winningTeamMoney;
		this.winningTeamScore = winningTeamScore;
		
		this.losingTeam = losingTeam;
		this.losingTeamMoney = losingTeamMoney;
		this.losingTeamScore = 0f; // Losing teams shouldn't get any score
	}
	
}
