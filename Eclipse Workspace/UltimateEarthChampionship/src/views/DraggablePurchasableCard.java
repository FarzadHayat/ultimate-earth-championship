package views;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.SwingUtilities;

import manager.GameManager;
import model.Purchasable;
import model.Team;

public class DraggablePurchasableCard extends PurchasableCard {

	private static final long serialVersionUID = -2995242825604964903L;

	private GameManager gameManager = GameManager.getInstance();
	
	private Point currentPoint;
	private Point startPoint;
	
	/**
	 * Create the panel.
	 */
	public DraggablePurchasableCard(Purchasable purchasable, CardType cardType, Team enemyTeam) {
		super(purchasable, cardType);
		PurchasableCard purchasableCard = this;
		addMouseMotionListener(new MouseMotionAdapter() {
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
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				currentPoint = SwingUtilities.convertPoint(purchasableCard, e.getPoint(), purchasableCard.getParent());
				startPoint = purchasableCard.getLocation();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Component replacement = null;
				for (Component component : purchasableCard.getParent().getComponents()) {
					Point relativePoint = SwingUtilities.convertPoint(component.getParent(), currentPoint, component);
					if (component != purchasableCard && component.contains(relativePoint)) {
						replacement = component;
						break;
					}
				}
				if (replacement != null) {
					int i1 = purchasableCard.getParent().getComponentZOrder(purchasableCard);
					int i2 = purchasableCard.getParent().getComponentZOrder(replacement);
					gameManager.getPlayerTeam().swapPositions(i1, i2);
					gameManager.visitMatchSetup(enemyTeam);
				}
				else {
					purchasableCard.setLocation(startPoint);
				}
				currentPoint = null;
			}
		});
	}

}
