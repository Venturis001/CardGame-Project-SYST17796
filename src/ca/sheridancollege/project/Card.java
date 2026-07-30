/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project. Must be general
 * enough to be instantiated for any Card game. Students wishing to add to the code
 * should remember to add themselves as a modifier.
 * @author dancye, 2018
 * modified by Esosa Emokpae, Jankat Erel, Ranbir Dhanki - July 2026
 *           (reviewed for our Blackjack game and kept as an abstract base.
 *           PlayingCard is our only child of it, and that is where the rank,
 *           the suit and the card value were added.)
 */
public abstract class Card
{
    /**
     * Students should implement this method for their specific children classes.
     * @return a String representation of a card. Could be an UNO card, a regular playing card etc.
     */
    @Override
    public abstract String toString();

}
