/**
 * SYST 17796 Group Project - Blackjack
 */
package ca.sheridancollege.project;

import java.util.Scanner;

/**
 * Sets up a 1 to 3 player game, each round every player gets two cards
 * and as the round goes on they decide to hit or stand.
 * The dealer plays by the standard house rules and the round results
 * are announced by declareWinner().
 * @author Esosa Emokpae, Jankat Erel, Ranbir Dhanki - July 2026
 */
public class BlackjackGame extends Game
{
    private Deck deck;              //the deck used for the current round
    private final Dealer dealer;    //the dealer (controlled by the game)
    private final Scanner keyboard; //one shared Scanner for all input

    public BlackjackGame(String givenName)
    {
        super(givenName);
        dealer = new Dealer();
        keyboard = new Scanner(System.in);
    }

    /**
     * The main loop of the game. Welcomes the players, sets them up,
     * then keeps playing rounds until they say no to another one.
     */
    @Override
    public void play()
    {
        System.out.println("=== Welcome to " + getGameName() + "! ===");
        System.out.println("Get as close to 21 as you can without going over.");
        System.out.println("Number cards are face value, J/Q/K are 10, Aces are 11 or 1.");
        System.out.println();

        setUpPlayers();

        boolean keepPlaying = true;
        while (keepPlaying)
        {
            playRound();
            System.out.print("Play another round? (y/n): ");
            String answer = keyboard.nextLine().trim().toLowerCase();
            keepPlaying = answer.equals("y") || answer.equals("yes");
            System.out.println();
        }

        System.out.println("Thanks for playing!");
    }

    /**
     * Asks how many people are playing (1 to 3) and their names,
     * then adds them to the game's player list.
     */
    private void setUpPlayers()
    {
        int numPlayers = 0;

        while (numPlayers < 1 || numPlayers > 3)
        {
            System.out.print("How many players? (1-3): ");
            try
            {
                numPlayers = Integer.parseInt(keyboard.nextLine().trim());
            }
            catch (NumberFormatException e)
            {
                numPlayers = 0; //not a number, ask again
            }

            if (numPlayers < 1 || numPlayers > 3)
            {
                System.out.println("Please enter a number from 1 to 3.");
            }
        }

        for (int i = 1; i <= numPlayers; i++)
        {
            System.out.print("Enter a name for player " + i + ": ");
            String name = keyboard.nextLine().trim();
            if (name.isEmpty())
            {
                name = "Player " + i;
            }

            BlackjackPlayer player = new BlackjackPlayer(name);
            player.setKeyboard(keyboard);
            getPlayers().add(player);
        }
        System.out.println();
    }

    /**
     * Plays one full round, new fresh deck and hands, deals two cards to
     * everyone. Each player takes a turn, then the dealer, then the
     * results are displayed.
     */
    private void playRound()
    {
        //fresh shuffled deck and empty hands every round
        deck = new Deck();
        deck.shuffle();

        for (Player p : getPlayers())
        {
            BlackjackPlayer player = (BlackjackPlayer) p;
            player.setHand(new Hand());
            player.setDeck(deck);
        }
        dealer.setHand(new Hand());
        dealer.setDeck(deck);

        //deal two cards to each player and the dealer
        for (int i = 0; i < 2; i++)
        {
            for (Player p : getPlayers())
            {
                BlackjackPlayer player = (BlackjackPlayer) p;
                player.getHand().addCard(deck.dealCard());
            }
            dealer.getHand().addCard(deck.dealCard());
        }

        //the dealer only shows their first card at the start
        System.out.println("Dealer shows: " + dealer.getHand().showCards().get(0)
                + " and one hidden card.");
        System.out.println();

        //each player takes their turn, then the dealer plays
        for (Player p : getPlayers())
        {
            BlackjackPlayer player = (BlackjackPlayer) p;
            player.play();
        }
        dealer.play();

        declareWinner();
    }

    /**
     * Compares every player's total against the dealer's total and
     * shows who won, lost, or tied this round.
     */
    @Override
    public void declareWinner()
    {
        int dealerTotal = dealer.getHand().getTotal();

        System.out.println("--- Round Results ---");
        for (Player p : getPlayers())
        {
            BlackjackPlayer player = (BlackjackPlayer) p;
            int total = player.getHand().getTotal();

            if (total > 21)
            {
                System.out.println(player.getPlayerID() + " busted and loses.");
            }
            else if (dealerTotal > 21)
            {
                System.out.println(player.getPlayerID() + " wins! The dealer busted.");
            }
            else if (total > dealerTotal)
            {
                System.out.println(player.getPlayerID() + " wins, " + total
                        + " beats the dealer's " + dealerTotal + "!");
            }
            else if (total == dealerTotal)
            {
                System.out.println(player.getPlayerID() + " pushes (ties) with the dealer at "
                        + total + ".");
            }
            else
            {
                System.out.println(player.getPlayerID() + " loses, " + total
                        + " is under the dealer's " + dealerTotal + ".");
            }
        }
        System.out.println();
    }
}
