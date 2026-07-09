/**
 * SYST 17796 Group Project - Blackjack
 */
package ca.sheridancollege.project;

/**
 * Creates a new blackjack game and starts it.
 * @author Esosa Emokpae, Jankat Erel, Ranbir Dhanki - July 2026
 */
public class StartBlackjack 
{
    public static void main(String[] args)
    {
        BlackjackGame game = new BlackjackGame("Blackjack");
        game.play();
    }
}
