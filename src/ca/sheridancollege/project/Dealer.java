/**
 * SYST 17796 Group Project - Blackjack
 */
package ca.sheridancollege.project;

/**
 * The dealer is controlled by the game, not a person, so play() is overridden:
 * the dealer keeps hitting until the hand is total 17 or more.
 * @author Esosa Emokpae, Jankat Erel, Ranbir Dhanki - July 2026
 */
public class Dealer extends BlackjackPlayer 
{
    public Dealer()
    {
        super("Dealer");
    }
    
    /**
     * The dealer's turn. The dealer reveals the hand, then follows
     * the standard house rule: hit on 16 or less, stand on 17 or more.
     */

    @Override
    public void play()
    {
        System.out.println("Dealer's hand: " + getHand().showCards()
                + " (total: " + getHand().getTotal() + ")");
        
        while (getHand().getTotal() < 17)
        {
            Card newCard = getDeck().dealCard();
            getHand().addCard(newCard);
            System.out.println("Dealer draws: " + newCard
                    + " (total: " + getHand().getTotal() + ")");
        }
        
        if (getHand().getTotal() > 21)
        {
            System.out.println("Dealer busts!");
        }
        else
        {
            System.out.println("Dealer stands at " + getHand().getTotal() + ".");
        }
        System.out.println();
    }
}
