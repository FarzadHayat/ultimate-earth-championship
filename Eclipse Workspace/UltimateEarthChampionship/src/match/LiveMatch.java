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
	
	/**
	 * Gets a champion match card
	 * @param champ The champion which card is wanted
	 * @return The champion card of the champion
	 */
	public ChampionMatchCard getCard(Champion champ)
	{
		return cards.get(champ.getLane()).get(champ.getPosition());
	}
	
	/**
	 * Gets a champion match card at specified position
	 * @param row The row of the card
	 * @param column The column of the card
	 * @return The champion card at the specific position
	 */
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
	
	/**
	 * Constructor
	 * @param team1 The player team
	 * @param team2 The team the player is fighting
	 */
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
			
			lane++;
		}
	}

	public void nextTurn()
	{
		turn++;
		
		// Wrap turn around so it doesn't go out of index
		if (turn == turnOrder.size())
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
	
	
	public void setChampionCard(Champion champ)
	{
		getCard(champ.getLane(), champ.getPosition()).setChampion(champ);
	}
	
	public boolean championIsOnPlayerTeam(Champion champion)
	{
		if (champion == null) { return false; }
		
		return team1.getChosenChampions().contains(champion);
	}
	
	/**
	 * Gets a champion to retreat back a tile depending on what team they're on
	 * @param champion the champion to retreat
	 */
	private void championMoveBack(Champion champion)
	{
		int movementInt = 0;
		
		if (championIsOnPlayerTeam(champion))
		{
			// It is on the player team
			movementInt = -1;
		}
		else {
			// On the enemy team
			movementInt = +1;
		}
		
		moveChampion(champion, movementInt);
	}
	
	/**
	 * Moves a champion forward a tile, depending on what team they're on
	 * @param champion The champion to move forward
	 */
	private void championMoveForward(Champion champion)
	{
		int movementInt = 0;
		
		if (championIsOnPlayerTeam(champion))
		{
			// It is on the player team
			movementInt = +1;
		}
		else {
			// On the enemy team
			movementInt = -1;
		}
		
		moveChampion(champion, movementInt);
	}
	
	/**
	 * Moves a champion in a direction by updating champion card
	 * @param champion The champion to move
	 * @param direction The direction to go (either +1 or -1)
	 */
	private void moveChampion(Champion champion, int direction)
	{
		if (champion.getPosition()+direction == 7 || champion.getPosition()+direction == -1)
		{
			// Moving would take the champion off the screen, so don't move
			return;
		}
		
		ChampionMatchCard currentCard = getCard(champion);
		ChampionMatchCard nextCard = getCard(champion.getLane(), champion.getPosition()+direction);
		
		// Move the champion
		currentCard.removeChampion();
		nextCard.setChampion(champion);
		
		// Tell the champion of their new position
		champion.setPosition(champion.getPosition()+direction);
		
		// Update the cards
		currentCard.updateCard();
		nextCard.updateCard();
	}
	
	/**
	 * Causes two champions to fight each other
	 * @param attacker The attacker
	 * @param defender The defender
	 */
	private void championFight(Champion attacker, Champion defender)
	{
		// Find possible damage if the attacker wins
		float damage = attacker.getDamage();
		
		// Find winner in the fight
		Champion winner = combat(attacker, defender);
		
		if (winner == defender)
		{
			// Defender dodges!
			matchView.showDialogue(defender.getName() + " succesfully dodges " + attacker.getName() + "'s attack!");
			
			defender.giveXP(damage);
		}
		else
		{
			matchView.showDialogue(attacker.getName() + " succesfully hits " + defender.getName() + " for " + damage + " damage!");
			
			// Make the champion fall back
			championMoveBack(defender);
			
			// damage the champion and give Xp to the attacker
			attacker.giveXP(damage);
			defender.addHealth(-damage);
			
			// Check for health
			if (defender.getHealth() <= 0)
			{
				matchView.showDialogue(defender.getName() + " has been knocked out!");
				getCard(defender).removeChampion();
				// Remove from AI turn order
			}
		}
}
	
	
	// Button Functions:
	
	/**
	 * Called by the view upon the player wanting the champion to retreat
	 */
	public void championRetreat()
	{
		championMoveBack(currentChampion);
		
		nextTurn();
	}
	
	/**
	 * Called by the view upon the player wanting the champion to attack/move forward
	 */
	public void championAttack()
	{
		ChampionMatchCard nextCard = getCard(currentChampion.getLane(), currentChampion.getPosition()+1);
		Champion championToFight = nextCard.getChampion();
		
		if (championToFight == null)
		{
			// Move the champion forward
			championMoveForward(currentChampion);
		}
		else {
			// Attack the next champion
			championFight(currentChampion, nextCard.getChampion());
			
			// Update both cards
			getCard(championToFight).updateCard();
			getCard(currentChampion).updateCard();
		}
		
		nextTurn();
	}
	
	/**
	 * Called by the view upon the player wanting the champion to wait the turn
	 */
	public void championWait()
	{
		// Champion does nothing
		nextTurn();
	}
	
	/**
	 * Called by the view upon the player wanting their champion to attack up
	 */
	public void championAttackUp()
	{
		
	}
	
	/**
	 * Called by the view upon the player wanting their champion to attack down
	 */
	public void championAttackDown()
	{
		
	}

}
