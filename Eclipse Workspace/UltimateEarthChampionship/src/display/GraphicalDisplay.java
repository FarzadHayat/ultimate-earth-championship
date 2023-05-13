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

public class GraphicalDisplay implements DisplayStrategy {

	private JFrame frame;

	private GameManager gameManager = GameManager.getInstance();

	public static final int WIDTH = 1600;
	public static final int HEIGHT = 900;

	/**
	 * Create the application.
	 */
	public GraphicalDisplay() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
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
	 * @param view the view to display on the screen
	 */
	public void displayView(Container view) {
		frame.setContentPane(view);
		frame.setVisible(true);
	}

	@Override
	public void displayCutscene(Cutscene cutscene) {
		CutsceneView cutsceneView = new CutsceneView(cutscene);
		displayView(cutsceneView);
	}

	@Override
	public void displaySetup() {
		SetupView setupView = new SetupView();
		displayView(setupView);
	}

	@Override
	public void displayTeam() {
		TabbedView tabbedView = new TabbedView();
		displayView(tabbedView);
		tabbedView.showTeamView();
	}

	@Override
	public void displayShop() {
		TabbedView tabbedView = new TabbedView();
		displayView(tabbedView);
		tabbedView.showShopView();

	}

	@Override
	public void displayStadium() {
		TabbedView tabbedView = new TabbedView();
		displayView(tabbedView);
		tabbedView.showStadiumView();
	}

	@Override
	public void displayChampionSetup() {
		ChampionSetupView championSetupView = new ChampionSetupView();
		displayView(championSetupView);
	}

	@Override
	public void displayWeaponSetup() {
		WeaponSetupView weaponSetupView = new WeaponSetupView();
		displayView(weaponSetupView);
	}

	@Override

	public void displayLiveMatch(LiveMatch match) {

		MatchView matchView = new MatchView(match);
		displayView(matchView);

	}

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

	@Override
	public void displayGameResults() {
		// TODO Auto-generated method stub

	}

}
