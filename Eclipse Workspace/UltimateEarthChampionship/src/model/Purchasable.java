package model;

import javax.swing.ImageIcon;

import exception.FullTeamException;
import exception.IllegalPurchaseException;
import exception.InsufficientFundsException;

/**
 * Interface for objects that can be purchased in the game shop.
 */
public interface Purchasable {
    
    /**
     * Get the name of the purchasable object.
     * @return The name of the object.
     */
    String getName();
    
    /**
     * Get the price of the purchasable object.
     * @return The price of the object.
     */
    float getPrice();
    
    /**
     * Get the image of the purchasable object.
     * @return The image of the object.
     */
    ImageIcon getImage();
    
    void buy(Team team) throws InsufficientFundsException, FullTeamException, IllegalPurchaseException;
    void sell(Team team);
}