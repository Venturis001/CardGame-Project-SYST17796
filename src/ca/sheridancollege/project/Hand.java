/**
 * SYST 17796 Group Project - Blackjack
 */
package ca.sheridancollege.project;

/**
 * A child of GroupOfCards, its main job is to add up the Blackjack total of the hand.
 * @author Esosa Emokpae, Jankat Erel, Ranbir Dhanki - July 2026
 */
public class Hand extends GroupOfCards
{
    public Hand()
    {
        super(11); //the most cards a Blackjack hand could ever hold
    }

    /**
     * Adds up the total value of the hand. Every Ace starts as 11,
     * but if the total is over 21, Aces can drop to 1 instead.
     * @return the total value of this hand
     */
    public int getTotal()
    {
        int total = 0;
        int aces = 0;

        for (Card card : showCards())
        {
            PlayingCard playingCard = (PlayingCard) card;
            total = total + playingCard.getValue();
            if (playingCard.getRank() == Rank.ACE)
            {
                aces = aces + 1;
            }
        }

        //count an Ace as 1 instead of 11 if we are over 21
        while (total > 21 && aces > 0)
        {
            total = total - 10;
            aces = aces - 1;
        }

        return total;
    }
}
