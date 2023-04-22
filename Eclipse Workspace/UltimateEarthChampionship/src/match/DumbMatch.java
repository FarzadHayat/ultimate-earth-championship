package match;

import weapon.Weapon;
import champion.Champion;
import model.Team;

import java.util.*;

public class DumbMatch extends Match {

	public DumbMatch(Team team1, Team team2)
	{
		super(team1, team2);
	}
	
	@Override
	public MatchResult runMatch() {
		
		ArrayList<Champion> team1Champs = team1.getChosenChampions();
		ArrayList<Champion> team2Champs = team2.getChosenChampions();
		
		// Prepare a best of 3 match...
		int team1Score = 0;
		int team2Score = 0;
		
		int roundNumber = -1;
		
		boolean matchRunning = true;
		
		while (matchRunning)
		{
			roundNumber += 1;
			
			if (team1Score == 2)
			{
				// team 1 wins match
				return matchOver(team1, team2);
			}
			
			if (team2Score == 2)
			{
				// team2 wins match
				return matchOver(team2, team1);
			}
			
			Champion team1Champ = team1Champs.get(roundNumber);
			Champion team2Champ = team2Champs.get(roundNumber);
			
			Champion winner = fight(team1Champ, team2Champ);
			
			if (winner == team1Champ)
			{
				// team 1 wins round
				team1Score += 1;
			}
			else
			{
				// team 2 wins round
				team2Score += 1;
			}
		}
		
		return null;
	}
	
	private Champion fight(Champion champ1, Champion champ2)
	{
		boolean fighting = true;
		
		Random rand = new Random();
		boolean team1Attacker = rand.nextBoolean(); // Get random boolean
		
		while(fighting)
		{
			Champion attacker = null;
			Champion defender = null;
			
			if (team1Attacker)
			{
				team1Attacker = false;
				// team 1 champion attacks
				attacker = champ1;
				defender = champ2;
			}
			else
			{
				team1Attacker = true;
				// team 2 champion attacks
				attacker = champ2;
				defender = champ1;
			}
			
			// Find possible damage if the attacker wins
			float damage = attacker.getDamage();
			
			// Find winner in the fight
			Champion winner = combat(attacker, defender);
			
			
			if (winner == defender)
			{
				System.out.println("DEBUG: " + defender.getName() + " succesfully dodges " + attacker.getName() + "'s attack!");
				// Defender dodges!
				defender.giveXP(damage);
			}
			else
			{
				System.out.println("DEBUG: " + attacker.getName() + " succesfully hits " + defender.getName() + " for " + damage + " damage!");
				attacker.giveXP(damage);
				defender.addHealth(-damage);
				
				// Check for health
				if (defender.getHealth() < 0)
				{
					System.out.println("DEBUG" + defender.getName() + " has been knocked out!");
					return attacker;
				}
			}
			
		}
		
		return null;
	}

}
