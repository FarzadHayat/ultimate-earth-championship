package match;

import java.util.ArrayList;

import model.Champion;
import model.Team;
import views.ChampionMatchCard;
import views.MatchView;

public class LiveMatch extends Match{

	private ArrayList<ArrayList<ChampionMatchCard>> cards;
	
	private MatchView matchView;
	
	/**
	 * List of champions in the order that their turn occurs
	 */
	private ArrayList<Champion> turnOrder;
	
	private int turn;
	
	private Champion currentChampion;
	
	public ChampionMatchCard getCard(int row, int column)
	{
		return cards.get(row).get(column);
	}
	
	public void setCards(ArrayList<ArrayList<ChampionMatchCard>> cards)
	{
		this.cards = cards;
	}
	
	public void setMatchView(MatchView matchView)
	{
		this.matchView = matchView;
	}
	
	public LiveMatch(Team team1, Team team2) {
		super(team1, team2);
		
		turn = -1;
	}
	
	/**
	 * Assigns the player champions and enemy champions to their starting positions
	 * on the game board. Called by the MatchView
	 */
	public void assignChampions()
	{
		turnOrder = new ArrayList<Champion>();
		
		// Player Champions:
		int lane = 0;
		for (Champion champion : team1.getChosenChampions())
		{
			champion.setLane(lane);
			champion.setPosition(2);
			
			cards.get(lane).get(2).setChampion(champion);
			turnOrder.add(champion);
			
			lane++;
		}
		
		// Enemy champions:
		System.out.println("WARNING: Currently using team2.getChampions() as getChosenChampions is not working for AI teams");
		lane = 0;
		for (Champion champion : team2.getChampions())
		{
			// REMOVE THIS ONCE getChosenChampions is fixed!!!!
			// This prevents errors in the event of team champion length > 4
			if (lane >= 4)
			{
				System.out.println("Breaking!");
				break;
			}
			
			champion.setLane(lane);
			champion.setPosition(4);
			
			cards.get(lane).get(4).setChampion(champion);
			turnOrder.add(champion);
			
			lane++;
		}
	}

	public void nextTurn()
	{
		turn++;
		
		// Wrap turn around so it doesn't go out of index
		if (turn == 8)
		{
			turn = 0;
		}
		
		currentChampion = turnOrder.get(turn);
		
		matchView.selectChampion(currentChampion);
		
	}
	
	@Override
	public MatchResult getMatchResult() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ChampionMatchCard getChampionCard(Champion champ)
	{
		return cards.get(champ.getLane()).get(champ.getPosition());
	}
	
	public void setChampionCard(Champion champ)
	{
		getCard(champ.getLane(), champ.getPosition()).setChampion(champ);
	}
	
	public boolean championIsOnTeam(Champion champion, Team team)
	{
		if (champion == null) { return false; }
		return team1.getChosenChampions().contains(champion);
	}
	
	
	// Button Functions:
	
	public void championRetreat()
	{
		getChampionCard(currentChampion).removeChampion();
		
		currentChampion.setPosition(currentChampion.getPosition()-1);
		
		setChampionCard(currentChampion);
		
		nextTurn();
	}
	
	public void championAttack()
	{
		
	}
	
	public void championWait()
	{
		// Champion does nothing
		nextTurn();
	}
	
	public void championAttackUp()
	{
		
	}
	
	public void championAttackDown()
	{
		
	}

}
