/**
 * SYST 17796 Group Project - Blackjack
 */
package ca.sheridancollege.project;

/**
 * A class that models one standard
 * playing card with a rank and a suit such as ACE of SPADES).
 * @author Esosa Emokpae, Jankat Erel, Ranbir Dhanki - July 2026
 */
public class PlayingCard extends Card 
{
    private final Rank rank;
    private final Suit suit;
    
    public PlayingCard(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }
    
    /**
     * return the rank of this card
     */
    public Rank getRank()
    {
        return rank;
    }
    
    /**
     * return the suit of this card
     */
    public Suit getSuit()
    {
        return suit;
    }
    
    /**
     * return the Blackjack value for this card
     */
    public int getValue()
    {
        return rank.getValue();
    }
    
    /**
     * return a String version of this card
     */
    @Override
    public String toString()
    {
        return rank + " of " + suit;
    }
}
