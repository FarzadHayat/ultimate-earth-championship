package display;

import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import events.RandomEventInfo;
import manager.GameManager;
import match.LiveMatch;
import match.MatchResult;
import story.Cutscene;
import views.ChampionSetupView;
import views.CutsceneView;
import views.MatchView;
import views.SetupView;
import views.TabbedView;
import views.WeaponSetupView;

/**
 * A concrete implementation of the DisplayStrategy interface for the GUI
 * display. The graphical display uses the Java Swing GUI toolkit to display the
 * game.
 */
public class GraphicalDisplay implements DisplayStrategy {

	private GameManager gameManager = GameManager.getInstance();

	/**
	 * The main frame of the application window.
	 */
	private JFrame frame;
	/**
	 * The width of the application window.
	 */
	public static final int WIDTH = 1600;
	/**
	 * The height of the application window.
	 */
	public static final int HEIGHT = 900;

	/**
	 * Create the GraphicalDisplay object.
	 */
	public GraphicalDisplay() {
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		frame.setTitle("Ultimate Earth Championship");
		frame.setBounds(100, 100, WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * Paint the given view onto the frame's content pane.
	 *
	 * @param view the view to display on the window
	 */
	public void displayView(Container view) {
		frame.setContentPane(view);
		frame.setVisible(true);
	}

	/**
	 * Display the given cutscene on a cutscene view. The cutscene view interacts
	 * with the cutscene object to iterate through the cutscene slides.
	 *
	 * @param cutscene the cutscene to display
	 */
	@Override
	public void displayCutscene(Cutscene cutscene) {
		CutsceneView cutsceneView = new CutsceneView(cutscene);
		displayView(cutsceneView);
	}

	/**
	 * Display the setup view.
	 */
	@Override
	public void displaySetup() {
		SetupView setupView = new SetupView();
		displayView(setupView);
	}

	/**
	 * Display the team view inside a tabbed view.
	 */
	@Override
	public void displayTeam() {
		TabbedView tabbedView = new TabbedView();
		displayView(tabbedView);
		tabbedView.showTeamView();
	}

	/**
	 * Display the shop view inside a tabbed view.
	 */
	@Override
	public void displayShop() {
		TabbedView tabbedView = new TabbedView();
		displayView(tabbedView);
		tabbedView.showShopView();

	}

	/**
	 * Display the stadium view inside a tabbed view.
	 */
	@Override
	public void displayStadium() {
		TabbedView tabbedView = new TabbedView();
		displayView(tabbedView);
		tabbedView.showStadiumView();
	}

	/**
	 * Display the champion setup view.
	 */
	@Override
	public void displayChampionSetup() {
		ChampionSetupView championSetupView = new ChampionSetupView();
		displayView(championSetupView);
	}

	/**
	 * Display the weapon setup view. Assumes the player team has already selected
	 * champions.
	 */
	@Override
	public void displayWeaponSetup() {
		WeaponSetupView weaponSetupView = new WeaponSetupView();
		displayView(weaponSetupView);
	}

	/**
	 * Display the live match view. The live match view interacts closely with the
	 * live match object to handle the match gameplay.
	 *
	 * @param match the match to display
	 */
	@Override
	public void displayLiveMatch(LiveMatch match) {
		MatchView matchView = new MatchView(match);
		displayView(matchView);

	}

	/**
	 * Display the match results using Java Swing pop ups. This method should be
	 * used as a part of the live match view as it does not redirect the user to
	 * another view on its own.
	 *
	 * @param matchResult the object containing the match results
	 */
	@Override
	public void displayMatchResults(MatchResult matchResult) {
		StringBuilder text = new StringBuilder();
		if (matchResult.winningTeam == gameManager.getPlayerTeam()) {
			double winningMoneyRounded = Math.round(matchResult.winningTeamMoney * 100.0) / 100.0;
			double winningScoreRounded = Math.round(matchResult.winningTeamScore * 100.0) / 100.0;
			text.append("You have won the match! Money awarded: ").append(winningMoneyRounded)
					.append(" Score awarded: ").append(winningScoreRounded);
		} else {
			double losingMoneyRounded = Math.round(matchResult.losingTeamMoney * 100.0) / 100.0;
			double losingScoreRounded = Math.round(matchResult.losingTeamScore * 100.0) / 100.0;
			text.append("You have lost the match! Money awarded: ").append(losingMoneyRounded)
					.append(" Score awarded: ").append(losingScoreRounded);
		}
		JOptionPane.showMessageDialog(frame, text.toString());
	}

	/**
	 * Display the week results using Java Swing pop ups. This method should be used
	 * as a part of another view as it does not redirect the user to another view on
	 * its own.
	 */
	@Override
	public void displayWeekResults(ArrayList<RandomEventInfo> randomEvents) {
		String weekResults = String.format(
				"You have reached the end of week %s.\n" + "Your team will now rest back to full stamina.",
				String.valueOf(gameManager.getGameEnvironment().getCurrentWeek()));
		JOptionPane.showMessageDialog(frame, weekResults, "WEEK RESULTS", JOptionPane.INFORMATION_MESSAGE);
		for (RandomEventInfo randomEvent : randomEvents) {
			JOptionPane.showMessageDialog(frame, randomEvent.description + "\n" + randomEvent.effectString,
					randomEvent.name, JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Display the game results view.
	 */
	@Override
	public void displayGameResults() {
		// TODO Auto-generated method stub

	}

}
