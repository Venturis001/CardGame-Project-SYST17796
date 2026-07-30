/**
 * SYST 17796 Group Project - Blackjack
 */
package ca.sheridancollege.project;

import java.util.Scanner;

/**
 * A person playing at the table. This class holds the cards they were
 * dealt and asks them to hit or stand until their turn is over.
 * @author Esosa Emokpae, Jankat Erel, Ranbir Dhanki - July 2026
 */
public class BlackjackPlayer extends Player
{
    private Hand hand;       //the cards this player is holding
    private Deck deck;       //the deck this player draws from
    private Scanner keyboard;//used to read the player's choices

    public BlackjackPlayer(String name)
    {
        super(name);
        hand = new Hand();
    }

    /**
     * @return the player's hand
     */
    public Hand getHand()
    {
        return hand;
    }

    /**
     * @param hand the new hand for the player
     */
    public void setHand(Hand hand)
    {
        this.hand = hand;
    }

    /**
     * @return the deck the player draws from
     */
    public Deck getDeck()
    {
        return deck;
    }

    /**
     * @param deck the deck the player will draw from
     */
    public void setDeck(Deck deck)
    {
        this.deck = deck;
    }

    /**
     * @param keyboard the Scanner used to read this player's choices
     */
    public void setKeyboard(Scanner keyboard)
    {
        this.keyboard = keyboard;
    }

    /**
     * Keeps showing the hand and asking hit or stand
     * until the player stands, hits 21, or busts.
     */
    @Override
    public void play()
    {
        boolean turnOver = false;

        while (!turnOver)
        {
            int total = hand.getTotal();
            System.out.println(getPlayerID() + "'s hand: " + hand.showCards()
                    + " (total: " + total + ")");

            if (total == 21)
            {
                System.out.println(getPlayerID() + " has 21!");
                turnOver = true;
            }
            else if (total > 21)
            {
                System.out.println(getPlayerID() + " busts!");
                turnOver = true;
            }
            else
            {
                System.out.print("Hit or stand? (h/s): ");
                String choice = keyboard.nextLine().trim().toLowerCase();

                if (choice.equals("h"))
                {
                    Card newCard = deck.dealCard();
                    hand.addCard(newCard);
                    System.out.println(getPlayerID() + " draws: " + newCard);
                }
                else if (choice.equals("s"))
                {
                    System.out.println(getPlayerID() + " stands at " + total + ".");
                    turnOver = true;
                }
                else
                {
                    System.out.println("Please type h or s.");
                }
            }
        }
        System.out.println();
    }
}
