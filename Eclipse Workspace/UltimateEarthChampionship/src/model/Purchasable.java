package model;

import javax.swing.ImageIcon;

import exception.FullTeamException;
import exception.IllegalPurchaseException;
import exception.InsufficientFundsException;

/**
 * Interface for objects that can be purchased in the shop. This interface
 * ensure the classes implementing it contains all the necessary functionality
 * to interact with the shop in order to be bought and sold.
 */
public interface Purchasable {

	/**
	 * Get the name of the purchasable object.
	 *
	 * @return The name of the object
	 */
	String getName();

	/**
	 * Get the price of the purchasable object.
	 *
	 * @return The price of the object
	 */
	float getPrice();

	/**
	 * Get the image of the purchasable object.
	 *
	 * @return The image of the object
	 */
	ImageIcon getImage();

	/**
	 * Method to buy the purchasable for the given team.
	 *
	 * @param team the team that wants to buy the purchasable
	 * @throws InsufficientFundsException if the team cannot afford this purchasable
	 * @throws FullTeamException          if the team already has a full inventory
	 *                                    of this purchasable type
	 * @throws IllegalPurchaseException   if the purchase should not be allowed for
	 *                                    the team
	 */
	void buy(Team team) throws InsufficientFundsException, FullTeamException, IllegalPurchaseException;

	/**
	 * Method to sell the purchasable in the given team.
	 *
	 * @param team the team that contains the purchasable to be sold
	 */
	void sell(Team team);
}