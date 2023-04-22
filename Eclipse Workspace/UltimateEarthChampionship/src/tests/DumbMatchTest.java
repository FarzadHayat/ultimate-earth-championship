package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.regex.MatchResult;

import org.junit.jupiter.api.Test;

import match.DumbMatch;
import model.Team;
import champion.*;
import champion.champions.*;

class DumbMatchTest {

	@Test
	void mainTest() {
		
		ArrayList<Champion> a = new ArrayList<Champion>();
		
		a.add(new BernardMontgomery());
		a.add(new QueenVictoria());
		a.add(new SunTzu());
		a.add(new WilliamShakespeare());
		
		Team team1 = new Team(false, a);
		
		a = new ArrayList<Champion>();
		
		a.add(new CharlesDarwin());
		a.add(new Confucius());
		a.add(new AdamSmith());
		a.add(new CharlesDarwin());
		
		Team team2 = new Team(false, a);
		
		DumbMatch testMatch = new DumbMatch(team1, team2);
		
		match.MatchResult result = testMatch.runMatch();
		
		System.out.println("Winning team: " + result.winningTeam);
		System.out.println("Prize Money: " + result.winningTeamMoney);
		System.out.println("Prize Score: " + result.winningTeamScore);
	}

}
