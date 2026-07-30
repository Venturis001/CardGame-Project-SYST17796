/**
 * SYST 17796 Group Project - Blackjack
 */
package ca.sheridancollege.project;

/**
 * A child of GroupOfCards that models the deck used in the game.
 * A Deck is created with all 52 playing cards (13 ranks x 4 suits).
 * The shuffle() method is inherited from GroupOfCards.
 * @author Esosa Emokpae, Jankat Erel, Ranbir Dhanki - July 2026
 */
public class Deck extends GroupOfCards
{
    public Deck()
    {
        super(52);
        for (Suit suit : Suit.values())
        {
            for (Rank rank : Rank.values())
            {
                addCard(new PlayingCard(rank, suit));
            }
        }
    }

    /**
     * Removes the top card from the deck and returns it.
     * @return the card that was dealt
     * @throws IllegalStateException if the deck has already run out of cards
     */
    public Card dealCard()
    {
        if (showCards().isEmpty())
        {
            throw new IllegalStateException("The deck is empty, there is no card left to deal.");
        }
        return showCards().remove(showCards().size() - 1);
    }
}
