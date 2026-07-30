/**
 * SYST 17796 Group Project - Blackjack
 */
package ca.sheridancollege.project;

/**
 * Enums for ranks of a playing card deck
 * an ace starts at 11 and if needed it gets lowered to a 1
 *
 * @author Esosa Emokpae, Jankat Erel, Ranbir Dhanki - July 2026
 */
public enum Rank
{
    TWO(2), THREE(3), FOUR(4),
    FIVE(5), SIX(6), SEVEN(7),
    EIGHT(8), NINE(9), TEN(10),
    JACK(10), QUEEN(10), KING(10), ACE(11);

    //the Blackjack value of this rank
    private final int value;

    Rank(int value)
    {
        this.value = value;
    }

    /**
     * @return the Blackjack value of this rank
     */
    public int getValue()
    {
        return value;
    }
}
