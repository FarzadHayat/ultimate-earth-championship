package views;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import manager.GameManager;
import model.Champion;
import model.Team;
import model.Purchasable;

public class MatchSetupView extends JPanel {

	private static final long serialVersionUID = -1352915737619575543L;
	
	private GameManager gameManager = GameManager.getInstance();
	
	private Point currentPoint;
	private Point startPoint;
	
	/**
	 * Create the panel.
	 */
	public MatchSetupView(Team enemyTeam) {
		setLayout(new FlowLayout());
		
		for (Champion champion : gameManager.getPlayerTeam().getChosenChampions()) {
			PurchasableCard purchasableCard = new PurchasableCard((Purchasable) champion, CardType.MINIMAL);
			purchasableCard.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					Point location = SwingUtilities.convertPoint(purchasableCard, e.getPoint(), purchasableCard.getParent());
					if (purchasableCard.getParent().getBounds().contains(location)) {
						Point newLocation = purchasableCard.getLocation();
						newLocation.translate(location.x - currentPoint.x, location.y - currentPoint.y);
						newLocation.x = Math.max(newLocation.x, 0);
						newLocation.y = Math.max(newLocation.y, 0);
						newLocation.x = Math.min(newLocation.x, purchasableCard.getParent().getWidth() - purchasableCard.getWidth());
						newLocation.y = Math.min(newLocation.y, purchasableCard.getParent().getHeight() - purchasableCard.getHeight());
						purchasableCard.setLocation(newLocation);
						currentPoint = location;
					}
				}
			});
			purchasableCard.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					currentPoint = SwingUtilities.convertPoint(purchasableCard, e.getPoint(), purchasableCard.getParent());
					startPoint = purchasableCard.getLocation();
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					Component replacement = null;
					for (Component component : getComponents()) {
						Point relativePoint = SwingUtilities.convertPoint(component.getParent(), currentPoint, component);
						if (component != purchasableCard && component.contains(relativePoint)) {
							replacement = component;
							break;
						}
					}
					if (replacement != null) {
						int i1 = getComponentZOrder(purchasableCard);
						int i2 = getComponentZOrder(replacement);
						gameManager.getPlayerTeam().swapPositions(i1, i2);
						gameManager.visitMatchSetup(enemyTeam);
					}
					else {
						purchasableCard.setLocation(startPoint);
					}
					currentPoint = null;
				}
			});
			add(purchasableCard);
		}
	}

}
