package match;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import model.Champion;
import model.Team;
import views.ChampionMatchCard;
import views.MatchView;

public class LiveMatch extends Match implements ActionListener{

	private ArrayList<ArrayList<ChampionMatchCard>> cards;
	
	private MatchView matchView;
	
	/**
	 * List of player champions in the order that their turn occurs
	 */
	private ArrayList<Champion> playerChampions;
	
	/**
	 * List of enemy champions in the order that their turn occurs
	 */
	private ArrayList<Champion> enemyChampions;
	
	private boolean playerTeamsTurn;
	
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
	 * @param lane The lane of the card (0 - 3)
	 * @param position The position of the card (0 - 7)
	 * @return The champion card at the specific position
	 */
	public ChampionMatchCard getCard(int lane, int position)
	{
		return cards.get(lane).get(position);
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
		playerChampions = new ArrayList<Champion>();
		enemyChampions = new ArrayList<Champion>();
		
		playerTeamsTurn = true;
		
		// Random int between 0 and 3 to determine who has the flag
		Random random = new Random();
		int flagLane = random.nextInt(4);
		
		// Player Champions:
		int lane = 0;
		for (Champion champion : team1.getChosenChampions())
		{
			champion.setLane(lane);
			champion.setPosition(2);
			
			// Assign flag
			if (lane == flagLane)
			{
				champion.setFlagCarrier(true);
			}
			else {
				champion.setFlagCarrier(false);
			}
			
			cards.get(lane).get(2).setChampion(champion);
			playerChampions.add(champion);
			
			lane++;
		}
		
		// Enemy champions:
		System.out.println("WARNING: Currently using team2.getChampions() as getChosenChampions is not working for AI teams");
		lane = 0;
		flagLane = random.nextInt(3);
		
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
			
			// Assign flag
			if (lane == flagLane)
			{
				champion.setFlagCarrier(true);
			}
			else {
				champion.setFlagCarrier(false);
			}
			
			cards.get(lane).get(4).setChampion(champion);
			enemyChampions.add(champion);
			
			lane++;
		}
	}

	public void nextTurn()
	{
		//System.out.println("nextTurn() called");
		turn++;
		
		// If all player champions have had their turn, it is the enemy teams turn to move
		if (playerTeamsTurn)
		{
			// Check if all player champions have had their turn
			if (turn == playerChampions.size())
			{
				playerTeamsTurn = false;
				turn = 0;
			}			
		}
		else {
			// Check if all enemy champions have had their turn
			if (turn == enemyChampions.size())
			{
				playerTeamsTurn = true;
				turn = 0;
			}
		}
		
		// Change button visibility on the view
		if (playerTeamsTurn)
		{
			//matchView.updateButtons();
		}
		else
		{
			//matchView.disableAllButtons();
		}
		
		if (playerTeamsTurn)
		{
			// Player's turn
			currentChampion = playerChampions.get(turn);
			
			matchView.selectChampion(currentChampion);
		}
		else
		{
			// Enemy turn
			System.out.println(enemyChampions.get(turn).getName() + "'s turn");
			
			currentChampion = enemyChampions.get(turn);
			
			AITurnDelay();
		}
		
		
		
		
		
	}
	
	
	@Override
	public MatchResult getMatchResult() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private void championAITurn()
	{
		// Get card infront
		ChampionMatchCard cardInfront = null;
		
		if (currentChampion.getPosition() != 0)
		{
			cardInfront = getCard(currentChampion.getLane(), currentChampion.getPosition()-1); 
		}
		
		//Get card above
		ChampionMatchCard cardAbove = null;

		if (currentChampion.getLane() != 0)
		{
			cardAbove = getCard(currentChampion.getLane()-1, currentChampion.getPosition()); 
			
			if (!championIsOnPlayerTeam(cardAbove.getChampion()))
			{
				// Reset to null IF champion above is on enemy team
				cardAbove = null;
			}
		}
		
		//Get card above
		ChampionMatchCard cardBelow = null;

		if (currentChampion.getLane() != 3)
		{
			cardBelow = getCard(currentChampion.getLane()+1, currentChampion.getPosition());
			if (!championIsOnPlayerTeam(cardBelow.getChampion()))
			{
				// Reset to null IF champion below is on enemy team
				cardBelow = null;
			}
		}
		
		// If there is a player champion infront of me, attack 100% of the time
		if (cardInfront.getChampion() != null)
		{
			// attack
			championFight(currentChampion, cardInfront.getChampion());
			
			nextTurn();
			return;
		}
		
		// If there is a player champion above me, attack 100% of the time
		if (cardAbove != null)
		{
			// attack up
			championFight(currentChampion, cardAbove.getChampion());
			
			nextTurn();
			return;
		}
		
		// If there is a player champion below me, attack 100% of the time
		if (cardBelow != null)
		{
			// attack down
			championFight(currentChampion, cardBelow.getChampion());
			
			nextTurn();
			return;
		}
		
		// Find a random aggression score, consisting of a random number + the teams aggression factor
		Random random = new Random();
		int agressionScore = random.nextInt(100) + team2.getAgression();
		
		if (agressionScore < 35)
		{
			// Wait
			nextTurn();
			return;
		}
		
		// Move forward
		championMoveForward(currentChampion);
			
		nextTurn();
		return;
		
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
		
		// Check after movement if the champion has properly moved
		if (champion.isFlagCarrier())
		{
			if (championIsOnPlayerTeam(champion) && champion.getPosition() == 6)
			{
				// Player victory!
				matchView.showDialogue(champion.getName() + " has moved the flag across the field! " +
										team1.getName() + " wins!");
			}
			
			if (!championIsOnPlayerTeam(champion) && champion.getPosition() == 1)
			{
				// Enemy victory!
				matchView.showDialogue(champion.getName() + " has moved the flag across the field! " +
										team2.getName() + " wins!");
			}
		}
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
		
		System.out.println("Movement of " + champion.getName() + " finished!");
	}
	
	/**
	 * Causes two champions to fight each other
	 * @param attacker The attacker
	 * @param defender The defender
	 */
	private void championFight(Champion attacker, Champion defender)
	{
		// Find possible damage if the attacker wins
		float rawDamage = attacker.getDamage();
		float adjustedDamage = rawDamage - defender.getDefense();
		
		// Find winner in the fight
		Champion winner = combat(attacker, defender);
		
		if (winner == defender)
		{
			// Defender dodges!
			matchView.showDialogue(defender.getName() + " succesfully dodges " + attacker.getName() + "'s attack!");
			
			defender.giveXP(rawDamage);
		}
		else
		{
			matchView.showDialogue(attacker.getName() + " succesfully hits " + defender.getName() + " for " + adjustedDamage + " damage!");
			
			// damage the champion and give XP to the attacker
			attacker.giveXP(rawDamage);
			defender.addHealth(-adjustedDamage);

			
			// Make the champion fall back
			championMoveBack(defender);
			
			// Check for health
			if (defender.getHealth() <= 0)
			{
				knockoutChampion(defender);
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
		// Attack up
		ChampionMatchCard championCardUp = getCard(currentChampion.getLane() -1, currentChampion.getPosition());
		Champion championUp = championCardUp.getChampion();
		
		// Attack the champion
		championFight(currentChampion, championUp);
		
		// Update both cards
		getCard(championUp).updateCard();
		getCard(currentChampion).updateCard();
		
		nextTurn();
	}
	
	/**
	 * Called by the view upon the player wanting their champion to attack down
	 */
	public void championAttackDown()
	{
		// Attack down
		ChampionMatchCard championCardDown = getCard(currentChampion.getLane() +1, currentChampion.getPosition());
		Champion championDown = championCardDown.getChampion();
		
		// Attack the champion
		championFight(currentChampion, championDown);
		
		// Update both cards
		getCard(championDown).updateCard();
		getCard(currentChampion).updateCard();
		
		nextTurn();
	}

	/**
	 * Delays the start of the AI's turn by creating a timer which goes on to trigger the actionListener
	 */
	private void AITurnDelay()
	{
		Timer testTimer = new Timer(config.AI_WAIT_TIME_MS, this);
		testTimer.setRepeats(false);
		testTimer.start();		
	}
	
	/**
	 * Timer listener which starts the AI's turn once the delay has finished on the timer started
	 * in AITurnDelay()
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		championAITurn();
	}
	
	/**
	 * Removes a champion from the playing field
	 * @param champion The champion to be removed
	 */
	private void knockoutChampion(Champion champion)
	{
		matchView.showDialogue(champion.getName() + " has been injured!");
		
		getCard(champion).removeChampion();
		
		// Remove from turn order
		if (championIsOnPlayerTeam(champion))
		{
			playerChampions.remove(champion);
			
			// Redistribute flag if need be
			if (champion.isFlagCarrier())
			{
				redistributeFlag(team1);
			}
			
		}
		else {
			enemyChampions.remove(champion);

			// Redistribute flag if need be
			if (champion.isFlagCarrier())
			{
				redistributeFlag(team2);
			}
		}
	}
	
	/**
	 * Gives the flag to an alive champion on the specified team.
	 * Should be called after the flag carrier dies.
	 * @param team The team to give a new flag to
	 */
	private void redistributeFlag(Team team)
	{
		ArrayList<Champion> champions;
		
		if (team == team1)
		{
			champions = playerChampions;
		}
		else if (team == team2)
		{
			champions = enemyChampions;
		}
		else {
			System.out.println("WARNING: Flag redistribution failure");
			return;
		}
		
		// End game if there are no champions left to choose from:
		if (champions.size() == 0)
		{
			if (team == team1)
			{
				// Enemy wins
				matchView.showDialogue(team1.getName() + " has had all their champions injured. " + team2.getName() + " wins!");
			}
			else {
				// Player wins
				matchView.showDialogue(team2.getName() + " has had all their champions injured. " + team1.getName() + " wins!");
			}
		}
		
		Random random = new Random();
		
		int i = random.nextInt(champions.size());
		
		champions.get(i).setFlagCarrier(true);
		getCard(champions.get(i)).updateCard();
	}
	
}
