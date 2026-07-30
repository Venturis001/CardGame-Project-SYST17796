/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game.
 * HINT, you might want to subclass this more than once.
 * The group of cards has a maximum size attribute which is flexible for reuse.
 * @author dancye
 * modified by Esosa Emokpae, Jankat Erel, Ranbir Dhanki - July 2026
 *           (initialized the cards ArrayList in the constructor and
 *           added the addCard() method so subclasses can add cards)
 */
public class GroupOfCards
{

    //The group of cards, stored in an ArrayList
    private ArrayList<Card> cards;
    private int size;//the most cards this grouping was built to hold

    public GroupOfCards(int givenSize)
    {
        size = givenSize;
        cards = new ArrayList<>();
    }

    /**
     * A method that will get the group of cards as an ArrayList
     * @return the group of cards.
     */
    public ArrayList<Card> showCards()
    {
        return cards;
    }

    /**
     * Adds a card to this group of cards.
     * @param card the card to add
     */
    public void addCard(Card card)
    {
        cards.add(card);
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    /**
     * This is the size the group was created for, not a live count. For how
     * many cards are actually being held right now, use showCards().size().
     * @return the maximum size of the group of cards
     */
    public int getSize() {
        return size;
    }

    /**
     * @param givenSize the max size for the group of cards
     */
    public void setSize(int givenSize) {
        size = givenSize;
    }

}//end class
