package match;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import model.Champion;
import model.Team;
import views.ChampionMatchCard;
import views.MatchView;
import weapons.Fists;

/**
 * Live match, represents a match shown on the UI to the player
 */
public class LiveMatch extends Match implements ActionListener {

	// 2d array of cards which represent a champions position on the board
	private ArrayList<ArrayList<ChampionMatchCard>> cards;

	// The view
	private MatchView matchView;

	/**
	 * List of player champions in the order that their turn occurs
	 */
	private ArrayList<Champion> playerChampions;

	/**
	 * List of enemy champions in the order that their turn occurs
	 */
	private ArrayList<Champion> enemyChampions;

	// If it is currently the player teams turn
	private boolean playerTeamsTurn;

	// The turn number, ranging from 0 - 3 based on which champion's turn it is
	private int turn;

	// The current champion selected for battle
	private Champion currentChampion;
	
	// Boolean which keeps track of whether the game has finished, prevents
	// nextTurn() from executing any logic.
	private boolean gameOver;

	/**
	 * Gets a champion match card
	 *
	 * @param champ The champion which card is wanted
	 * @return The champion card of the champion
	 */
	public ChampionMatchCard getCard(Champion champ) {
		return cards.get(champ.getLane()).get(champ.getPosition());
	}

	/**
	 * Gets a champion match card at specified position
	 *
	 * @param lane     The lane of the card (0 - 3)
	 * @param position The position of the card (0 - 7)
	 * @return The champion card at the specific position
	 */
	public ChampionMatchCard getCard(int lane, int position) {
		return cards.get(lane).get(position);
	}

	/**
	 * Sets the card variable
	 * @param cards The new cards variable
	 */
	public void setCards(ArrayList<ArrayList<ChampionMatchCard>> cards) {
		this.cards = cards;
	}

	/**
	 * Sets the match view
	 * @param matchView The new matchView
	 */
	public void setMatchView(MatchView matchView) {
		this.matchView = matchView;
	}

	/**
	 * Constructor
	 *
	 * @param team1 The player team
	 * @param team2 The team the player is fighting
	 */
	public LiveMatch(Team team1, Team team2) {
		super(team1, team2);

		turn = -1;
		gameOver = false;
	}

	/**
	 * Assigns the player champions and enemy champions to their starting positions
	 * on the game board. Called by the MatchView
	 */
	public void assignChampions() {
		playerChampions = new ArrayList<>();
		enemyChampions = new ArrayList<>();

		playerTeamsTurn = true;

		// Random int between 0 and 3 to determine who has the flag
		Random random = new Random();
		int flagLane = random.nextInt(4);

		// Player Champions:
		int lane = 0;
		for (Champion champion : team1.getChosenChampions()) {
			champion.setLane(lane);
			champion.setPosition(2);

			// Assign flag
			if (lane == flagLane) {
				champion.setFlagCarrier(true);
			} else {
				champion.setFlagCarrier(false);
			}

			cards.get(lane).get(2).setChampion(champion);
			playerChampions.add(champion);
			
			lane++;
		}

		// Enemy champions:
		lane = 0;
		flagLane = random.nextInt(3);

		for (Champion champion : team2.getChosenChampions()) {
			if (lane >= 4) {
				// System.out.println("Breaking!");
				// This shouldn't ever occur,
				// but i'm leaving this here just in case
				break;
			}

			champion.setLane(lane);
			champion.setPosition(4);

			// Assign flag
			if (lane == flagLane) {
				champion.setFlagCarrier(true);
			} else {
				champion.setFlagCarrier(false);
			}

			cards.get(lane).get(4).setChampion(champion);
			enemyChampions.add(champion);

			lane++;
		}
	}

	/**
	 * Updates the current champion to be the next one in the turn order,
	 * Also disables buttons on the matchView if need be and regenerates
	 * the health of each champion
	 */
	public void nextTurn() {
		// System.out.println("nextTurn() called");
		if (gameOver)
		{
			return;
		}
		
		turn++;

		// If all player champions have had their turn, it is the enemy teams turn
		if (playerTeamsTurn) {
			// Check if all player champions have had their turn
			if (turn == playerChampions.size()) {
				playerTeamsTurn = false;
				turn = 0;
			}
		} else {
			// Check if all enemy champions have had their turn
			if (turn == enemyChampions.size()) {
				playerTeamsTurn = true;
				turn = 0;
			}
		}

		// Change button visibility on the view
		if (!playerTeamsTurn) {
			matchView.disableAllButtons();
		}

		if (playerTeamsTurn) {
			// If there are no player champions left, stop
			if (playerChampions.size() == 0){
				return;
			}
			// Player's turn
			currentChampion = playerChampions.get(turn);

			// Regen health
			championHealthRegen(currentChampion);

			matchView.updateSelectedChampionPanel(currentChampion);

			matchView.selectChampion(currentChampion);
		} else {
			if (enemyChampions.size() == 0) {
				return;
			}
			// Enemy turn
			currentChampion = enemyChampions.get(turn);

			// Regen health
			championHealthRegen(currentChampion);

			matchView.updateSelectedChampionPanel(currentChampion);

			AITurnDelay();
		}

	}

	/**
	 * Generates a match result for the match.
	 * @param winner the winning team
	 * @param loser the losing team
	 * @return a MatchResult object containing the match results
	 */
	public MatchResult getMatchResult(Team winner, Team loser) {
		return matchOver(winner, loser);
	}

	/**
	 * Acts out a turn for the AI, dependent upon the current champion
	 */
	private void championAITurn() {
		if (currentChampion.getPosition() == 0) {
			// AI cannot move forward anymore
			nextTurn();
		}

		// Get card infront
		ChampionMatchCard cardInfront = null;

		if (currentChampion.getPosition() != 0) {
			cardInfront = getCard(currentChampion.getLane(), currentChampion.getPosition() - 1);
		}

		// Get card above
		ChampionMatchCard cardAbove = null;

		if (currentChampion.getLane() != 0) {
			cardAbove = getCard(currentChampion.getLane() - 1, currentChampion.getPosition());

			if (!championIsOnPlayerTeam(cardAbove.getChampion())) {
				// Reset to null IF champion above is on enemy team
				cardAbove = null;
			}
		}

		// Get card above
		ChampionMatchCard cardBelow = null;

		if (currentChampion.getLane() != 3) {
			cardBelow = getCard(currentChampion.getLane() + 1, currentChampion.getPosition());
			if (!championIsOnPlayerTeam(cardBelow.getChampion())) {
				// Reset to null IF champion below is on enemy team
				cardBelow = null;
			}
		}

		// If there is a player champion infront of me, attack 100% of the time
		if (cardInfront.getChampion() != null) {
			// attack
			championFight(currentChampion, cardInfront.getChampion());

			nextTurn();
			return;
		}

		// If there is a player champion above me, attack 100% of the time
		if (cardAbove != null) {
			// attack up
			championFight(currentChampion, cardAbove.getChampion());

			nextTurn();
			return;
		}

		// If there is a player champion below me, attack 100% of the time
		if (cardBelow != null) {
			// attack down
			championFight(currentChampion, cardBelow.getChampion());

			nextTurn();
			return;
		}

		// Find a random aggression score, consisting of a random number + the teams
		// aggression factor
		Random random = new Random();
		int agressionScore = random.nextInt(100) + team2.getAgression();

		if (agressionScore < 35) {
			// Wait
			nextTurn();
			return;
		}

		// Move forward
		championMoveForward(currentChampion);

		nextTurn();
		return;

	}

	/**
	 * Updates a champion card to display the data from a champion at the champions
	 * position
	 *
	 * @param champ The champion whose card needs to be updated
	 */
	public void setChampionCard(Champion champ) {
		getCard(champ.getLane(), champ.getPosition()).setChampion(champ);
	}

	/**
	 * Checks to see if a given champion is on the player team
	 *
	 * @param champion Champion to be checked
	 * @return True if the champion is on the player tema
	 */
	public boolean championIsOnPlayerTeam(Champion champion) {
		if (champion == null) {
			return false;
		}

		return team1.getChosenChampions().contains(champion);
	}

	/**
	 * Gets a champion to retreat back a tile depending on what team they're on
	 *
	 * @param champion the champion to retreat
	 */
	private void championMoveBack(Champion champion) {
		int movementInt = 0;

		if (championIsOnPlayerTeam(champion)) {
			// It is on the player team
			movementInt = -1;
		} else {
			// On the enemy team
			movementInt = +1;
		}

		moveChampion(champion, movementInt);
	}

	/**
	 * Moves a champion forward a tile, depending on what team they're on
	 *
	 * @param champion The champion to move forward
	 */
	private void championMoveForward(Champion champion) {
		int movementInt = 0;

		if (championIsOnPlayerTeam(champion)) {
			// It is on the player team
			movementInt = +1;
		} else {
			// On the enemy team
			movementInt = -1;
		}

		moveChampion(champion, movementInt);

		// Check after movement if the champion has properly moved
		if (champion.isFlagCarrier()) {
			checkFlagHolderPosition(champion);
		}
	}

	/**
	 * Moves a champion in a direction by updating champion card
	 *
	 * @param champion  The champion to move
	 * @param direction The direction to go (either +1 or -1)
	 */
	private void moveChampion(Champion champion, int direction) {
		if (champion.getPosition() + direction == 7 || champion.getPosition() + direction == -1) {
			// Moving would take the champion off the screen, so don't move
			return;
		}

		ChampionMatchCard currentCard = getCard(champion);
		ChampionMatchCard nextCard = getCard(champion.getLane(), champion.getPosition() + direction);

		// Move the champion
		currentCard.removeChampion();
		nextCard.setChampion(champion);

		// Tell the champion of their new position
		champion.setPosition(champion.getPosition() + direction);

		// Update the cards
		currentCard.updateCard();
		nextCard.updateCard();

		if (champion.isFlagCarrier()) {
			checkFlagHolderPosition(champion);
		}
	}

	/**
	 * Causes two champions to fight each other
	 *
	 * @param attacker The attacker
	 * @param defender The defender
	 */
	private void championFight(Champion attacker, Champion defender) {
		// Find possible damage if the attacker wins
		float rawDamage = attacker.getDamage();
		float adjustedDamage = rawDamage - defender.getDefense();

		if (!championIsOnPlayerTeam(attacker)) {
			// If the attacker is on the enemy team
			// Apply damage bonus
			adjustedDamage = adjustedDamage * config.AI_DAMAGE_MULTIPLIER;
		}

		// Find winner in the fight
		Champion winner = combat(attacker, defender);

		if (winner == defender) {
			// Defender dodges!
			matchView.showDialogue(defender.getName() + " succesfully dodges " + attacker.getName() + "'s attack!");

			defender.giveXP(rawDamage);
		} else {
			String hitString = "";
			if (Fists.class.isInstance(attacker.getWeapon())) {
				hitString = attacker.getName() + " succesfully hits " + defender.getName() + " for "
						+ Math.ceil(adjustedDamage) + " damage!";
			} else {
				hitString = attacker.getName() + " succesfully hits " + defender.getName() + " with a "
						+ attacker.getWeapon().getName() + " for " + Math.ceil(adjustedDamage) + " damage!";
			}

			matchView.showDialogue(hitString);

			// damage the champion and give XP to the attacker
			attacker.giveXP(rawDamage);
			defender.addStamina(-adjustedDamage);

			// Make the champion fall back
			championMoveBack(defender);

			// Check for stamina
			if (defender.getStamina() <= 0) {
				knockoutChampion(defender);
			}
		}
	}

	// Button Functions:

	/**
	 * Called by the view upon the player wanting the champion to retreat
	 */
	public void championRetreat() {
		championMoveBack(currentChampion);

		nextTurn();
	}

	/**
	 * Called by the view upon the player wanting the champion to attack/move
	 * forward
	 */
	public void championAttack() {
		ChampionMatchCard nextCard = getCard(currentChampion.getLane(), currentChampion.getPosition() + 1);
		Champion championToFight = nextCard.getChampion();

		if (championToFight == null) {
			// Move the champion forward
			championMoveForward(currentChampion);
		} else {
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
	public void championWait() {
		// Champion does nothing
		nextTurn();
	}

	/**
	 * Called by the view upon the player wanting their champion to attack up
	 */
	public void championAttackUp() {
		// Attack up
		ChampionMatchCard championCardUp = getCard(currentChampion.getLane() - 1, currentChampion.getPosition());
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
	public void championAttackDown() {
		// Attack down
		ChampionMatchCard championCardDown = getCard(currentChampion.getLane() + 1, currentChampion.getPosition());
		Champion championDown = championCardDown.getChampion();

		// Attack the champion
		championFight(currentChampion, championDown);

		// Update both cards
		getCard(championDown).updateCard();
		getCard(currentChampion).updateCard();

		nextTurn();
	}

	/**
	 * Delays the start of the AI's turn by creating a timer which goes on to
	 * trigger the actionListener
	 */
	private void AITurnDelay() {
		Timer testTimer = new Timer(config.AI_WAIT_TIME_MS, this);
		testTimer.setRepeats(false);
		testTimer.start();
	}

	/**
	 * Timer listener which starts the AI's turn once the delay has finished on the
	 * timer started in AITurnDelay()
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		championAITurn();
	}

	/**
	 * Removes a champion from the playing field
	 *
	 * @param champion The champion to be removed
	 */
	private void knockoutChampion(Champion champion) {
		matchView.showDialogue(champion.getName() + " has been injured!");

		getCard(champion).removeChampion();

		// Remove from turn order
		if (championIsOnPlayerTeam(champion)) {
			playerChampions.remove(champion);

			// Redistribute flag if need be
			if (champion.isFlagCarrier()) {
				redistributeFlag(team1);
			}

		} else {
			enemyChampions.remove(champion);

			// Redistribute flag if need be
			if (champion.isFlagCarrier()) {
				redistributeFlag(team2);
			}
		}
	}

	/**
	 * Gives the flag to an alive champion on the specified team. Should be called
	 * after the flag carrier dies.
	 *
	 * @param team The team to give a new flag to
	 */
	private void redistributeFlag(Team team) {
		ArrayList<Champion> champions;

		if (team == team1) {
			champions = playerChampions;
		} else if (team == team2) {
			champions = enemyChampions;
		} else {
			System.out.println("WARNING: Flag redistribution failure");
			return;
		}

		// End game if there are no champions left to choose from:
		if (champions.size() == 0) {
			if (team == team1) {
				// Enemy wins
				gameOver(
					team1.getName() + " has had all their champions injured. " + team2.getName() + " wins!",
					team2, 
					team1);
				
				return;
			} else {
				// Player wins
				gameOver(
					team2.getName() + " has had all their champions injured. " + team1.getName() + " wins!",
					team1, 
					team2);
				return;
			}
		} else {
			Random random = new Random();

			int i = random.nextInt(champions.size());

			champions.get(i).setFlagCarrier(true);
			getCard(champions.get(i)).updateCard();
		}
	}

	/**
	 * Checks the position of a provided champion and ends the game if they have
	 * brought the flag to the opposite side of the field
	 *
	 * @param champion The champion to be checked
	 */
	private void checkFlagHolderPosition(Champion champion) {
		if (championIsOnPlayerTeam(champion)) {
			if (champion.getPosition() == 6) {
				// Player victory!
				gameOver(
					champion.getName() + " has moved the flag across the field! " + team1.getName() + " wins!",
					team1, 
					team2);
			}
			
			return;
		}

		if (!championIsOnPlayerTeam(champion)) {
			if (champion.getPosition() == 0) {
				// Enemy victory!
				gameOver(
					champion.getName() + " has moved the flag across the field! " + team2.getName() + " wins!",
					team2,
					team1);
			}
			
			return;
		}
	}

	/**
	 * Regenerates a champions health by their regen stat
	 *
	 * @param champion The champion to regenerate
	 */
	private void championHealthRegen(Champion champion) {
		champion.addStamina(champion.getRegen());
		getCard(champion).updateCard();
	}
	
	/**
	 * Ends the game, preventing a next turn from occurring and displays a message to the player.
	 * @param messageToPlayer The dialogue message to be shown to the player
	 * @param winner The winning team
	 * @param loser The losing team
	 */
	private void gameOver(String messageToPlayer, Team winner, Team loser)
	{
		// Super final measure to prevent double gameovers
		if (gameOver)
		{
			return;
		}
		
		gameOver = true;
		matchView.showDialogue(messageToPlayer);
		gameManager.finishedMatch(matchOver(winner, loser));
	}

	/**
	 * Asks if the player would like to surrender
	 */
	public void playerSurrenderConfirm()
	{
		 int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to surrender?", "Surrender?",
	               JOptionPane.YES_NO_OPTION,
	               JOptionPane.QUESTION_MESSAGE);
	            if(result == JOptionPane.YES_OPTION)
	            {
	               gameOver( team1.getName() + " has surrendered. " + team2.getName() + " wins", team2, team1);
	            }
	            else {
	               // Nothing happens
	            }
	}
	
}
